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

package net.ljcomputing.people.entity;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import net.ljcomputing.people.domain.MailingAddress;

/**
 * Phone number entity.
 * 
 * @author James G. Willmore
 *
 */
@NodeEntity(useShortNames = true)
public class MailingAddressEntity extends MailingAddress implements ContactEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2564488588081338668L;

	/** The id. */
	@GraphId
	private Long id;

	/** The indexed value. */
	@Indexed(unique = true, indexType = IndexType.FULLTEXT, indexName = "uniqueMailingAddressIdx", failOnDuplicate = true)
	private String indexedValue;

	/**
	 * Instantiates a new mailing address entity.
	 */
	public MailingAddressEntity() {
	}

	/**
	 * Instantiates a new mailing address entity.
	 *
	 * @param mailingAddress the mailing address
	 */
	public MailingAddressEntity(MailingAddress mailingAddress) {
		super(mailingAddress);
	}

	/**
	 * Instantiates a new mailing address entity.
	 *
	 * @param mailingAddress the mailing address
	 * @param id the id
	 */
	public MailingAddressEntity(MailingAddress mailingAddress, Long id) {
		super(mailingAddress);
		setId(id);
	}

	/**
	 * Instantiates a new mailing address entity.
	 *
	 * @param mailingAddressEntity the mailing address entity
	 */
	public MailingAddressEntity(MailingAddressEntity mailingAddressEntity) {
		this(mailingAddressEntity, mailingAddressEntity.getId());
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.entity.Entity#setId(java.lang.Long)
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.entity.Entity#getId()
	 */
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.entity.Entity#getIndexedValue()
	 */
	public String getIndexedValue() {
		return indexedValue;
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.core.entity.Entity#createIndexedValue()
	 */
	public void createIndexedValue() {
		indexedValue = getFullPostalAddress();

		if (null == getUuid()) {
			createUuid();
		}
	}

	/* (non-Javadoc)
	 * @see net.ljcomputing.people.domain.MailingAddress#toString()
	 */
	@Override
	public String toString() {
		return "MailingAddressEntity [id=" + id + ", indexedValue=" + indexedValue + ", "
				+ super.toString() + "]";
	}
}
