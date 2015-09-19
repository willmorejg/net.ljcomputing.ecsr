package net.ljcomputing.people.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.ljcomputing.people.PeopleApplication;
import net.ljcomputing.people.domain.Person;
import net.ljcomputing.people.domain.SalutationType;
import net.ljcomputing.people.service.PersonService;

/**
 * Person service implementation test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PeopleApplication.class)
public class PersonServiceImplTest {
	
	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(PersonServiceImplTest.class);

	/** The person. */
	private Person person;

	/** The person service. */
	@Autowired
	private PersonService personService;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		person = new Person(SalutationType.MR, "Test", null, "Testy", null, null);
	}

	/**
	 * Test.
	 */
	@Test
	public void test() {
		personService.deleteAll();
		
		person = personService.save(person);

		for (Person p : personService.findAll()) {
			logger.debug("person: {}", p.toString());
		}

		logger.debug("person by uuid: {}", personService.findByUuid(person.getUuid()).toString());

		person.setMiddleName("George");
		person = personService.update(person);
		logger.debug("person by uuid - after update: {}", personService.findByUuid(person.getUuid()).toString());

		personService.delete(person);
		logger.debug("  after delete");
		for (Person p : personService.findAll()) {
			logger.debug("person: {}", p.toString());
		}
	}
}
