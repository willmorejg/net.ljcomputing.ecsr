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

/**
 * Enumeration defining the type of contact.
 * 
 * @author James G. Willmore
 *
 */
public enum ContactType {

	/** Business contact type. */
	BUSINESS(1, "Business"), 
	/** Personal contact type. */
	PERSONAL(2, "Personal"), 
	/** Unknown contact type. */
	UNKNOWN(99, "Unknown");

	/** The order of contact type. */
	private Integer order;

	/** The description of the contact type. */
	private String description;

	/**
	 * Instantiates a new contact type.
	 *
	 * @param order
	 *            the order
	 * @param description
	 *            the description
	 */
	private ContactType(final Integer order, final String description) {
		this.order = order;
		this.description = description;
	}

	/**
	 * Gets the order of contact type.
	 *
	 * @return the order
	 */
	public Integer getOrder() {
		return order;
	}

	/**
	 * Gets the description of the contact type.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the value of description.
	 *
	 * @param description
	 *            the description
	 * @return the value of description
	 */
	public static ContactType getValueOfDescription(final String description) {
		ContactType result = ContactType.UNKNOWN;

		for (ContactType contactType : ContactType.values()) {
			if (description.equals(contactType.getDescription())) {
				result = contactType;
			}
		}

		return result;
	}
}
