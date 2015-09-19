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
 * Enumeration defining the order in which the person is contacted.
 * 
 * @author James G. Willmore
 *
 */
public enum ContactOrder {

	/** Primary contact order. */
	PRIMARY(1, "Primary"), 
	/** Secondary contact order. */
	SECONDARY(2, "Secondary"), 
	/** Tertiary contact order. */
	TERTIARY(3, "Tertiary"), 
	/** Unknown contact order. */
	UNKNOWN(99, "Unknown");

	/** The order of contact. */
	private Integer order;

	/** The description of the contact order. */
	private String description;

	/**
	 * Instantiates a new contact order.
	 *
	 * @param order
	 *            the order
	 * @param description
	 *            the description
	 */
	private ContactOrder(final Integer order, final String description) {
		this.order = order;
		this.description = description;
	}

	/**
	 * Gets the order of contact.
	 *
	 * @return the order
	 */
	public Integer getOrder() {
		return order;
	}

	/**
	 * Gets the description of the contact order.
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
	public static ContactOrder getValueOfDescription(final String description) {
		ContactOrder result = ContactOrder.UNKNOWN;

		for (ContactOrder contactOrder : ContactOrder.values()) {
			if (description.equals(contactOrder.getDescription())) {
				result = contactOrder;
			}
		}

		return result;
	}
}
