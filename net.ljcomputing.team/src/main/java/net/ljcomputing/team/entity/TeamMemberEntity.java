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

package net.ljcomputing.team.entity;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;
import org.springframework.data.neo4j.support.index.IndexType;

import net.ljcomputing.core.domain.Relationship;
import net.ljcomputing.core.entity.Entity;
import net.ljcomputing.people.entity.PersonEntity;
import net.ljcomputing.team.domain.TeamMember;

/**
 * Class representing a team member entity.
 * 
 * @author James G. Willmore
 *
 */
@RelationshipEntity(useShortNames = true, type = "TEAM_MEMBER")
public class TeamMemberEntity extends TeamMember implements Relationship<PersonEntity, TeamEntity>, Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 211713338853969856L;

	/** The person entity. */
	@StartNode
	private PersonEntity personEntity;

	/** The team entity. */
	@EndNode
	private TeamEntity teamEntity;

	/** The id. */
	@GraphId
	private Long id;

	@Indexed(indexType = IndexType.FULLTEXT, indexName = "teamMemberIdx")
	private String teamMemberIndex;

	@Indexed(unique = true, indexType = IndexType.FULLTEXT, indexName = "uniqueTeamMemberIdx", failOnDuplicate = true)
	private String indexedValue;

	/**
	 * @see net.ljcomputing.core.entity.Entity#setId(java.lang.Long)
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @see net.ljcomputing.core.entity.Entity#getId()
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @see net.ljcomputing.core.entity.Entity#getIndexedValue()
	 */
	public String getIndexedValue() {
		return indexedValue;
	}

	/**
	 * @see net.ljcomputing.core.entity.Entity#createIndexedValue()
	 */
	public void createIndexedValue() {
		indexedValue = getEndNode().getTeamName() + "\t" + getEndNode().getDescription() + "\t" + getTeamMembershipType() + "\t" + getStartNode().getFullName();

		if (null == getUuid()) {
			createUuid();
		}
	}

	/**
	 * @see net.ljcomputing.core.domain.Relationship#getStartNode()
	 */
	public PersonEntity getStartNode() {
		return personEntity;
	}

	/**
	 * @see net.ljcomputing.core.domain.Relationship#setStartNode(java.lang.Object)
	 */
	public void setStartNode(PersonEntity startNode) {
		this.personEntity = startNode;
	}

	/**
	 * @see net.ljcomputing.core.domain.Relationship#getEndNode()
	 */
	public TeamEntity getEndNode() {
		return teamEntity;
	}

	/**
	 * @see net.ljcomputing.core.domain.Relationship#setEndNode(java.lang.Object)
	 */
	public void setEndNode(TeamEntity endNode) {
		this.teamEntity = endNode;
	}
}
