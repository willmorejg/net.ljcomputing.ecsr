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
 * Enumeration defining a phone address type (ex. hard-line, cell, fax, etc.).
 * 
 * @author James G. Willmore
 *
 */
public enum PhoneType {

	/** A hard-line phone type. */
	HARDLINE(1, "Hard-line"), 
	/** A cellular phone type. */
	CELL(2, "Cellular"), 
	/** Phone is used primarily for faxing. */
	FAX(3, "Fax"), 
	/** A VoIP (or Internet-based, like Skype or Google Voice) phone type. */
	VOIP(4, "VoIP"), 
	/** Unknown type of phone. */
	UNKNOWN(99, "Unknown");

	/** The order. */
	private Integer order;

	/** The description. */
	private String description;

	/**
	 * Instantiates a new phone type.
	 *
	 * @param order the order
	 * @param description the description
	 */
	private PhoneType(final Integer order, final String description) {
		this.order = order;
		this.description = description;
	}

	/**
	 * Gets the order.
	 *
	 * @return the order
	 */
	public Integer getOrder() {
		return order;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the value of description.
	 *
	 * @param description the description
	 * @return the value of description
	 */
	public static PhoneType getValueOfDescription(final String description) {
		PhoneType result = PhoneType.UNKNOWN;

		for (PhoneType contactOrder : PhoneType.values()) {
			if (description.equals(contactOrder.getDescription())) {
				result = contactOrder;
			}
		}

		return result;
	}
}
