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
 * Enumeration defining a mailing address type (ex. physical or postal).
 * 
 * @author James G. Willmore
 *
 */
public enum MailingAddressType {

	/** Mailing address represents all types of mailing address types. */
	ALL(1, "Physical and Mailing"), 
	/** Mailing address represents a physical mailing address type only. */
	PHYSICAL(2, "Physical"), 
	/** Mailing address represents a postal mailing address type only. */
	POSTAL(3, "Postal"), 
	/** Mailing address type is unknown. */
	UNKNOWN(99, "Unknown");

	/** The order. */
	private Integer order;

	/** The description. */
	private String description;

	/**
	 * Instantiates a new mailing address type.
	 *
	 * @param order the order
	 * @param description the description
	 */
	private MailingAddressType(final Integer order, final String description) {
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
	public static MailingAddressType getValueOfDescription(final String description) {
		MailingAddressType result = MailingAddressType.UNKNOWN;

		for (MailingAddressType contactOrder : MailingAddressType.values()) {
			if (description.equals(contactOrder.getDescription())) {
				result = contactOrder;
			}
		}

		return result;
	}
}
