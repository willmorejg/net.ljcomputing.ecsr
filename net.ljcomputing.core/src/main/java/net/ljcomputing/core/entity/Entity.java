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

package net.ljcomputing.core.entity;

import java.io.Serializable;

import net.ljcomputing.core.domain.Node;

/**
 * Interface for entity classes. The interface ensures that there will be an id
 * attribute for an entity.
 * 
 * @author James G. Willmore
 *
 */
public interface Entity extends Serializable, Node {

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	void setId(Long id);

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	Long getId();

	/**
	 * Creates the indexed value.
	 */
	void createIndexedValue();

	/**
	 * Gets the indexed value.
	 *
	 * @return the indexed value
	 */
	String getIndexedValue();
	
	/**
	 * Indicates if entity has all required values.
	 *
	 * @return the boolean
	 */
	Boolean isValid();
}
