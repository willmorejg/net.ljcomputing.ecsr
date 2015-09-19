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
import net.ljcomputing.people.domain.Person;
import net.ljcomputing.people.entity.PersonEntity;
import net.ljcomputing.people.service.PersonService;

/**
 * Person entity repository tests.
 * 
 * @author James G. Willmore
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PeopleApplication.class)
public class PersonEntityRepositoryTest {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(PersonEntityRepositoryTest.class);

	/** The repository. */
	@Autowired
	private PersonRepository repository;

	/** The person service. */
	@Autowired
	private PersonService personService;

	/** The person 1. */
	private Person person1 = new Person();

	/** The person 2. */
	private Person person2 = new Person();

	/** The person 3. */
	private Person person3 = new Person();

	/** The entity 1. */
	private PersonEntity entity1;

	/** The entity 2. */
	private PersonEntity entity2;

	/** The entity 3. */
	private PersonEntity entity3;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		person1.setFirstName("YO!");
		person1.setLastName("Zevon");

		entity1 = new PersonEntity(person1);

		person2.setFirstName("Janice");
		person2.setLastName("Joplin");

		entity2 = new PersonEntity(person2);

		person3.setFirstName("Jim");
		person3.setLastName("Morrison");

		entity3 = new PersonEntity(person3);
	}

	@Test
	@Transactional
	public void test() throws Exception {
		//repository.deleteAll();

		repository.save(entity1);
		repository.save(entity2);
		repository.save(entity3);

		entity1.setFirstName("Warren");

		personService.update(entity1);
		personService.save(entity2);
		personService.save(entity3);

		Result<PersonEntity> set = repository.findAll();
		// List<PersonEntity> set = repository.findByFullNameLike("*Da*");

		for (PersonEntity entity : set) {
			logger.debug("------------------------->>>> entity: {}", entity.toString());
		}

		// repository.deleteAll();
	}
}
