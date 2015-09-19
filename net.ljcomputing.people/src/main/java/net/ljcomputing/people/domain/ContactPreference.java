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

import net.ljcomputing.core.domain.Domain;

/**
 * Interface for a contact preference.
 *
 * @author James G. Willmore
 */
public interface ContactPreference extends Domain {
	
	/**
	 * Sets the contact type.
	 *
	 * @param contactType the new contact type
	 */
	public void setContactType(ContactType contactType);
	
	/**
	 * Gets the contact type.
	 *
	 * @return the contact type
	 */
	public ContactType getContactType();
	
	/**
	 * Sets the contact order.
	 *
	 * @param contactOrder the new contact order
	 */
	public void setContactOrder(ContactOrder contactOrder);
	
	/**
	 * Gets the contact order.
	 *
	 * @return the contact order
	 */
	public ContactOrder getContactOrder();
}
