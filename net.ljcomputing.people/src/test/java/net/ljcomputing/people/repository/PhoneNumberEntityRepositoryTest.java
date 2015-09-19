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
import net.ljcomputing.people.domain.PhoneNumber;
import net.ljcomputing.people.entity.PhoneNumberEntity;

/**
 * Phone number entity repository tests.
 * 
 * @author James G. Willmore
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PeopleApplication.class)
public class PhoneNumberEntityRepositoryTest {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(PhoneNumberEntityRepositoryTest.class);

	/** The repository. */
	@Autowired
	private PhoneNumberRepository repository;

	private PhoneNumber phone1 = new PhoneNumber();

	/** The phone2. */
	private PhoneNumber phone2 = new PhoneNumber();

	/** The phone3. */
	private PhoneNumber phone3 = new PhoneNumber();

	/** The entity 1. */
	private PhoneNumberEntity entity1 = new PhoneNumberEntity();

	/** The entity 2. */
	private PhoneNumberEntity entity2 = new PhoneNumberEntity();

	/** The entity 3. */
	private PhoneNumberEntity entity3 = new PhoneNumberEntity();

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		phone1.setAreaCode("908");
		phone1.setPrefix("687");
		phone1.setNumber("5715");
		
		entity1 = new PhoneNumberEntity(phone1);

		phone2.setAreaCode("908");
		phone2.setPrefix("687");
		phone2.setNumber("7890");

		entity2 = new PhoneNumberEntity(phone2);

		phone3.setAreaCode("717");
		phone3.setPrefix("308");
		phone3.setNumber("9999");
		phone3.setExtension("123");

		entity3 = new PhoneNumberEntity(phone3);
	}

	@Test
	@Transactional
	public void test() {
		//repository.deleteAll();

		repository.save(entity1);
		repository.save(entity2);
		repository.save(entity3);

		Result<PhoneNumberEntity> set = repository.findAll();

		for (PhoneNumberEntity entity : set) {
			logger.debug("------------------------->>>> entity: {}", entity.toString());
		}

		// repository.deleteAll();
	}

}
