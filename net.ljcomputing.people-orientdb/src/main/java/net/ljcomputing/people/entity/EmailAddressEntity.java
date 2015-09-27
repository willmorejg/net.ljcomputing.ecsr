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

import net.ljcomputing.people.domain.EmailAddress;

/**
 * Email address entity.
 * 
 * @author James G. Willmore
 *
 */
public class EmailAddressEntity extends EmailAddress implements ContactEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2564488588081338668L;

	/** The id. */
	private Long id;

	/** The email address. */
	private String emailAddress;

	/** The indexed value. */
	private String indexedValue;
	
	/**
	 * Instantiates a new email address entity.
	 */
	public EmailAddressEntity() {}
	
	/**
	 * Instantiates a new email address entity.
	 *
	 * @param emailAddress the email address
	 */
	public EmailAddressEntity(EmailAddress emailAddress) {
		super(emailAddress);
	}
	
	/**
	 * Instantiates a new email address entity.
	 *
	 * @param emailAddress the email address
	 * @param id the id
	 */
	public EmailAddressEntity(EmailAddress emailAddress, Long id) {
		this(emailAddress);
		setId(id);
	}
	
	/**
	 * Instantiates a new email address entity (copy constructor).
	 *
	 * @param emailAddressEntity the email address entity
	 */
	public EmailAddressEntity(EmailAddressEntity emailAddressEntity) {
		this(emailAddressEntity, emailAddressEntity.getId());
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
		indexedValue = getFullEmailAddress();

		if (null == getUuid()) {
			createUuid();
		}
	}

	@Override
	public String toString() {
		return "EmailAddressEntity [id=" + id + ", emailAddress=" + emailAddress + ", indexedValue=" + indexedValue
				+ ", " + super.toString() + "]";
	}
}
