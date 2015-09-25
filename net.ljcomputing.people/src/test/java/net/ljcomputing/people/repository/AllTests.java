package net.ljcomputing.people.repository;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * JUnit test suite.
 */
@RunWith(Suite.class)
@SuiteClasses({ EmailAddressEntityRepositoryTest.class, MailingAddressEntityRepositoryTest.class,
		PersonEntityRepositoryTest.class, PhoneNumberEntityRepositoryTest.class })
public class AllTests {

}
