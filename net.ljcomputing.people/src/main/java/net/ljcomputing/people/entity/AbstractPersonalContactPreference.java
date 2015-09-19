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

import java.util.UUID;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.StartNode;

import net.ljcomputing.core.entity.Entity;
import net.ljcomputing.people.domain.ContactOrder;
import net.ljcomputing.people.domain.ContactPreference;
import net.ljcomputing.people.domain.ContactType;

/**
 * Abstract implementation of a personal contact preference (relationship) entity.
 *
 * @author James G. Willmore
 * @param <T> the generic type
 */
public abstract class AbstractPersonalContactPreference<T extends Entity> implements ContactPreference, PersonalContactPreferenceEntity<T> {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1021219383686202784L;

	/** The id. */
	@GraphId
	protected Long id;
	
	/** The uuid. */
	protected UUID uuid;
	
	/** The person. */
	@StartNode
	protected PersonEntity person;
	
	/** The contact. */
	@Fetch
	@EndNode
	protected T contact;
	
	/** The contact order. */
	private ContactOrder contactOrder = ContactOrder.UNKNOWN;
	
	/** The contact type. */
	private ContactType contactType = ContactType.UNKNOWN;

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
	 * @see net.ljcomputing.core.entity.Entity#createIndexedValue()
	 */
	public abstract void createIndexedValue();

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.entity.Entity#getIndexedValue()
	 */
	public abstract String getIndexedValue();

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.domain.Domain#setUuid(java.util.UUID)
	 */
	@Override
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.domain.Domain#getUuid()
	 */
	public UUID getUuid() {
		return uuid;
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.domain.Domain#createUuid()
	 */
	public void createUuid() {
		if(null == uuid) {
			uuid = UUID.randomUUID();
		}
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.people.entity.PersonalContactPreferenceEntity#setPerson(net.ljcomputing.people.entity.PersonEntity)
	 */
	public void setPerson(PersonEntity person) {
		this.person = person;
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.people.entity.PersonalContactPreferenceEntity#getPerson()
	 */
	public PersonEntity getPerson() {
		return person;
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.people.entity.PersonalContactPreferenceEntity#setContact(net.ljcomputing.core.entity.Entity)
	 */
	@Override
	public void setContact(T contact) {
		this.contact = contact;
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.people.entity.PersonalContactPreferenceEntity#getContact()
	 */
	public T getContact() {
		return contact;
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.people.domain.ContactPreference#getContactOrder()
	 */
	public ContactOrder getContactOrder() {
		return contactOrder;
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.people.domain.ContactPreference#setContactOrder(net.ljcomputing.people.domain.ContactOrder)
	 */
	public void setContactOrder(ContactOrder contactOrder) {
		this.contactOrder = contactOrder;
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.people.domain.ContactPreference#getContactType()
	 */
	public ContactType getContactType() {
		return contactType;
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.people.domain.ContactPreference#setContactType(net.ljcomputing.people.domain.ContactType)
	 */
	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}
	
	
	/* (non-Javadoc)
	 * @see net.ljcomputing.core.entity.Entity#isValid()
	 */
	public Boolean isValid() {
		Boolean result = Boolean.FALSE;
		
		if(null != getPerson() && getPerson().isValid() && null != getContact() && getContact().isValid()) {
			result = Boolean.TRUE;
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AbstractPersonalContactPreference [id=" + id + ", uuid=" + uuid + ", person=" + person + ", contact="
				+ contact + ", contactOrder=" + contactOrder + ", contactType=" + contactType + "]";
	}
}
