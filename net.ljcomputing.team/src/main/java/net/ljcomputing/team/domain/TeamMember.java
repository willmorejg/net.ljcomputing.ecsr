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

import com.google.common.collect.ComparisonChain;

import net.ljcomputing.core.domain.AbstractDomain;
import net.ljcomputing.core.domain.TeamMembershipType;
import net.ljcomputing.people.domain.Person;

/**
 * Class depicting a team member.
 * 
 * @author James G. Willmore
 *
 */
public class TeamMember extends AbstractDomain implements Comparable<TeamMember>, Serializable, TeamMembership<Person, Team> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4583528327580353199L;

	/** The team membership type. */
	protected TeamMembershipType teamMembershipType;

	/** The team member. */
	protected Person teamMember;

	/** The team. */
	protected Team team;

	/**
	 * @see net.ljcomputing.team.domain.TeamMembership#getTeamMembershipType()
	 */
	public TeamMembershipType getTeamMembershipType() {
		return teamMembershipType;
	}

	/**
	 * @see net.ljcomputing.team.domain.TeamMembership#setTeamMembershipType(net.ljcomputing.core.domain.TeamMembershipType)
	 */
	public void setTeamMembershipType(TeamMembershipType teamMembershipType) {
		this.teamMembershipType = teamMembershipType;
	}

	/**
	 * @see net.ljcomputing.team.domain.TeamMembership#getTeamMember()
	 */
	public Person getTeamMember() {
		return teamMember;
	}

	/**
	 * @see net.ljcomputing.team.domain.TeamMembership#setTeamMember(java.lang.Object)
	 */
	public void setTeamMember(Person teamMember) {
		this.teamMember = teamMember;
	}

	/**
	 * @see net.ljcomputing.team.domain.TeamMembership#getTeam()
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 * @see net.ljcomputing.team.domain.TeamMembership#setTeam(java.lang.Object)
	 */
	public void setTeam(Team team) {
		this.team = team;
	}

	/**
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

		final TeamMember other = (TeamMember) obj;
		return Objects.equals(this.team, other.team) && Objects.equals(this.teamMember, other.teamMember)
				&& Objects.equals(this.teamMembershipType, other.teamMembershipType);
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(team, teamMember, teamMembershipType);
	}

	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(TeamMember o) {
		return ComparisonChain.start().compare(this.team, o.team).compare(this.teamMember, o.teamMember)
				.compare(this.teamMembershipType, o.teamMembershipType).result();
	}

	/**
	 * @see net.ljcomputing.core.domain.AbstractDomain#toString()
	 */
	@Override
	public String toString() {
		return "TeamMember [" + super.toString() + ", teamMembershipType=" + teamMembershipType + ", teamMember="
				+ teamMember + ", team=" + team + "]";
	}
}
