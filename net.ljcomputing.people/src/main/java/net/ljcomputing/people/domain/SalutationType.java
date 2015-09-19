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
 * Enumeration defining a salutation type (ex. Mr., Mrs., Ms.).
 * 
 * @author James G. Willmore
 *
 */
public enum SalutationType {
	
	/** None, other, or unknown */
	NONE(0, ""),
	/** Mister (Mr.) */
	MR(1, "Mr."),
	/** Missis (Mrs.) */
	MRS(1, "Mrs."),
	/** Miss */
	MISS(1, "Miss"),
	/** The ms. */
	MS(1, "Ms."),
	/** Doctor (Dr.) */
	DR(1, "Dr."),
	/** Professor (Prof.) */
	PROF(1, "Prof."),
	/** Reverand (Rev.) */
	REV(1, "Rev.");

	/** The order. */
	private Integer order;

	/** The description. */
	private String description;

	/**
	 * Instantiates a new salutation type.
	 *
	 * @param order the order
	 * @param description the description
	 */
	private SalutationType(final Integer order, final String description) {
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
	public static SalutationType getValueOfDescription(final String description) {
		SalutationType result = SalutationType.NONE;

		for (SalutationType contactOrder : SalutationType.values()) {
			if (description.equals(contactOrder.getDescription())) {
				result = contactOrder;
			}
		}

		return result;
	}
}
