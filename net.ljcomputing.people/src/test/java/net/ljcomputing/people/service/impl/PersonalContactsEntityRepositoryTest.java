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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import net.ljcomputing.people.PeopleApplication;
import net.ljcomputing.people.domain.ContactOrder;
import net.ljcomputing.people.domain.ContactType;
import net.ljcomputing.people.entity.EmailAddressEntity;
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
public class PersonalContactsEntityRepositoryTest {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(PersonalContactsEntityRepositoryTest.class);

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

	@Test
	@Transactional
	public void test() throws Exception {
		entity1 = personService.findAll().get(0);
		PersonalContactsEntity personalContactsEntity = personalContactsService.readPersonalContacts(entity1);
		emailAddressEntity1 = emailAddressService.findAll().get(0);
		personalContactsEntity.addEmailPreference(emailAddressEntity1, ContactOrder.PRIMARY, ContactType.BUSINESS);
		logger.debug("saved as : {}", personalContactsService.savePersonalContacts(personalContactsEntity));
	}
}
