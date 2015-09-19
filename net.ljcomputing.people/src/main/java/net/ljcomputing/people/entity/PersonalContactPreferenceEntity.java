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

import net.ljcomputing.core.entity.Entity;
import net.ljcomputing.core.entity.EntityRelationship;

/**
 * Interface defining a personal contact preference (ex. a person's primary
 * business email address).
 *
 * @author James G. Willmore
 * @param <S> the generic type
 * 
 */
public interface PersonalContactPreferenceEntity<S extends Entity> extends EntityRelationship<PersonEntity, S> {
	
	/**
	 * Sets the person.
	 *
	 * @param person the new person
	 */
	public void setPerson(PersonEntity person);

	/**
	 * Gets the person.
	 *
	 * @return the person
	 */
	public PersonEntity getPerson();

	/**
	 * Sets the contact.
	 *
	 * @param contact the new contact
	 */
	public void setContact(S contact);

	/**
	 * Gets the contact.
	 *
	 * @return the contact
	 */
	public S getContact();
}
