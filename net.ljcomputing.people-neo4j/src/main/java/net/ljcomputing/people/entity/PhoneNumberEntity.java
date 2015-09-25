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

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import net.ljcomputing.people.domain.PhoneNumber;

/**
 * Phone number entity.
 * 
 * @author James G. Willmore
 *
 */
@NodeEntity(useShortNames = true)
public class PhoneNumberEntity extends PhoneNumber implements ContactEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2564488588081338668L;

	/** The id. */
	@GraphId
	private Long id;

	/** The indexed value. */
	@Indexed(unique = true, indexType = IndexType.FULLTEXT, indexName = "uniquePhoneNumberIdx", failOnDuplicate = true)
	private String indexedValue;

	/**
	 * Instantiates a new phone number entity.
	 */
	public PhoneNumberEntity() {}
	
	/**
	 * Instantiates a new phone number entity.
	 *
	 * @param phoneNumber the phone number
	 */
	public PhoneNumberEntity(PhoneNumber phoneNumber) {
		super(phoneNumber);
	}

	/**
	 * Instantiates a new phone number entity.
	 *
	 * @param phoneNumber the phone number
	 * @param id the id
	 */
	public PhoneNumberEntity(PhoneNumber phoneNumber, Long id) {
		super(phoneNumber);
		setId(id);
	}

	/**
	 * Instantiates a new phone number entity.
	 *
	 * @param phoneNumberEntity the phone number entity
	 */
	public PhoneNumberEntity(PhoneNumberEntity phoneNumberEntity) {
		this(phoneNumberEntity, phoneNumberEntity.getId());
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 * @see net.ljcomputing.core.entity.Entity#setId(java.lang.Long)
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 * @see net.ljcomputing.core.entity.Entity#getId()
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Gets the indexed value.
	 *
	 * @return the indexed value
	 * @see net.ljcomputing.core.entity.Entity#getIndexedValue()
	 */
	public String getIndexedValue() {
		return indexedValue;
	}

	/**
	 * Creates the indexed value.
	 *
	 * @see net.ljcomputing.core.entity.Entity#createIndexedValue()
	 */
	public void createIndexedValue() {
		indexedValue = getFullPhoneNumber();

		if (null == getUuid()) {
			createUuid();
		}
	}

	@Override
	public String toString() {
		return "PhoneNumberEntity [id=" + id + ", indexedValue=" + indexedValue + ", " + super.toString()
				+ "]";
	}
}
