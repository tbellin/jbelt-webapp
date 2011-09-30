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
package org.jbelt.tools.reference;

/**
 * A simple Java bean representing a month. This bean assumes that the names it is provided have already been localized.
 *
 * @author <a href="http://community.jboss.org/people/tbellin">Tiziano Bellin</a>
 */
public class Month {

    private int index;
    private String name;
    private String shortName;

    public Month() {
    }

    public Month(int index, String name, String shortName) {
        this.index = index;
        this.name = name;
        this.shortName = shortName;
    }

    public int getIndex() {
        return index;
    }

    public int getNumber() {
        return index + 1;
    }

    public String getLongName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getName() {
        return name;
    }
}
