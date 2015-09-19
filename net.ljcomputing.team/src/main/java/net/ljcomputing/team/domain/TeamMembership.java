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

import net.ljcomputing.core.domain.TeamMembershipType;

/**
 * Interface that defines team membership.
 * 
 * @author James G. Willmore
 *
 */
public interface TeamMembership<T, S> {

	/**
	 * Gets the team membership type.
	 *
	 * @return the team membership type
	 */
	public TeamMembershipType getTeamMembershipType();

	/**
	 * Sets the team membership type.
	 *
	 * @param teamMembershipType
	 *            the new team membership type
	 */
	public void setTeamMembershipType(TeamMembershipType teamMembershipType);

	/**
	 * Gets the team member.
	 *
	 * @return the team member
	 */
	public T getTeamMember();

	/**
	 * Sets the team member.
	 *
	 * @param teamMember
	 *            the new team member
	 */
	public void setTeamMember(T teamMember);
	
	/**
	 * Gets the team.
	 *
	 * @return the team
	 */
	public S getTeam();
	
	/**
	 * Sets the team.
	 *
	 * @param team the new team
	 */
	public void setTeam(S team);
}
