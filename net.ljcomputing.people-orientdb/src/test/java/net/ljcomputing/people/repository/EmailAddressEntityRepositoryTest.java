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

import static org.junit.Assert.assertTrue;

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
import net.ljcomputing.people.domain.EmailAddress;
import net.ljcomputing.people.entity.EmailAddressEntity;

/**
 * Email address entity repository tests.
 * 
 * @author James G. Willmore
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PeopleApplication.class)
public class EmailAddressEntityRepositoryTest {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(EmailAddressEntityRepositoryTest.class);

	/** The repository. */
	@Autowired
	private EmailAddressRepository repository;

	/** The email1. */
	private EmailAddress email1 = new EmailAddress();

	/** The email2. */
	private EmailAddress email2 = new EmailAddress();

	/** The email3. */
	private EmailAddress email3 = new EmailAddress();

	/** The entity 1. */
	private EmailAddressEntity entity1 = new EmailAddressEntity();

	/** The entity 2. */
	private EmailAddressEntity entity2 = new EmailAddressEntity();

	/** The entity 3. */
	private EmailAddressEntity entity3 = new EmailAddressEntity();

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		email1.setLocalPart("jim");
		email1.setDomain("localhost");
		
		entity1 = new EmailAddressEntity(email1);

		email2.setLocalPart("sally.combs");
		email2.setDomain("zepher.net");

		entity2 = new EmailAddressEntity(email2);

		email3.setLocalPart("eddie");
		email3.setDomain("bam");

		entity3 = new EmailAddressEntity(email3);
	}

	@Test
	@Transactional
	public void test() {
		repository.deleteAll();

		repository.save(entity1);
		repository.save(entity2);
		repository.save(entity3);
		
		entity1.setDomain("somewhere.net");
		repository.save(entity1);

		Iterable<EmailAddressEntity> set = repository.findAll();

		for (EmailAddressEntity entity : set) {
			logger.debug("------------------------->>>> entity: {}", entity.toString());
		}

		assertTrue(true);
	}

}
