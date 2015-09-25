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

package net.ljcomputing.core.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.neo4j.repository.CRUDRepository;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import net.ljcomputing.core.domain.AbstractDomain;
import net.ljcomputing.core.entity.Entity;
import net.ljcomputing.core.service.EntityService;
import net.ljcomputing.gson.converter.GsonConverterService;

/**
 * Abstract implementation of entity service.
 * 
 * @param <T> the domain 
 * @param <S> the entity 
 * @param <R> the repository
 * 
 * @author James G. Willmore
 */
public abstract class AbstractEntityServiceImpl<T extends AbstractDomain, S extends Entity, R extends CRUDRepository<S>>
		implements EntityService<T, S, R> {

	/** The gson converter service. */
	@Autowired
	private GsonConverterService gsonConverterService;

	/** The repository. */
	@Autowired
	private R repository;

	/** The Neo4J template. */
	@Autowired
	protected Neo4jTemplate template;

	/** The ignored properties. */
	private static String[] IGNORED_PROPERTIES = { "uuid" };

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.service.EntityService#getGsonConverterService()
	 */
	public GsonConverterService getGsonConverterService() {
		return gsonConverterService;
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.service.EntityService#getRepository()
	 */
	public R getRepository() {
		return repository;
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.service.EntityService#getEntityClass()
	 */
	@SuppressWarnings("unchecked")
	public Class<S> getEntityClass() {
		return (Class<S>) (((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.service.EntityService#getNewEntityInstance()
	 */
	public S getNewEntityInstance() {
		try {
			return (S) getEntityClass().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.service.EntityService#cloneFromDomain(java.lang.Object, java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public S cloneFromDomain(T domain, Class<? extends Entity> klass) {
		String json = gsonConverterService.toJson(domain);
		return (S) gsonConverterService.fromJson(json, klass);
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.service.EntityService#exists(java.lang.Object)
	 */
	public S exists(T domain) {
		S entity = getNewEntityInstance();
		entity = cloneFromDomain(domain, entity.getClass());
		try {
			return readByUuid(domain.getUuid());
		} catch (Exception e) {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.service.EntityService#create(java.lang.Object)
	 */
	@SuppressWarnings({ "unchecked" })
	@Transactional
	public T create(T domain) {
		S entity = exists(domain);
		if (null == entity) {
			entity = cloneFromDomain(domain, getEntityClass());
		}
		entity = getRepository().save(entity);
		return (T) entity;
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.service.EntityService#readAll()
	 */
	@Transactional
	public List<S> readAll() {
		return (List<S>) Lists.newArrayList(getRepository().findAll());
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.service.EntityService#readAll(org.springframework.data.domain.Pageable)
	 */
	@Transactional
	public List<S> readAll(Pageable page) {
		return (List<S>) Lists.newArrayList(getRepository().findAll(page));
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.service.EntityService#readByUuid(java.util.UUID)
	 */
	@Transactional
	public S readByUuid(UUID uuid) {
		String query = String.format("MATCH (n: %s) WHERE n.uuid='%s' RETURN n", getEntityClass().getSimpleName(),
				uuid);
		return getRepository().query(query, null).singleOrNull();
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.service.EntityService#readByIndexedValue(java.lang.String)
	 */
	@Transactional
	public S readByIndexedValue(String indexedValue) {
		String query = String.format("MATCH (n:%s) WHERE n.indexedValue='%s' RETURN n",
				getEntityClass().getSimpleName(), indexedValue);
		return getRepository().query(query, null).singleOrNull();
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.service.EntityService#searchLike(java.lang.String, java.lang.String)
	 */
	@Transactional
	public List<S> searchLike(String property, String search) {
		String query = String.format("MATCH (n:%s) WHERE n.%s=~'%s' RETURN n", getEntityClass().getSimpleName(),
				property, search);
		return (List<S>) Lists.newArrayList(getRepository().query(query, null));
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.service.EntityService#searchLike(java.lang.String, java.lang.String, org.springframework.data.domain.Pageable)
	 */
	@Transactional
	public List<S> searchLike(String property, String search, Pageable page) {
		String query = String.format("MATCH (n:%s) WHERE n.%s=~'%s' RETURN n", getEntityClass().getSimpleName(),
				property, search);
		return (List<S>) Lists.newArrayList(getRepository().query(query, null));
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.service.EntityService#searchLike(java.lang.String, java.lang.String, org.springframework.data.domain.Sort)
	 */
	@Transactional
	public List<S> searchLike(String property, String search, Sort sort) {
		String query = String.format("MATCH (n:%s) WHERE n.%s=~'%s' RETURN n", getEntityClass().getSimpleName(),
				property, search);
		return (List<S>) Lists.newArrayList(getRepository().query(query, null));
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.service.EntityService#readByUuidString(java.lang.String)
	 */
	public S readByUuidString(String uuidString) {
		if (uuidString != null) {
			return readByUuid(UUID.fromString(uuidString));
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.service.EntityService#readById(java.lang.Long)
	 */
	@Transactional
	public S readById(Long id) {
		return getRepository().findOne(id);
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.service.EntityService#update(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public T update(T domain) {
		S entity = exists(domain);
		if (null != entity) {
			gsonConverterService.merge(entity, domain, IGNORED_PROPERTIES);
			entity = getRepository().save(entity);
		}

		return (T) entity;
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.service.EntityService#delete(java.lang.Object)
	 */
	public void delete(T domain) {
		delete(domain.getUuid());
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.service.EntityService#delete(java.lang.Long)
	 */
	public void delete(Long id) {
		getRepository().delete(id);
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.service.EntityService#delete(java.util.UUID)
	 */
	@Transactional
	public void delete(UUID uuid) {
		S entity = readByUuid(uuid);
		if (null != entity) {
			getRepository().delete(entity);
		}
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.service.EntityService#delete(java.lang.String)
	 */
	public void delete(String uuidString) {
		if (uuidString != null) {
			delete(UUID.fromString(uuidString));
		} else {
			throw new RuntimeException("Cannot delete a null UUID");
		}
	}
	
	/* (non-Javadoc)
	 * @see net.ljcomputing.core.service.EntityService#deleteAll()
	 */
	@Transactional
	public void deleteAll() {
		getRepository().deleteAll();
	}
}
