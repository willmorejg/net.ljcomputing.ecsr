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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import net.ljcomputing.people.domain.ContactOrder;
import net.ljcomputing.people.domain.ContactType;
import net.ljcomputing.people.domain.MailingAddressType;
import net.ljcomputing.people.domain.PhoneType;

/**
 * Encapsulation of personal contact preferences associate with a person.
 * 
 * @author James G. Willmore
 *
 */
public class PersonalContactsEntity extends PersonEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7714367051246027961L;

	/**
	 * Instantiates a new personal contacts entity.
	 */
	public PersonalContactsEntity() {
		super();
	}

	/**
	 * Instantiates a new personal contacts entity.
	 *
	 * @param personEntity the person entity
	 */
	public PersonalContactsEntity(PersonEntity personEntity) {
		super(personEntity);
	}

	/** The email preferences. */
	private Set<EmailAddressPreferenceEntity> emailPreferences = new HashSet<EmailAddressPreferenceEntity>();

	/** The phone preferences. */
	private Set<PhoneNumberPreferenceEntity> phonePreferences = new HashSet<PhoneNumberPreferenceEntity>();

	/** The mailing preferences. */
	private Set<MailingAddressPreferenceEntity> mailingPreferences = new HashSet<MailingAddressPreferenceEntity>();

	/**
	 * Gets the email preferences.
	 *
	 * @return the email preferences
	 */
	public Set<EmailAddressPreferenceEntity> getEmailPreferences() {
		return emailPreferences;
	}

	/**
	 * Sets the email preferences.
	 *
	 * @param emailPreferences the new email preferences
	 */
	public void setEmailPreferences(Set<EmailAddressPreferenceEntity> emailPreferences) {
		this.emailPreferences = emailPreferences;
	}

	/**
	 * Adds the email preference.
	 *
	 * @param emailAddressEntity the email address entity
	 * @param contactOrder the contact order
	 * @param contactType the contact type
	 */
	public void addEmailPreference(EmailAddressEntity emailAddressEntity, ContactOrder contactOrder,
			ContactType contactType) {
		getEmailPreferences()
				.add(new EmailAddressPreferenceEntity(this, emailAddressEntity, contactOrder, contactType, null));
	}
	
	//TODO verify
	/**
	 * Removes the email preference.
	 *
	 * @param emailAddressPreferenceEntity the email address preference entity
	 */
	public void removeEmailPreference(EmailAddressPreferenceEntity emailAddressPreferenceEntity) {
		Iterator<EmailAddressPreferenceEntity> it = getEmailPreferences().iterator();
		while(it.hasNext()) {
			EmailAddressPreferenceEntity entity = (EmailAddressPreferenceEntity) it.next();
			if(emailAddressPreferenceEntity.equals(entity)) {
				it.remove();
				break;
			}
		}
	}

	/**
	 * Gets the phone preferences.
	 *
	 * @return the phone preferences
	 */
	public Set<PhoneNumberPreferenceEntity> getPhonePreferences() {
		return phonePreferences;
	}

	/**
	 * Sets the phone preferences.
	 *
	 * @param phonePreferences the new phone preferences
	 */
	public void setPhonePreferences(Set<PhoneNumberPreferenceEntity> phonePreferences) {
		this.phonePreferences = phonePreferences;
	}

	/**
	 * Adds the phone preference.
	 *
	 * @param phoneNumberEntity the phone number entity
	 * @param contactOrder the contact order
	 * @param contactType the contact type
	 * @param phoneType the phone type
	 */
	public void addPhonePreference(PhoneNumberEntity phoneNumberEntity, ContactOrder contactOrder,
			ContactType contactType, PhoneType phoneType) {
		getPhonePreferences().add(
				new PhoneNumberPreferenceEntity(this, phoneNumberEntity, contactOrder, contactType, phoneType, null));
	}
	
	//TODO verify
	/**
	 * Removes the phone preference.
	 *
	 * @param phoneNumberPreferenceEntity the phone number preference entity
	 */
	public void removePhonePreference(PhoneNumberPreferenceEntity phoneNumberPreferenceEntity) {
		Iterator<PhoneNumberPreferenceEntity> it = getPhonePreferences().iterator();
		while(it.hasNext()) {
			PhoneNumberPreferenceEntity entity = (PhoneNumberPreferenceEntity) it.next();
			if(phoneNumberPreferenceEntity.equals(entity)) {
				it.remove();
				break;
			}
		}
	}

	/**
	 * Gets the mailing preferences.
	 *
	 * @return the mailing preferences
	 */
	public Set<MailingAddressPreferenceEntity> getMailingPreferences() {
		return mailingPreferences;
	}

	/**
	 * Sets the mailing preferences.
	 *
	 * @param mailingPreferences the new mailing preferences
	 */
	public void setMailingPreferences(Set<MailingAddressPreferenceEntity> mailingPreferences) {
		this.mailingPreferences = mailingPreferences;
	}

	/**
	 * Adds the mailing preference.
	 *
	 * @param mailingAddressEntity the mailing address entity
	 * @param contactOrder the contact order
	 * @param contactType the contact type
	 * @param mailingAddressType the mailing address type
	 */
	public void addMailingPreference(MailingAddressEntity mailingAddressEntity, ContactOrder contactOrder,
			ContactType contactType, MailingAddressType mailingAddressType) {
		getMailingPreferences().add(new MailingAddressPreferenceEntity(this, mailingAddressEntity, contactOrder,
				contactType, mailingAddressType, null));
	}
	
	//TODO verify
	/**
	 * Removes the mailing preference.
	 *
	 * @param mailingAddressPreferenceEntity the mailing address preference entity
	 */
	public void removeMailingPreference(MailingAddressPreferenceEntity mailingAddressPreferenceEntity) {
		Iterator<MailingAddressPreferenceEntity> it = getMailingPreferences().iterator();
		while(it.hasNext()) {
			MailingAddressPreferenceEntity entity = (MailingAddressPreferenceEntity) it.next();
			if(mailingAddressPreferenceEntity.equals(entity)) {
				it.remove();
				break;
			}
		}
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.people.domain.Person#isValid()
	 */
	public Boolean isValid() {
		Boolean result = Boolean.TRUE;

		if (!super.isValid()) {
			result = Boolean.FALSE;
		}

		result = validateMailingPreferences(result);
		result = validatePhonePreferences(result);
		result = validateEmailPreferences(result);

		return result;
	}

	/**
	 * Validate mailing preferences.
	 *
	 * @param result the result 
	 * @return the boolean
	 */
	private Boolean validateMailingPreferences(Boolean result) {
		if (result && null != getMailingPreferences()) {
			for (MailingAddressPreferenceEntity mailPreferenceEntity : getMailingPreferences()) {
				if (result && !mailPreferenceEntity.isValid()) {
					result = Boolean.FALSE;
					break;
				}
			}
		}

		return result;
	}

	/**
	 * Validate phone preferences.
	 *
	 * @param result the result 
	 * @return the boolean
	 */
	private Boolean validatePhonePreferences(Boolean result) {
		if (result && null != getPhonePreferences()) {
			for (PhoneNumberPreferenceEntity phonePreferenceEntity : getPhonePreferences()) {
				if (result && !phonePreferenceEntity.isValid()) {
					result = Boolean.FALSE;
					break;
				}
			}
		}

		return result;
	}

	/**
	 * Validate email preferences.
	 *
	 * @param result the result 
	 * @return the boolean
	 */
	private Boolean validateEmailPreferences(Boolean result) {
		if (result && null != getEmailPreferences()) {
			for (EmailAddressPreferenceEntity emailPreferenceEntity : getEmailPreferences()) {
				if (result && !emailPreferenceEntity.isValid()) {
					result = Boolean.FALSE;
					break;
				}
			}
		}

		return result;
	}
}
