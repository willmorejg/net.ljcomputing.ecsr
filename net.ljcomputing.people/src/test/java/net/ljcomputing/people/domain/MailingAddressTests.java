/**
           Copyright 2015 James G. Willmore

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

package net.ljcomputing.people.domain;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.OutputCapture;

import net.ljcomputing.people.domain.MailingAddress;
import net.ljcomputing.people.domain.State;

/**
 * PostalAddress JUnit tests.
 * 
 * @author James G. Willmore
 */
public class MailingAddressTests {
	private static Logger logger = LoggerFactory.getLogger(MailingAddressTests.class);

	@Rule
	public OutputCapture outputCapture = new OutputCapture();

	private String profiles;

	@Before
	public void init() {
		this.profiles = System.getProperty("spring.profiles.active");
	}

	@After
	public void after() {
		if (this.profiles != null) {
			System.setProperty("spring.profiles.active", this.profiles);
		} else {
			System.clearProperty("spring.profiles.active");
		}
	}

	@Test
	public void phoneNumberList() {

		MailingAddress a = new MailingAddress();
		a.setAddressLine1("123 Mocking Bird Lane");
		a.setAddressLine2("P.O. Box 123");
		a.setCity("Salem");
		a.setState(State.MASSACHUSETTS);
		a.setZipCode("12341");
		a.setZipCodePlus4("6788");

		MailingAddress b = new MailingAddress();
		b.setAddressLine1("123 Mocking Bird Lane");
		b.setCity("Salem");
		b.setState(State.MASSACHUSETTS);
		b.setZipCode("12341");
		b.setZipCodePlus4("6788");

		MailingAddress c = new MailingAddress();
		c.setAddressLine1("123 Mocking Bird Lane");
		c.setAddressLine2("P.O. Box 123");
		c.setCity("Salem");
		c.setState(State.MASSACHUSETTS);
		c.setZipCode("12341");

		MailingAddress d = new MailingAddress();
		d.setAddressLine1("123 Mocking Bird Lane");
		d.setCity("Salem");
		d.setState(State.MASSACHUSETTS);
		d.setZipCode("12341");

		/*
		 * MailingAddress e = new MailingAddress(); e.setAddressLine1(
		 * "123 Mocking Bird Lane"); e.setAddressLine2("P.O. Box 123");
		 * e.setCity("Salem"); e.setState(State.MASSACHUSETTS);
		 * e.setZipCode("12341"); e.setZipCodePlus4("6788");
		 */

		List<MailingAddress> list = new ArrayList<MailingAddress>();
		list.add(a);
		list.add(b);
		list.add(c);
		list.add(d);

		logger.debug(list.toString());

		for (MailingAddress postalAddress : list) {
			logger.debug("  ");
			MailingAddress test = new MailingAddress(postalAddress.getFullPostalAddress());
			logger.debug("  {}", test.getFullPostalAddress());
		}

		String testCaseString = "123 Mocking Bird Lane,Salem,MA,12345";
		MailingAddress testCase = new MailingAddress(testCaseString);
		logger.debug("test case: {}", testCase.getFullPostalAddress());
		logger.debug("test case valid: {}", testCase.isValid());

		assertTrue(true);

	}
}
