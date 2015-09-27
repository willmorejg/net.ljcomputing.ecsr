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

package net.ljcomputing.people.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import net.ljcomputing.people.entity.PersonEntity;

/**
 * Neo4J person graph repository.
 * 
 * @author James G. Willmore
 *
 */
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

	/**
	 * Find by uuid.
	 *
	 * @param uuid
	 *            the uuid
	 * @return the person entity
	 */
	PersonEntity findByUuid(UUID uuid);

	/**
	 * Find by full name like.
	 *
	 * @param fullName
	 *            the full name
	 * @return the list
	 */
	List<PersonEntity> findByFullNameLike(String fullName);

	/**
	 * Find by full name like.
	 *
	 * @param fullName
	 *            the full name
	 * @param page
	 *            the page
	 * @return the list
	 */
	List<PersonEntity> findByFullNameLike(String fullName, Pageable page);

	/**
	 * Find by full name like.
	 *
	 * @param fullName
	 *            the full name
	 * @param sort
	 *            the sort
	 * @return the list
	 */
	List<PersonEntity> findByFullNameLike(String fullName, Sort sort);
}
