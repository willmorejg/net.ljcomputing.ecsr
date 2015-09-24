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

package net.ljcomputing.people.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.ljcomputing.logging.annotation.InjectLogging;
import net.ljcomputing.logging.annotation.LogEvent;
import net.ljcomputing.logging.annotation.LogEvent.Level;
import net.ljcomputing.logging.annotation.LogResponse;
import net.ljcomputing.people.domain.EmailAddress;
import net.ljcomputing.people.entity.EmailAddressEntity;
import net.ljcomputing.people.service.EmailAddressService;

/**
 * Email address address controller.
 * 
 * @author James G. Willmore
 *
 */
@Controller
@RequestMapping("/emails")
public class EmailAddressController {

	/** The logger. */
	@InjectLogging
	private static Logger logger;

	/** The EmailAddress service. */
	@Autowired
	private EmailAddressService emailAddressService;

	/**
	 * Gets the all email address addresses.
	 *
	 * @return the all email address addresses
	 */
	@LogResponse
	@LogEvent(level = Level.INFO, message = "Getting all Email Addresses")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody List<EmailAddressEntity> getAllEmailAddressAddresses() {
		return emailAddressService.readAll();
	}

	/**
	 * Gets the email address.
	 *
	 * @param uuidString
	 *            the uuid string
	 * @return the email address
	 */
	@LogResponse
	@LogEvent(level = Level.INFO, message = "Getting Email Address")
	@RequestMapping(value = "/{uuidString}", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody EmailAddressEntity getEmailAddress(@PathVariable String uuidString) {
		return emailAddressService.readByUuidString(uuidString);
	}

	/**
	 * Creates the email address.
	 *
	 * @param personalEmailContactEntity
	 *            the personal email contact entity
	 * @return the boolean
	 */
//	@LogEvent(level = Level.INFO, message = "Creating EmailAddress")
//	@RequestMapping(value = "/", method = RequestMethod.POST, produces = { "application/json" }, consumes = {
//			"application/json" })
//	public @ResponseBody EmailAddress createEmailAddress(
//			@RequestBody PersonalEmailAddressContactPreferenceEntity personalEmailContactEntity) {
//		logger.debug("emailAddress: {}", personalEmailContactEntity);
//		return emailAddressService.save((EmailAddress) personalEmailContactEntity.getContact());
//	}

	/**
	 * Update email address.
	 *
	 * @param uuidString
	 *            the uuid string
	 * @param emailAddress
	 *            the email address
	 * @return the boolean
	 */
	@LogEvent(level = Level.INFO, message = "Updating EmailAddress")
	@RequestMapping(value = "/{uuidString}", method = RequestMethod.POST, produces = {
			"application/json" }, consumes = { "application/json" })
	public @ResponseBody EmailAddress updateEmailAddress(@PathVariable String uuidString,
			@RequestBody EmailAddress emailAddress) {
		return emailAddressService.update(emailAddress);
	}

	/**
	 * Delete email address.
	 *
	 * @param uuidString
	 *            the uuid string
	 * @return the boolean
	 */
	@LogEvent(level = Level.INFO, message = "Deleting EmailAddress")
	@RequestMapping(value = "/{uuidString}", method = RequestMethod.DELETE, produces = { "application/json" })
	public @ResponseBody Boolean deleteEmailAddress(@PathVariable String uuidString) {
		emailAddressService.delete(uuidString);
		return true;
	}
}
