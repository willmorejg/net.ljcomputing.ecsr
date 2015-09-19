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

import net.ljcomputing.people.domain.PhoneNumber;

/**
 * PhoneNumber JUnit tests.
 * 
 * @author James G. Willmore
 */
public class PhoneNumberTests {
	private static Logger logger = LoggerFactory.getLogger(PhoneNumberTests.class);

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
		PhoneNumber a = new PhoneNumber();
		a.setAreaCode("717");
		a.setPrefix("111");
		a.setNumber("2222");
		a.setExtension("99999999");

		PhoneNumber b = new PhoneNumber();
		b.setAreaCode("717");
		b.setPrefix("111");
		b.setNumber("2222");
		b.setExtension("1");

		PhoneNumber c = new PhoneNumber();
		c.setAreaCode("717");
		c.setPrefix("111");
		c.setNumber("222");

		List<PhoneNumber> list = new ArrayList<PhoneNumber>();
		list.add(a);
		list.add(b);
		list.add(c);

		logger.debug(list.toString());

		for (PhoneNumber phoneNumber : list) {
			logger.debug("  ");
			logger.debug("  {}", phoneNumber.getFullPhoneNumber());
			logger.debug("  isValid: {}", phoneNumber.isValid());
		}

		assertTrue(true);

	}
}
