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
 * Class encapsulating an email address.
 * 
 * @author James G. Willmore
 *
 */
public class EmailAddress extends AbstractDomain implements Contact {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6191388396493574415L;

	/** The regular expression patter for a valid email address. */
	public static final String EMAIL_ADDRESS_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/** The local part of the email address. */
	private String localPart;

	/** The domain part of the email address. */
	private String domain;

	/** The full email address. This is just an attribute of the class that is constructed from the email address parts. */
	private String fullEmailAddress;

	/**
	 * Instantiates a new email address.
	 */
	public EmailAddress() {}

	/**
	 * Instantiates a new email address.
	 *
	 * @param localPart            the local part
	 * @param domain            the domain
	 * @param uuid the uuid
	 */
	public EmailAddress(String localPart, String domain, UUID uuid) {
		if(null == uuid) {
			createUuid();
		}

		setLocalPart(localPart);
		setDomain(domain);

		createFullEmailAddress();
	}
	
	/**
	 * Instantiates a new email address (copy constructor).
	 *
	 * @param emailAddress the email address
	 */
	public EmailAddress(EmailAddress emailAddress) {
		this(emailAddress.getLocalPart(), emailAddress.getDomain(), emailAddress.getUuid());
	}

	/**
	 * Instantiates a new email address.
	 *
	 * @param fullEmailAddress the full email address
	 */
	public EmailAddress(String fullEmailAddress) {
		constructFullEmailAddress(fullEmailAddress);
	}

	/**
	 * Gets the local part.
	 *
	 * @return the local part
	 */
	public String getLocalPart() {
		return localPart;
	}

	/**
	 * Sets the local part.
	 *
	 * @param localPart
	 *            the new local part
	 */
	public void setLocalPart(String localPart) {
		this.localPart = localPart;
		createFullEmailAddress();
	}

	/**
	 * Gets the domain.
	 *
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * Sets the domain.
	 *
	 * @param domain
	 *            the new domain
	 */
	public void setDomain(String domain) {
		this.domain = domain;
		createFullEmailAddress();
	}

	/**
	 * Gets the full email address.
	 *
	 * @return the full email address
	 */
	public String getFullEmailAddress() {
		StringBuffer buf = new StringBuffer();

		buf.append((null != localPart) ? localPart : "");
		buf.append((null != domain) ? "@" + domain : "");

		return buf.toString();
	}

	/**
	 * Creates the full email address.
	 */
	public void createFullEmailAddress() {
		fullEmailAddress = getFullEmailAddress();
	}

	/**
	 * Checks if email address is valid.
	 *
	 * @return the boolean
	 */
	public Boolean isValid() {
		// no point in checking any further if the local part is null or empty
		if (null == localPart || "".equals(localPart.trim()))
			return false;

		Pattern pattern = Pattern.compile(EMAIL_ADDRESS_PATTERN);
		Matcher matcher = pattern.matcher(getFullEmailAddress());
		return matcher.matches();
	}

	/**
	 * Construct full email address.
	 *
	 * @param address the address
	 */
	private void constructFullEmailAddress(String address) {
		// no point in going any further if the address provided is null or empty
		if (null != address && !"".equals(address.trim())) {

			//do a simple split on what's provided
			String[] parts = address.split("@");
			
			//if we don't have enough parts, don't continue
			if(null != parts && parts.length == 2) {
				
				//validate what's provided - if invalid, don't continue
				Pattern pattern = Pattern.compile(EMAIL_ADDRESS_PATTERN);
				Matcher matcher = pattern.matcher(parts[0] + "@" + parts[1]);
				
				if (matcher.matches()) {
					setLocalPart(parts[0]);
					setDomain(parts[1]);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.domain.AbstractDomain#toString()
	 */
	@Override
	public String toString() {
		return "EmailAddress [localPart=" + localPart + ", domain=" + domain + ", fullEmailAddress=" + fullEmailAddress
				+ ", uuid=" + uuid + "]";
	}
}
