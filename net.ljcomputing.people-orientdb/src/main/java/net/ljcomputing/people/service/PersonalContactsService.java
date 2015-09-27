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

package net.ljcomputing.people.service;

import java.util.List;
import java.util.UUID;

import net.ljcomputing.people.entity.PersonEntity;
import net.ljcomputing.people.entity.PersonalContactsEntity;

/**
 * Interface defining a personal contacts service.
 * 
 * @author James G. Willmore
 *
 */
public interface PersonalContactsService {
	
	/**
	 * Save personal contacts.
	 *
	 * @param personalContactsEntity the personal contacts entity
	 * @return the personal contacts entity
	 */
	public PersonalContactsEntity savePersonalContacts(PersonalContactsEntity personalContactsEntity);
	
	/**
	 * Read personal contacts by uuid.
	 *
	 * @param uuidString the uuid string
	 * @return the personal contacts entity
	 */
	public PersonalContactsEntity readPersonalContactsByUuid(String uuidString);
	
	/**
	 * Read personal contacts by uuid.
	 *
	 * @param uuid the uuid
	 * @return the personal contacts entity
	 */
	public PersonalContactsEntity readPersonalContactsByUuid(UUID uuid);
	
	/**
	 * Read personal contacts.
	 *
	 * @param personEntity the person entity
	 * @return the personal contacts entity
	 */
	public PersonalContactsEntity readPersonalContacts(PersonEntity personEntity);

	/**
	 * Read personal contacts.
	 *
	 * @param peopleEntities the people entities
	 * @return the list
	 */
	public List<PersonalContactsEntity> readPersonalContacts(List<PersonEntity> peopleEntities);
}
