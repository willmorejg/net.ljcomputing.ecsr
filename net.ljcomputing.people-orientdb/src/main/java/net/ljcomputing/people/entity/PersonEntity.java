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

package net.ljcomputing.people.entity;

import net.ljcomputing.core.entity.Entity;
import net.ljcomputing.people.domain.Person;

/**
 * Person entity.
 * 
 * @author James G. Willmore
 *
 */
public class PersonEntity extends Person implements Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2564488588081338668L;

	/** The id. */
	private Long id;

	/** Used to create uniqueness between entities (think primary key). */
	private String indexedValue;

	/**
	 * Instantiates a new person entity.
	 */
	public PersonEntity() {}
	
	/**
	 * Instantiates a new person entity.
	 *
	 * @param person the person
	 */
	public PersonEntity(Person person) {
		super(person);
	}

	/**
	 * Instantiates a new person entity.
	 *
	 * @param person the person
	 * @param id the id
	 */
	public PersonEntity(Person person, Long id) {
		this(person);
		setId(id);
	}

	/**
	 * Instantiates a new person entity (copy constructor).
	 *
	 * @param personEntity the person entity
	 */
	public PersonEntity(PersonEntity personEntity) {
		this(personEntity, personEntity.getId());
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.entity.Entity#setId(java.lang.Long)
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.entity.Entity#getId()
	 */
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.entity.Entity#getIndexedValue()
	 */
	public String getIndexedValue() {
		return indexedValue;
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.entity.Entity#createIndexedValue()
	 */
	public void createIndexedValue() {
		if (null == getUuid()) {
			createUuid();
		}

		if (null == indexedValue) {
			indexedValue = getUuid().toString();
		}

		createFullName();
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.people.domain.Person#toString()
	 */
	@Override
	public String toString() {
		return "PersonEntity [id=" + id + ", indexedValue=" + indexedValue + ", "
				+ super.toString() + "]";
	}
}
