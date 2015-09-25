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

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import net.ljcomputing.gson.converter.GsonConverterService;
import net.ljcomputing.people.PeopleApplication;
import net.ljcomputing.people.domain.ContactOrder;
import net.ljcomputing.people.domain.ContactType;
import net.ljcomputing.people.entity.EmailAddressEntity;
import net.ljcomputing.people.entity.EmailAddressPreferenceEntity;
import net.ljcomputing.people.entity.PersonEntity;
import net.ljcomputing.people.entity.PersonalContactsEntity;
import net.ljcomputing.people.service.EmailAddressService;
import net.ljcomputing.people.service.PersonService;
import net.ljcomputing.people.service.PersonalContactsService;

/**
 * Person entity repository tests.
 * 
 * @author James G. Willmore
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PeopleApplication.class)
public class PersonalContactsServiceImplTest {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(PersonalContactsServiceImplTest.class);
	
	@Autowired
	private GsonConverterService gsonConverterService;

	/** The person service. */
	@Autowired
	private PersonService personService;

	/** The personal contacts service. */
	@Autowired
	private PersonalContactsService personalContactsService;

	/** The email address service. */
	@Autowired
	private EmailAddressService emailAddressService;

	/** The entity 1. */
	private PersonEntity entity1;

	/** The email address entity1. */
	private EmailAddressEntity emailAddressEntity1;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {

	}

	/**
	 * Test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@Transactional
	public void test() throws Exception {
		logger.debug("start");
		
		PersonalContactsEntity personalContactsEntity = null;
		List<PersonEntity> personEntities = personService.readAll();
		
		for(PersonEntity personEntity : personEntities) {
			entity1 = personEntity;
			personalContactsEntity = personalContactsService.readPersonalContacts(entity1);
			logger.debug("  personalContactsEntity: {}", gsonConverterService.toJson(personalContactsEntity));
		}
		
		emailAddressEntity1 = emailAddressService.readAll().get(0);
		
		personalContactsEntity.addEmailPreference(emailAddressEntity1, ContactOrder.PRIMARY, ContactType.BUSINESS);
		
		logger.debug("saved as : {}", gsonConverterService.toJson(personalContactsService.savePersonalContacts(personalContactsEntity)));
		
		logger.debug(" before: {}", personalContactsEntity.getEmailPreferences().size());
		personalContactsEntity.removeEmailPreference((EmailAddressPreferenceEntity) (personalContactsEntity.getEmailPreferences()).toArray()[0]);
		logger.debug(" after: {}", personalContactsEntity.getEmailPreferences().size());
		
		logger.debug("saved as : {}", gsonConverterService.toJson(personalContactsService.savePersonalContacts(personalContactsEntity)));

		logger.debug(" after save: {}", personalContactsService.readPersonalContacts(entity1).getEmailPreferences().size());

		assertTrue(true);

		logger.debug("end");
	}
}
