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
package org.jbelt.tools.i18n;

import org.jboss.seam.international.status.builder.BundleKey;

/**
 */
public class DefaultBundleKey extends BundleKey {

    public static final String DEFAULT_BUNDLE_NAME = "messages";

    public DefaultBundleKey(String key) {
        super(DEFAULT_BUNDLE_NAME, key);
    }
}
