/**
           Copyright 2015, James G. Willmore

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package net.ljcomputing.team.domain;

import java.io.Serializable;
import java.util.Objects;

import com.google.common.base.Strings;
import com.google.common.collect.ComparisonChain;

import net.ljcomputing.core.domain.AbstractDomain;
import net.ljcomputing.core.domain.Domain;

/**
 * Class depicting a team.
 *
 * @author James G. Willmore
 */
public class Team extends AbstractDomain implements Serializable, Comparable<Team>, Domain {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7144693468911407437L;

	/** The name. */
	private String name;

	/** The description. */
	private String description;

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the team name.
	 *
	 * @return the team name
	 */
	public String getTeamName() {
		StringBuffer buf = new StringBuffer(name);
		return buf.toString();
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		final Team other = (Team) obj;
		return Objects.equals(this.name, other.name);
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Team o) {
		return ComparisonChain.start().compare(Strings.nullToEmpty(this.name), Strings.nullToEmpty(o.name)).result();
	}

	/**
	 * @see net.ljcomputing.core.domain.AbstractDomain#toString()
	 */
	@Override
	public String toString() {
		return "Team [" + super.toString() + ", name=" + name + ", description=" + description + "]";
	}

}
