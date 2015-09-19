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

import com.google.common.primitives.Ints;

import net.ljcomputing.core.domain.AbstractDomain;

/**
 * @author James G. Willmore
 *
 */
public class MailingAddress extends AbstractDomain implements Contact {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2013080636269652273L;

	/** The address line1. */
	private String addressLine1;

	/** The address line2. */
	private String addressLine2;

	/** The city. */
	private String city;

	/** The state - default is UNKNOWN. */
	private State state = State.UNKNOWN;

	/** The zip code. */
	private String zipCode;

	/** The zip code plus4. */
	private String zipCodePlus4;

	/**
	 * The full mailing address. This is just an attribute of the class that is
	 * constructed from the mailing address parts.
	 */
	private String fullMailingAddress;

	/**
	 * Instantiates a new mailing address.
	 */
	public MailingAddress() {}

	/**
	 * Instantiates a new mailing address.
	 *
	 * @param addressLine1 the address line1
	 * @param addressLine2 the address line2
	 * @param city the city
	 * @param state the state
	 * @param zipCode the zip code
	 * @param zipCodePlus4 the zip code plus4
	 * @param uuid the uuid
	 */
	public MailingAddress(String addressLine1, String addressLine2, String city, State state, String zipCode,
			String zipCodePlus4, UUID uuid) {
		if (null == uuid) {
			createUuid();
		}

		setAddressLine1(addressLine1);
		setAddressLine2(addressLine2);
		setCity(city);
		setState(state);
		setZipCode(zipCode);
		setZipCodePlus4(zipCodePlus4);

		createFullMailingAddress();
	}

	/**
	 * Instantiates a new mailing address.
	 *
	 * @param mailingAddress the mailing address
	 */
	public MailingAddress(MailingAddress mailingAddress) {
		this(mailingAddress.getAddressLine1(), mailingAddress.getAddressLine2(), mailingAddress.getCity(),
				mailingAddress.getState(), mailingAddress.getZipCode(), mailingAddress.getZipCodePlus4(),
				mailingAddress.getUuid());
	}
	
	/**
	 * Instantiates a new mailing address.
	 *
	 * @param fullMailingAddress the full mailing address
	 */
	public MailingAddress(String fullMailingAddress) {
		constructFullMailingAddress(fullMailingAddress);
	}

	/**
	 * Gets the address line1.
	 *
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * Sets the address line1.
	 *
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * Gets the address line2.
	 *
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * Sets the address line2.
	 *
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * Gets the zip code.
	 *
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * Sets the zip code.
	 *
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * Gets the zip code plus4.
	 *
	 * @return the zipCodePlus4
	 */
	public String getZipCodePlus4() {
		return zipCodePlus4;
	}

	/**
	 * Sets the zip code plus4.
	 *
	 * @param zipCodePlus4 the zipCodePlus4 to set
	 */
	public void setZipCodePlus4(String zipCodePlus4) {
		this.zipCodePlus4 = zipCodePlus4;
	}

	/**
	 * Gets the full postal address. The format is in the following format:
	 * addressLine1, addressLine2, city, state, zipCode-zipCodePlus4.
	 * 
	 * Ex. 123 Mockingbird Lane, Box 5, Salem, MA, 12345-6789.
	 *
	 * @return the full postal address
	 */
	public String getFullPostalAddress() {
		StringBuffer buf = new StringBuffer();

		buf.append((null != addressLine1) ? addressLine1 : "");
		buf.append((null != addressLine2) ? (", " + addressLine2) : "");
		buf.append((null != city) ? (", " + city) : "");
		buf.append((null != state) ? (", " + state.getAbbreviation()) : "");
		buf.append((null != zipCode) ? (", " + zipCode) : "");
		buf.append((null != zipCodePlus4) ? ("-" + zipCodePlus4) : "");

		return buf.toString();
	}

	/**
	 * Creates the full mailing address.
	 */
	public void createFullMailingAddress() {
		fullMailingAddress = getFullPostalAddress();
	}
	
	/**
	 * Checks if mailing address is valid.
	 *
	 * @return the boolean
	 */
	public Boolean isValid() {
		Boolean result = Boolean.FALSE;
		
		if(null != getAddressLine1() && null != getCity() && (null != getState() && !State.UNKNOWN.equals(getState())) && null != getZipCode()) {
			result = Boolean.TRUE;
		}

		return result;
	}
	
	/**
	 * Construct full mailing address.
	 *
	 * @param address the address
	 */
	private void constructFullMailingAddress(String address) {
		// no point in going any further if the address provided is null or empty
		if (null != address && !"".equals(address.trim())) {
			String parts[] = address.split("[,-]");
			if(null != parts) {
				setAddressLine1(parts[0].trim());
				
				if(parts.length == 6) {
					setAddressLine2(parts[1].trim());
					setCity(parts[2].trim());
					setState(determineState(parts[3].trim()));
					setZipCode(validateZipCode(parts[4]) ? parts[4].trim() : null);
					setZipCodePlus4(validateZipCodePlus4(parts[5]) ? parts[5].trim() : null);
				} else if(parts.length == 4) {
					setCity(parts[1].trim());
					setState(determineState(parts[2].trim()));
					setZipCode(validateZipCode(parts[3]) ? parts[3].trim() : null);
				} else if(parts.length == 5) {
					if(null != Ints.tryParse(parts[3].trim())) {
						setCity(parts[1].trim());
						setState(determineState(parts[2].trim()));
						setZipCode(validateZipCode(parts[3]) ? parts[3].trim() : null);
						setZipCodePlus4(validateZipCodePlus4(parts[4]) ? parts[4].trim() : null);
					} else {
						setAddressLine2(parts[1].trim());
						setCity(parts[2].trim());
						setState(determineState(parts[3].trim()));
						setZipCode(validateZipCode(parts[4]) ? parts[4].trim() : null);
					}
				}
			}
		}
	}
	
	/**
	 * Determine State from provided String.
	 *
	 * @param stateString the state string
	 * @return the state
	 */
	private State determineState(String stateString) {
		State result = State.valueOfAbbreviation(stateString);
		
		if(State.UNKNOWN.equals(result)) {
			result = State.valueOfName(stateString);
		}
		
		return result;
	}
	
	/**
	 * Validate zip code.
	 *
	 * @param zipCode the zip code
	 * @return true, if successful
	 */
	private boolean validateZipCode(String zipCode) {
		return (null != zipCode && !zipCode.trim().equals("") && zipCode.trim().length() == 5);
	}
	
	/**
	 * Validate zip code plus4.
	 *
	 * @param zipCodePlus4 the zip code plus4
	 * @return true, if successful
	 */
	private boolean validateZipCodePlus4(String zipCodePlus4) {
		return (null != zipCodePlus4 && !zipCodePlus4.trim().equals("") && zipCodePlus4.trim().length() == 4);
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.domain.AbstractDomain#toString()
	 */
	@Override
	public String toString() {
		return "MailingAddress [addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", city=" + city
				+ ", state=" + state + ", zipCode=" + zipCode + ", zipCodePlus4=" + zipCodePlus4
				+ ", fullMailingAddress=" + fullMailingAddress + ", uuid=" + uuid + "]";
	}
}
