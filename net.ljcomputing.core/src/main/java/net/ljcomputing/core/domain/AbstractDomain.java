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

package net.ljcomputing.core.domain;

import java.util.UUID;

/**
 * Abstract implementation of a domain class.
 * 
 * @author James G. Willmore
 *
 */
public abstract class AbstractDomain implements Node {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5596912472711861850L;

	/** The uuid. */
	protected UUID uuid;

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ljcomputing.ecsr.domain.Domain#getUuid()
	 */
	public UUID getUuid() {
		return uuid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ljcomputing.ecsr.domain.Domain#setUuid(java.util.UUID)
	 */
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ljcomputing.ecsr.domain.Domain#createUuid()
	 */
	public void createUuid() {
		this.uuid = UUID.randomUUID();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AbstractDomain [uuid=" + uuid + "]";
	}
}
