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

package net.ljcomputing.people.domain;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.ljcomputing.core.domain.AbstractDomain;

/**
 * Class encapsulating a phone number.
 * 
 * @author James G. Willmore
 *
 */
public class PhoneNumber extends AbstractDomain implements Contact {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9175755808264117329L;

	/** The regular expression patter for a valid phone number. */
	public static final String PHONE_NUMBER_PATTERN = "^\\(?([0-9]{3})\\)?[-.\\s]?[0-9]{3}[-.\\s]?[0-9]{4}?[\\sextension]{0,10}[\\d]{0,7}$";

	/** The area code. */
	private String areaCode;

	/** The prefix. */
	private String prefix;

	/** The number. */
	private String number;

	/** The extension. */
	private String extension;

	/**
	 * The full phone number. This is just an attribute of the class that is
	 * constructed from the phone number parts.
	 */
	private String fullPhoneNumber;

	/**
	 * Instantiates a new phone number.
	 */
	public PhoneNumber() {}

	/**
	 * Instantiates a new phone number.
	 *
	 * @param areaCode            the area code
	 * @param prefix            the prefix
	 * @param number            the number
	 * @param extension            the extension
	 * @param uuid the uuid
	 */
	public PhoneNumber(String areaCode, String prefix, String number, String extension, UUID uuid) {
		if(null == uuid) {
			createUuid();
		}

		setAreaCode(areaCode);
		setPrefix(prefix);
		setNumber(number);
		setExtension(extension);

		createFullPhoneNumber();
	}
	
	/**
	 * Instantiates a new phone number (copy constructor).
	 *
	 * @param phoneNumber the phone number
	 */
	public PhoneNumber(PhoneNumber phoneNumber) {
		this(phoneNumber.getAreaCode(), phoneNumber.getPrefix(), phoneNumber.getNumber(), phoneNumber.getExtension(), phoneNumber.getUuid());
	}

	/**
	 * Instantiates a new phone number.
	 *
	 * @param fullPhoneNumber the full phone number
	 */
	public PhoneNumber(String fullPhoneNumber) {
		constructFullPhoneNumber(fullPhoneNumber);
	}

	/**
	 * Gets the area code.
	 *
	 * @return the areaCode
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * Sets the area code.
	 *
	 * @param areaCode
	 *            the areaCode to set
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
		createFullPhoneNumber();
	}

	/**
	 * Gets the prefix.
	 *
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * Sets the prefix.
	 *
	 * @param prefix
	 *            the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
		createFullPhoneNumber();
	}

	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Sets the number.
	 *
	 * @param number
	 *            the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
		createFullPhoneNumber();
	}

	/**
	 * Gets the extension.
	 *
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * Sets the extension.
	 *
	 * @param extension
	 *            the extension to set
	 */
	public void setExtension(String extension) {
		this.extension = extension;
		createFullPhoneNumber();
	}

	/**
	 * Gets the phone number. The format is (areaCode) prefix-number xextension.
	 * Ex. (717) 999-7777 x1234.
	 *
	 * @return the phone number
	 */
	public String getFullPhoneNumber() {
		StringBuffer buf = new StringBuffer();

		buf.append((null != areaCode) ? "(" + areaCode + ") " : "");
		buf.append((null != prefix) ? (prefix) : "");
		buf.append((null != number) ? ("-" + number) : " ");
		buf.append((null != extension) ? (" x" + extension) : "");

		return buf.toString();
	}

	/**
	 * Creates the full phone number.
	 */
	public void createFullPhoneNumber() {
		fullPhoneNumber = getFullPhoneNumber();
	}

	/**
	 * Checks if phone number is valid.
	 *
	 * @return the boolean
	 */
	public Boolean isValid() {
		// check for a prefix and number - no point in continuing if we don't
		// have a prefix or number
		if (null == prefix || "".equals(prefix.trim()) || null == number || "".equals(number.trim()))
			return false;

		Pattern pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
		Matcher matcher = pattern.matcher(getFullPhoneNumber());
		return matcher.matches();
	}

	/**
	 * Construct full phone number.
	 *
	 * @param number
	 *            the number
	 */
	private void constructFullPhoneNumber(String number) {
		// no point in going any further if the local part is null or empty
		if (null != number && !"".equals(number.trim())) {
			number = number.replaceAll("[\\(\\)-\\.extension]", " ").replaceAll("[\\s]+", " ");

			// do a simple split on what's provided
			String[] parts = number.split(" ");

			// if we don't have enough parts, don't continue
			if (null != parts && parts.length < 5 && parts.length > 2) {
				StringBuffer buf = new StringBuffer();

				buf.append(parts[0]);
				buf.append("-");
				buf.append(parts[1]);
				buf.append("-");
				buf.append(parts[2]);

				if (parts.length == 4 && null != parts[3] && !"".equals(parts[3].trim())) {
					buf.append(" x");
					buf.append(parts[3]);
				}

				// validate what's provided - if invalid, don't continue
				Pattern pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
				Matcher matcher = pattern.matcher(buf.toString());

				if (matcher.matches()) {
					setAreaCode(parts[0]);
					setPrefix(parts[1]);
					setNumber(parts[2]);

					if (parts.length == 4 && null != parts[3] && !"".equals(parts[3].trim())) {
						setExtension(parts[3]);
					}
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.domain.AbstractDomain#toString()
	 */
	@Override
	public String toString() {
		return "PhoneNumber [areaCode=" + areaCode + ", prefix=" + prefix + ", number=" + number + ", extension="
				+ extension + ", fullPhoneNumber=" + fullPhoneNumber + ", uuid=" + uuid + "]";
	}
}
