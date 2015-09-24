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

package net.ljcomputing.people.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.ljcomputing.core.service.EntityService;
import net.ljcomputing.logging.annotation.InjectLogging;
import net.ljcomputing.logging.annotation.LogEvent;
import net.ljcomputing.logging.annotation.LogEvent.Level;
import net.ljcomputing.logging.annotation.LogResponse;
import net.ljcomputing.people.domain.Person;
import net.ljcomputing.people.entity.PersonEntity;
import net.ljcomputing.people.entity.PersonalContactsEntity;
import net.ljcomputing.people.repository.PersonRepository;
import net.ljcomputing.people.service.PersonService;
import net.ljcomputing.people.service.PersonalContactsService;

/**
 * Person controller.
 * 
 * @author James G. Willmore
 *
 */
@Controller
@RequestMapping("/people")
public class PersonController extends AbstractController {

	/** The logger. */
	@InjectLogging
	private static Logger logger;

	/** The person service. */
	@Autowired
	private PersonService personService;

	@Autowired
	private PersonalContactsService personalContactsService;

	/**
	 * Creates the person.
	 *
	 * @param person
	 *            the person
	 * @return the person
	 */
	@LogResponse
	@LogEvent(level = Level.INFO, message = "Creating Person")
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Person createPerson(@RequestBody Person person) {
		return personService.create(person);
	}

	/**
	 * Gets the all people.
	 *
	 * @param pageNumber
	 *            the page number
	 * @param pageSize
	 *            the page size
	 * @param searchText
	 *            the search text
	 * @param sortName
	 *            the sort name
	 * @param sortOrder
	 *            the sort order
	 * @return the all people
	 */
	@LogResponse
	@LogEvent(level = Level.INFO, message = "Getting all People")
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAllPeople(
			@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
			@RequestParam(required = false, defaultValue = "5") Integer pageSize,
			@RequestParam(required = false, defaultValue = "") String searchText,
			@RequestParam(required = false, defaultValue = "lastName") String sortName,
			@RequestParam(required = false, defaultValue = "asc") String sortOrder) {

		logger.debug("pageNumber: {}", pageNumber);
		logger.debug("pageSize: {}", pageSize);
		logger.debug("searchText: {}", searchText);
		logger.debug("sortName: {}", sortName);
		logger.debug("sortOrder: {}", sortOrder);

		String searchProperty = "fullName";
		Map<String, Object> response = new HashMap<String, Object>();
		List<PersonEntity> list = new ArrayList<PersonEntity>();

		if (null != sortName) {
			sortName = "personEntity." + sortName;
		}

		PageRequest page = getPageRequest(pageNumber, pageSize, sortName, sortOrder);

		if (null != searchText && !searchText.trim().equals("")) {
			if (page != null) {
				list = personService.searchLike(searchProperty, searchText, page);
				response.put("total", personService.searchLike(searchProperty, searchText).size());
			} else {
				list = personService.searchLike(searchProperty, searchText);
				response.put("total", list.size());
			}
		} else if (page != null) {
			list = personService.readAll(page);
			response.put("total", personService.getRepository().count());
		} else {
			list = personService.readAll();
			response.put("total", personService.getRepository().count());
		}

		response.put("rows", personalContactsService.readPersonalContacts(list));

		return response;
	}

	/**
	 * Gets the person.
	 *
	 * @param uuidString
	 *            the uuid string
	 * @return the person
	 */
	@LogResponse
	@LogEvent(level = Level.INFO, message = "Get Person")
	@RequestMapping(value = "/{uuidString}", method = RequestMethod.GET)
	public @ResponseBody PersonalContactsEntity getPerson(@PathVariable String uuidString) {
		PersonEntity personEntity = personService.readByUuidString(uuidString);
		return personalContactsService.readPersonalContacts(personEntity);
	}

	/**
	 * Update person.
	 *
	 * @param uuidString
	 *            the uuid string
	 * @param person
	 *            the person
	 * @return the boolean
	 */
	@LogEvent(level = Level.INFO, message = "Updating Person")
	@RequestMapping(value = "/{uuidString}", method = RequestMethod.PUT)
	public @ResponseBody PersonalContactsEntity updatePerson(@PathVariable String uuidString, @RequestBody Person person) {
		try {
			((EntityService<Person, PersonEntity, PersonRepository>) personalContactsService).update(person);
		} catch (Exception e) {
			logger.error("Exception occured while updating {}: ", person, e);
		}

		return personalContactsService.readPersonalContactsByUuid(uuidString);
	}

	/**
	 * Delete person.
	 *
	 * @param uuidString
	 *            the uuid string
	 * @return the boolean
	 */
	@LogResponse
	@LogEvent(level = Level.INFO, message = "Deleting Person")
	@RequestMapping(value = "/{uuidString}", method = RequestMethod.DELETE)
	public @ResponseBody Map<String, Object> deletePerson(@PathVariable String uuidString) {
		personService.delete(uuidString);
		PageRequest page = getPageRequest(1, 10, "lastName", "asc");
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("rows", personService.readAll(page));
		response.put("total", personService.getRepository().count());
		return response;
	}
}
