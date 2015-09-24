package net.ljcomputing.rhino.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.ljcomputing.people.PeopleApplication;
import net.ljcomputing.people.domain.Person;
import net.ljcomputing.rhino.service.ObjectToJsService;

/**
 * Object to JavaScript service test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PeopleApplication.class)
public class ObjectToJsServiceImplTest {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(ObjectToJsServiceImplTest.class);

	/** The object to js service. */
	@Autowired
	private ObjectToJsService objectToJsService;

	/**
	 * Test Object to JavaScript.
	 */
	@Test
	public void testToJS() {
		logger.debug("start");

		Person person = new Person();
		person.setFirstName("Jim");
		person.setMiddleName("George");
		person.setLastName("Willmore");

		logger.debug(objectToJsService.toJS(person).toString());
		
		logger.debug("end");
	}

}
