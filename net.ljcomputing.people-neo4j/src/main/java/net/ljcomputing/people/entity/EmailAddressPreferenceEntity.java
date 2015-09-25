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

/**
 * Entity encapsulating a personal email address contact preference.
 * 
 * @author James G. Willmore
 *
 */
@RelationshipEntity(useShortNames = true, type = EmailAddressPreferenceEntity.TYPE)
public class EmailAddressPreferenceEntity extends AbstractPersonalContactPreference<EmailAddressEntity> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5377750195215979375L;

	/** The relationship type. */
	public final static String TYPE = "EMAIL_PREFERENCE";

	/** The indexed value. */
	/** Used to create uniqueness between entities (think primary key). */
	@Indexed(unique = true, indexType = IndexType.FULLTEXT, indexName = "personalEmailAddressPreferenceIdx")
	private String indexedValue;

	/**
	 * Instantiates a new email address preference entity.
	 */
	public EmailAddressPreferenceEntity() {}

	/**
	 * Instantiates a new email address preference entity.
	 *
	 * @param personEntity the person entity
	 * @param emailAddressEntity the email address entity
	 * @param contactOrder the contact order
	 * @param contactType the contact type
	 * @param id the id
	 */
	public EmailAddressPreferenceEntity(PersonEntity personEntity, EmailAddressEntity emailAddressEntity,
			ContactOrder contactOrder, ContactType contactType, Long id) {
		setPerson(personEntity);
		setContact(emailAddressEntity);
		setContactOrder(contactOrder);
		setContactType(contactType);
		setId(id);
	}

	/**
	 * Instantiates a new email address preference entity.
	 *
	 * @param emailAddressPreferenceEntity the email address preference entity
	 */
	public EmailAddressPreferenceEntity(EmailAddressPreferenceEntity emailAddressPreferenceEntity) {
		this(emailAddressPreferenceEntity.getPerson(), emailAddressPreferenceEntity.getContact(),
				emailAddressPreferenceEntity.getContactOrder(), emailAddressPreferenceEntity.getContactType(),
				emailAddressPreferenceEntity.getId());
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
		buf.append(getContact().getIndexedValue());

		indexedValue = buf.toString();
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.people.entity.AbstractPersonalContactPreference#getIndexedValue()
	 */
	public String getIndexedValue() {
		return indexedValue;
	}
}
