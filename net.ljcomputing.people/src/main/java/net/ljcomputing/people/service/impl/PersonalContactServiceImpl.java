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

package net.ljcomputing.people.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ljcomputing.people.entity.PersonEntity;
import net.ljcomputing.people.entity.PersonalContactsEntity;
import net.ljcomputing.people.service.PersonService;
import net.ljcomputing.people.service.PersonalContactsService;

/**
 * Implementation of the personal contacts service.
 * 
 * @author James G. Willmore
 *
 */
@Service("personalContactsService")
public class PersonalContactServiceImpl implements PersonalContactsService {

	/** The Neo4J template. */
	@Autowired
	private Neo4jTemplate template;

	/** The person service. */
	@Autowired
	private PersonService personService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ljcomputing.people.service.PersonalContactsService#
	 * savePersonalContacts(net.ljcomputing.people.entity.
	 * PersonalContactsEntity)
	 */
	public PersonalContactsEntity savePersonalContacts(PersonalContactsEntity personalContactsEntity) {
		template.save(personalContactsEntity);
		return readPersonalContacts(personalContactsEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ljcomputing.people.service.PersonalContactsService#
	 * readPersonalContactsByUuid(java.lang.String)
	 */
	public PersonalContactsEntity readPersonalContactsByUuid(String uuidString) {
		return readPersonalContacts(personService.findByUuidString(uuidString));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ljcomputing.people.service.PersonalContactsService#
	 * readPersonalContactsByUuid(java.util.UUID)
	 */
	public PersonalContactsEntity readPersonalContactsByUuid(UUID uuid) {
		return readPersonalContacts(personService.findByUuid(uuid));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ljcomputing.people.service.PersonalContactsService#
	 * readPersonalContacts(net.ljcomputing.people.entity.PersonEntity)
	 */
	@Transactional
	public PersonalContactsEntity readPersonalContacts(PersonEntity personEntity) {
		PersonalContactsEntity personalContactsEntity = new PersonalContactsEntity(personEntity);
		template.fetch(personalContactsEntity);
		return personalContactsEntity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ljcomputing.people.service.PersonalContactsService#
	 * readPersonalContacts(java.util.List)
	 */
	public List<PersonalContactsEntity> readPersonalContacts(List<PersonEntity> peopleEntities) {
		List<PersonalContactsEntity> list = new ArrayList<PersonalContactsEntity>();

		if (null != peopleEntities) {
			ListIterator<PersonEntity> it = peopleEntities.listIterator();

			while (it.hasNext()) {
				list.add(readPersonalContacts(it.next()));
			}
		}

		return list;
	}
}
