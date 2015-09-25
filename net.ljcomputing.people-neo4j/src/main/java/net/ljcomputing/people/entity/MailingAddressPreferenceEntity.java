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

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import net.ljcomputing.people.domain.ContactOrder;
import net.ljcomputing.people.domain.ContactType;
import net.ljcomputing.people.domain.MailingAddressType;

/**
 * Entity encapsulating a personal mailing address contact preference.
 * 
 * @author James G. Willmore
 *
 */
@RelationshipEntity(useShortNames = true, type = MailingAddressPreferenceEntity.TYPE)
public class MailingAddressPreferenceEntity extends AbstractPersonalContactPreference<MailingAddressEntity> {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5377750195215979375L;

	/** The relationship type. */
	public final static String TYPE = "MAILING_PREFERENCE";

	/** The indexed value. */
	/** Used to create uniqueness between entities (think primary key). */
	@Indexed(unique = true, indexType = IndexType.FULLTEXT, indexName = "personalEmailAddressPreferenceIdx")
	private String indexedValue;
	
	/** The mailing address type. */
	private MailingAddressType mailingAddressType = MailingAddressType.UNKNOWN;
	
	/**
	 * Instantiates a new mailing address preference entity.
	 */
	public MailingAddressPreferenceEntity() {}
	
	/**
	 * Instantiates a new mailing address preference entity.
	 *
	 * @param personEntity the person entity
	 * @param mailingAddressEntity the mailing address entity
	 * @param contactOrder the contact order
	 * @param contactType the contact type
	 * @param mailingAddressType the mailing address type
	 * @param id the id
	 */
	public MailingAddressPreferenceEntity(PersonEntity personEntity, MailingAddressEntity mailingAddressEntity,
			ContactOrder contactOrder, ContactType contactType, MailingAddressType mailingAddressType, Long id) {
		setPerson(personEntity);
		setContact(mailingAddressEntity);
		setContactOrder(contactOrder);
		setContactType(contactType);
		setMailingAddressType(mailingAddressType);
		setId(id);
	}
	
	/**
	 * Instantiates a new mailing address preference entity (copy constructor).
	 *
	 * @param mailingAddressPreferenceEntity the mailing address preference entity
	 */
	public MailingAddressPreferenceEntity(MailingAddressPreferenceEntity mailingAddressPreferenceEntity) {
		this(mailingAddressPreferenceEntity.getPerson(), mailingAddressPreferenceEntity.getContact(), 
				mailingAddressPreferenceEntity.getContactOrder(), mailingAddressPreferenceEntity.getContactType(), 
				mailingAddressPreferenceEntity.getMailingAddressType(), mailingAddressPreferenceEntity.getId());
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.entity.EntityRelationship#getType()
	 */
	public final String getType() {
		return TYPE;
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.people.entity.AbstractPersonalContactPreference#createIndexedValue()
	 */
	public void createIndexedValue() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(getPerson().getIndexedValue());
		buf.append("\t");
		buf.append(getContactOrder());
		buf.append("\t");
		buf.append(getContactType());
		buf.append("\t");
		buf.append(getMailingAddressType());
		buf.append("\t");
		buf.append(getContact().getIndexedValue());
		
		indexedValue = buf.toString();
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.people.entity.AbstractPersonalContactPreference#getIndexedValue()
	 */
	public String getIndexedValue() {
		return indexedValue;
	}

	/**
	 * Gets the mailing address type.
	 *
	 * @return the mailing address type
	 */
	public MailingAddressType getMailingAddressType() {
		return mailingAddressType;
	}

	/**
	 * Sets the mailing address type.
	 *
	 * @param mailingAddressType the new mailing address type
	 */
	public void setMailingAddressType(MailingAddressType mailingAddressType) {
		this.mailingAddressType = mailingAddressType;
	}
}
