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

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import net.ljcomputing.core.entity.Entity;
import net.ljcomputing.team.domain.Team;

/**
 * Team entity.
 * 
 * @author James G. Willmore
 *
 */
@NodeEntity(useShortNames = true)
public class TeamEntity extends Team implements Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9183937661836181000L;

	/** The id. */
	@GraphId
	private Long id;

	/**
	 * The team's name. Used to create uniqueness between entities (think
	 * primary key)
	 */
	@Indexed(unique = false, indexType = IndexType.FULLTEXT, indexName = "teamNameIdx")
	private String teamName;

	/**
	 * Used to create uniqueness between entities (think primary key)
	 */
	@Indexed(unique = true, indexType = IndexType.FULLTEXT, indexName = "teamIdx")
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
		if (null == getUuid()) {
			createUuid();
		}

		if (null == indexedValue) {
			indexedValue = getUuid().toString();
		}

		teamName = getTeamName();
	}

	/**
	 * @see net.ljcomputing.team.domain.Team#toString()
	 */
	@Override
	public String toString() {
		return "TeamEntity [id=" + id + ", " + super.toString() + "]";
	}

}
