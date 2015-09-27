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

import net.ljcomputing.people.domain.ContactOrder;
import net.ljcomputing.people.domain.ContactType;
import net.ljcomputing.people.domain.PhoneType;

/**
 * Entity encapsulating a personal phone number contact preference.
 * 
 * @author James G. Willmore
 *
 */
public class PhoneNumberPreferenceEntity extends AbstractPersonalContactPreference<PhoneNumberEntity> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5377750195215979375L;

	/** The relationship type. */
	public final static String TYPE = "PHONE_PREFERENCE";

	/** The indexed value. */
	/** Used to create uniqueness between entities (think primary key). */
	private String indexedValue;

	/** The phone type. */
	private PhoneType phoneType = PhoneType.UNKNOWN;
	
	/**
	 * Instantiates a new phone number preference entity.
	 */
	public PhoneNumberPreferenceEntity() {}

	/**
	 * Instantiates a new phone number preference entity.
	 *
	 * @param personEntity the person entity
	 * @param phoneNumberEntity the phone number entity
	 * @param contactOrder the contact order
	 * @param contactType the contact type
	 * @param phoneType the phone type
	 * @param id the id
	 */
	public PhoneNumberPreferenceEntity(PersonEntity personEntity, PhoneNumberEntity phoneNumberEntity,
			ContactOrder contactOrder, ContactType contactType, PhoneType phoneType, Long id) {
		setPerson(personEntity);
		setContact(phoneNumberEntity);
		setContactOrder(contactOrder);
		setContactType(contactType);
		setPhoneType(phoneType);
		setId(id);
	}

	/**
	 * Instantiates a new phone number preference entity (copy constructor).
	 *
	 * @param phoneNumberPreferenceEntity the phone number preference entity
	 */
	public PhoneNumberPreferenceEntity(PhoneNumberPreferenceEntity phoneNumberPreferenceEntity) {
		this(phoneNumberPreferenceEntity.getPerson(), phoneNumberPreferenceEntity.getContact(),
				phoneNumberPreferenceEntity.getContactOrder(), phoneNumberPreferenceEntity.getContactType(),
				phoneNumberPreferenceEntity.getPhoneType(), phoneNumberPreferenceEntity.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ljcomputing.core.entity.EntityRelationship#getType()
	 */
	public final String getType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ljcomputing.people.entity.AbstractPersonalContactPreference#
	 * createIndexedValue()
	 */
	public void createIndexedValue() {
		StringBuffer buf = new StringBuffer();

		buf.append(getPerson().getIndexedValue());
		buf.append("\t");
		buf.append(getContactOrder());
		buf.append("\t");
		buf.append(getContactType());
		buf.append("\t");
		buf.append(getPhoneType());
		buf.append("\t");
		buf.append(getContact().getIndexedValue());

		indexedValue = buf.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ljcomputing.people.entity.AbstractPersonalContactPreference#
	 * getIndexedValue()
	 */
	public String getIndexedValue() {
		return indexedValue;
	}

	/**
	 * Gets the phone type.
	 *
	 * @return the phone type
	 */
	public PhoneType getPhoneType() {
		return phoneType;
	}

	/**
	 * Sets the phone type.
	 *
	 * @param phoneType the new phone type
	 */
	public void setPhoneType(PhoneType phoneType) {
		this.phoneType = phoneType;
	}
}
