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

package net.ljcomputing.core.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import net.ljcomputing.core.entity.Entity;
import net.ljcomputing.gson.converter.GsonConverterService;

/**
 * Interface for entity service.
 *
 * @author James G. Willmore
 * @param <T>
 *            the domain
 * @param <S>
 *            the entity
 * @param <R>
 *            the repository
 */
public interface EntityService<T, S, R> {

	/**
	 * Gets the GSON converter service.
	 *
	 * @return the gsonConverterService
	 */
	GsonConverterService getGsonConverterService();

	/**
	 * Gets the entity repository associated with the given domain.
	 *
	 * @return the repository
	 */
	R getRepository();

	/**
	 * Gets the entity class.
	 *
	 * @return the entity class
	 */
	Class<S> getEntityClass();
	
	/**
	 * Gets the new entity instance.
	 *
	 * @return the new entity instance
	 */
	S getNewEntityInstance();
	
	/**
	 * Clone entity from provided domain.
	 *
	 * @param domain the domain
	 * @param klass the klass
	 * @return the s
	 */
	S cloneFromDomain(T domain, Class<? extends Entity> klass);
	
	/**
	 * Check to see if entity exists for provided domain.
	 *
	 * @param domain the domain
	 * @return the s
	 */
	S exists(T domain);
	
	/**
	 * Save the given domain as an entity.
	 *
	 * @param domain
	 *            the domain
	 * @return the t
	 */
	T create(T domain);

	/**
	 * Find all of the entities for the associated domain.
	 *
	 * @return a List of entities
	 */
	List<S> readAll();

	/**
	 * Find all of the entities for the associated domain.
	 *
	 * @param page
	 *            the page
	 * @return the Lists the
	 */
	List<S> readAll(Pageable page);

	/**
	 * Find the entity by the give UUID String.
	 *
	 * @param uuidString
	 *            the UUID String
	 * @return the s
	 */
	S readByUuidString(String uuidString);

	/**
	 * Find the entity by the give UUID.
	 *
	 * @param uuid
	 *            the UUID
	 * @return the s
	 */
	S readByUuid(UUID uuid);

	/**
	 * Find the associated entity by the given indexed value.
	 *
	 * @param indexedValue the indexed value
	 * @return the s
	 */
	S readByIndexedValue(String indexedValue);
	
	/**
	 * Search for instances where the given property value is like the provided
	 * search term.
	 *
	 * @param property
	 *            the property
	 * @param search
	 *            the search
	 * @return the list
	 */
	List<S> searchLike(String property, String search);

	/**
	 * Search for instances where the given property value is like the provided
	 * search term.
	 *
	 * @param property
	 *            the property
	 * @param search
	 *            the search
	 * @param page
	 *            the page
	 * @return the list
	 */
	List<S> searchLike(String property, String search, Pageable page);

	/**
	 * Search for instances where the given property value is like the provided
	 * search term.
	 *
	 * @param property
	 *            the property
	 * @param search
	 *            the search
	 * @param sort
	 *            the sort
	 * @return the list
	 */
	List<S> searchLike(String property, String search, Sort sort);

	/**
	 * Find an entity by the given id.
	 *
	 * @param id
	 *            the id
	 * @return the s
	 */
	S readById(Long id);

	/**
	 * Update the entity associated with the values of the given domain.
	 *
	 * @param domain
	 *            the domain
	 * @return the s
	 */
	T update(T domain);

	/**
	 * Delete the entity associated with the given domain.
	 *
	 * @param domain
	 *            the domain
	 */
	void delete(T domain);

	/**
	 * Delete the entity associated with the given id.
	 *
	 * @param id
	 *            the id
	 */
	void delete(Long id);

	/**
	 * Delete the entity associated with the given UUID.
	 *
	 * @param uuid
	 *            the UUID
	 */
	void delete(UUID uuid);

	/**
	 * Delete the entity associated with the given UUID string.
	 *
	 * @param uuidString
	 *            the uuid string
	 */
	void delete(String uuidString);
	
	/**
	 * Delete all entities for the defined entity class.
	 */
	void deleteAll();
}
