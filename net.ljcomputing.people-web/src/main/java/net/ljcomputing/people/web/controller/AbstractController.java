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

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * @author James G. Willmore
 *
 */
public abstract class AbstractController {

	/**
	 * Gets the page request.
	 *
	 * @param pageNumber
	 *            the page number
	 * @param pageSize
	 *            the page size
	 * @param sortName
	 *            the sort name
	 * @param sortOrder
	 *            the sort order
	 * @return the page request
	 */
	protected PageRequest getPageRequest(Integer pageNumber, Integer pageSize, String sortName, String sortOrder) {
		Sort.Direction order = null;
		PageRequest pageRequest = null;

		if (null != sortOrder && !sortOrder.trim().equals("")) {
			order = Sort.Direction.valueOf(sortOrder.toUpperCase());
		}

		if (null != pageNumber && null != pageSize) {
			if (order != null) {
				pageRequest = new PageRequest((pageNumber), pageSize, order, sortName);
			} else {
				pageRequest = new PageRequest((pageNumber), pageSize);
			}
		}

		return pageRequest;
	}
}
