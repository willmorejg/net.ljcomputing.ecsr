package net.ljcomputing.people.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * JUnit test suite.
 */
@RunWith(Suite.class)
@SuiteClasses({ EmailAddressTests.class, MailingAddressTests.class, PersonTests.class, PhoneNumberTests.class })
public class AllTests {

}
