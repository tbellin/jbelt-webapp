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
 */
package org.jbelt.tools.exceptioncontrol;

import org.jboss.logging.Logger;
import org.jboss.seam.exception.control.CaughtException;
import org.jboss.seam.exception.control.Handles;
import org.jboss.seam.exception.control.HandlesExceptions;

/**
 * Logs all exceptions and allows the to propagate
 *
 * @author <a href="http://community.jboss.org/people/tbellin">Tiziano Bellin</a>
 */
@HandlesExceptions
public class GeneralExceptionHandler {

    public void printExceptionMessage(@Handles CaughtException<Throwable> event, Logger log) {
        log.info("Exception logged by seam-catch catcher: " + event.getException().getMessage());
        event.rethrow();
    }
}
