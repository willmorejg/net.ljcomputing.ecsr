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

package net.ljcomputing.people.domain;

import java.util.UUID;

import net.ljcomputing.core.domain.AbstractDomain;

/**
 * Class representing a person.
 * 
 * @author James G. Willmore
 *
 */
public class Person extends AbstractDomain {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4172334299699269349L;

	/** The saluation. */
	private SalutationType saluation = SalutationType.NONE;

	/** The first name. */
	private String firstName;

	/** The middle name. */
	private String middleName;

	/** The last name. */
	private String lastName;

	/** The suffix. */
	private String suffix;

	/** The full name. */
	private String fullName;

	/**
	 * Instantiates a new person.
	 */
	public Person() {}

	/**
	 * Instantiates a new person.
	 *
	 * @param salutation the salutation
	 * @param firstName the first name
	 * @param middleName the middle name
	 * @param lastName the last name
	 * @param suffix the suffix
	 * @param uuid the uuid
	 */
	public Person(SalutationType salutation, String firstName, String middleName, String lastName, String suffix,
			UUID uuid) {
		if (null == uuid) {
			createUuid();
		}

		setSaluation(salutation);
		setFirstName(firstName);
		setMiddleName(middleName);
		setLastName(lastName);
		setSuffix(suffix);

		createFullName();
	}

	/**
	 * Instantiates a new person (copy constructor).
	 *
	 * @param person the person
	 */
	public Person(Person person) {
		this(person.getSaluation(), person.getFirstName(), person.getMiddleName(), person.getLastName(),
				person.getSuffix(), person.getUuid());
	}

	/**
	 * Gets the saluation.
	 *
	 * @return the saluation
	 */
	public SalutationType getSaluation() {
		return saluation;
	}

	/**
	 * Sets the saluation.
	 *
	 * @param saluation the new saluation
	 */
	public void setSaluation(SalutationType saluation) {
		this.saluation = saluation;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
		createFullName();
	}

	/**
	 * Gets the middle name.
	 *
	 * @return the middle name
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * Sets the middle name.
	 *
	 * @param middleName the new middle name
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
		createFullName();
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
		createFullName();
	}

	/**
	 * Gets the suffix.
	 *
	 * @return the suffix
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 * Sets the suffix.
	 *
	 * @param suffix the new suffix
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	/**
	 * Gets the full name.
	 *
	 * @return the full name
	 */
	public String getFullName() {
		StringBuffer buf = new StringBuffer();

		buf.append(
				(null != saluation && !SalutationType.NONE.equals(saluation)) ? saluation.getDescription() + " " : "");
		buf.append((null != firstName) ? firstName + " " : "");
		buf.append((null != middleName) ? middleName + " " : "");
		buf.append((null != lastName) ? lastName + " " : "");
		buf.append((null != suffix) ? suffix : "");

		return buf.toString();
	}

	/**
	 * Creates the full name.
	 */
	public void createFullName() {
		fullName = getFullName();
	}

	/**
	 * Checks if person is valid.
	 *
	 * @return the boolean
	 */
	public Boolean isValid() {
		Boolean result = Boolean.FALSE;

		if (null != firstName && null != lastName) {
			result = Boolean.TRUE;
		}

		return result;
	}

	@Override
	public String toString() {
		return "Person [saluation=" + saluation + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", suffix=" + suffix + ", fullName=" + fullName + ", uuid=" + uuid + "]";
	}
}
