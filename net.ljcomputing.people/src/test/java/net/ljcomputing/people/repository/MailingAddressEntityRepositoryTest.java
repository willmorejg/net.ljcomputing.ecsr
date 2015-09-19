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

package net.ljcomputing.people.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import net.ljcomputing.people.PeopleApplication;
import net.ljcomputing.people.domain.MailingAddress;
import net.ljcomputing.people.domain.State;
import net.ljcomputing.people.entity.MailingAddressEntity;

/**
 * Postal address entity repository tests.
 * 
 * @author James G. Willmore
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PeopleApplication.class)
public class MailingAddressEntityRepositoryTest {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(MailingAddressEntityRepositoryTest.class);

	/** The repository. */
	@Autowired
	private MailingAddressRepository repository;

	/** The address1. */
	private MailingAddress address1 = new MailingAddress();

	/** The address2. */
	private MailingAddress address2 = new MailingAddress();

	/** The address3. */
	private MailingAddress address3 = new MailingAddress();

	/** The entity 1. */
	private MailingAddressEntity entity1 = new MailingAddressEntity();

	/** The entity 2. */
	private MailingAddressEntity entity2 = new MailingAddressEntity();

	/** The entity 3. */
	private MailingAddressEntity entity3 = new MailingAddressEntity();

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		address1.setAddressLine1("123 Salem Road");
		address1.setCity("Salem");
		address1.setState(State.MASSACHUSETTS);
		address1.setZipCode("12345");
		
		entity1 = new MailingAddressEntity(address1);

		address2.setAddressLine1("1560 Detters Mill Road");
		address2.setCity("Dover");
		address2.setState(State.PENNSYLVANIA);
		address2.setZipCode("17315");
		address2.setZipCodePlus4("2812");

		entity2 = new MailingAddressEntity(address2);

		address3.setAddressLine1("1313 Mockingbird Lane");
		address3.setAddressLine2("Box 2");
		address3.setCity("Norfolk");
		address3.setState(State.VIRGINIA);
		address3.setZipCode("98765");
		
		entity3 = new MailingAddressEntity(address3);
	}

	@Test
	@Transactional
	public void test() {
		//repository.deleteAll();

		repository.save(entity1);
		repository.save(entity2);
		repository.save(entity3);

		Result<MailingAddressEntity> set = repository.findAll();

		for (MailingAddressEntity entity : set) {
			logger.debug("------------------------->>>> entity: {}", entity.toString());
		}

		// repository.deleteAll();
	}

}
