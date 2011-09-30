/*
 * JBelt
 * Copyright 2011, ivytech srl
 *
 * Licensed under the GNU Lesser General Public License, version 2.1 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author <a href="http://community.jboss.org/people/tbellin">Tiziano Bellin</a>
 * 
 */
package org.jbelt.tools.bootstrap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.Status;
import javax.transaction.UserTransaction;
import javax.validation.ConstraintViolationException;

import org.jboss.logging.Logger;
import org.jboss.seam.servlet.WebApplication;
import org.jboss.seam.servlet.event.Initialized;
import org.jboss.seam.servlet.http.ContextPath;

/**
 * An alternative bean used to import seed data into the database when the application is being initialized.
 *
 */
// @Stateless // can't use EJB since they are not yet available for lookup when initialized event is fired
@ApplicationScoped
@Alternative
public class ApplicationInitializer {
    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private UserTransaction utx;

    @Inject
    private Logger log;

    private String template;
    

    public ApplicationInitializer() {

    }

    /**
     * Import seed data when Seam Servlet fires an event notifying observers that the web application is being initialized.
     */
    public void importData(@Observes @Initialized WebApplication webapp) {
        log.info("Importing seed data for application " + webapp.getName());

        template = "artificial";
        log.info("Template standard :" + template);
        
        // use manual transaction control since this is a managed bean
        try {
            utx.begin();
            utx.commit();
            log.info("Seed data successfully imported");
        } catch (Exception e) {
            log.error("Import failed. Seed data will not be available.", e);
            try {
                if (utx.getStatus() == Status.STATUS_ACTIVE) {
                    try {
                        utx.rollback();
                    } catch (Exception rbe) {
                        log.error("Error rolling back transaction", rbe);
                    }
                }
            } catch (Exception se) {
            }
        }
    }

    private void persist(List<?> entities) {
        for (Object e : entities) {
            persist(e);
        }
    }

    private void persist(Object entity) {
        // use a try-catch block here so we can capture identity
        // of entity that fails to persist
        try {
            entityManager.persist(entity);
            entityManager.flush();
        } catch (ConstraintViolationException e) {
            throw new PersistenceException("Cannot persist invalid entity: " + entity);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error persisting entity: " + entity, e);
        }
    }

	/**
	 * @return the template
	 */
    @Produces
    @Named("template")
	public String getTemplate() {
		return template;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(String template) {
		this.template = template;
	}
    
}
