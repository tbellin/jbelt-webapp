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
 */
package org.jbelt.tools.controller;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.jbelt.tools.model.Member;
import org.jboss.logging.Logger;
import org.jboss.seam.solder.logging.Category;


// The @Stateful annotation eliminates the need for manual transaction demarcation
@Stateful
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class MemberRegistration {

   @Inject
   @Category("jbelt-webapp")
   private Logger log;

   @Inject
   private EntityManager em;

   @Inject
   private Event<Member> memberEventSrc;

   private Member newMember;

   @Produces
   @Named
   public Member getNewMember() {
      return newMember;
   }

   public void register() throws Exception {
      log.info("Registering " + newMember.getName());
      em.persist(newMember);
      memberEventSrc.fire(newMember);
      initNewMember();
   }

   @PostConstruct
   public void initNewMember() {
      newMember = new Member();
   }
}
