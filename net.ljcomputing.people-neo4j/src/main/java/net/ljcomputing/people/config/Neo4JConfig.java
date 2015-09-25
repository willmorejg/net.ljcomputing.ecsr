/**
           Copyright 2014 James G. Willmore

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

package net.ljcomputing.people.config;

import java.util.Date;

import org.neo4j.graphdb.GraphDatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.lifecycle.AfterDeleteEvent;
import org.springframework.data.neo4j.lifecycle.AfterSaveEvent;
import org.springframework.data.neo4j.lifecycle.BeforeDeleteEvent;
import org.springframework.data.neo4j.lifecycle.BeforeSaveEvent;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import net.ljcomputing.core.entity.Entity;

/**
 * Configuration for Neo4J data source.
 * 
 * @author James G. Willmore
 * 
 */
@EnableTransactionManagement
@Import(RepositoryRestMvcConfiguration.class)
@EnableAutoConfiguration
@ComponentScan(basePackages = { "net.ljcomputing.*.entity" })
@Configuration
@EnableNeo4jRepositories(basePackages = "net.ljcomputing.*.repository")
public class Neo4JConfig extends Neo4jConfiguration {
	private static Logger logger = LoggerFactory.getLogger(Neo4JConfig.class);

	private enum Event {
		BeforeSaveEvent, AfterSaveEvent, BeforeDeleteEvent, AfterDeleteEvent
	}

	/**
	 * Instantiates a new Neo4J config.
	 */
	public Neo4JConfig() {
		logger.info("Configuring Neo4j");

		setBasePackage("net.ljcomputing.*.entity");
	}

	/**
	 * The Neo4J graph database service.
	 * 
	 * @return the graph database service
	 */
	@Bean
	GraphDatabaseService graphDatabaseService() {
		return new SpringRestGraphDatabase("http://localhost:7474/db/data");
	}

	// @Bean(destroyMethod = "shutdown")
	// GraphDatabaseService graphDatabaseService() {
	// return new GraphDatabaseFactory().newEmbeddedDatabase("people.db");
	// }

	/* (non-Javadoc)
	 * @see org.springframework.data.neo4j.config.Neo4jConfiguration#neo4jTemplate()
	 */
	@Bean
	public Neo4jTemplate neo4jTemplate() {
		return new Neo4jTemplate(graphDatabaseService());
	}

	/**
	 * Before save event application listener.
	 *
	 * @return the application listener
	 */
	@SuppressWarnings("rawtypes")
	@Bean
	ApplicationListener<BeforeSaveEvent> beforeSaveEventApplicationListener() {
		return new ApplicationListener<BeforeSaveEvent>() {

			public void onApplicationEvent(BeforeSaveEvent event) {
				// create the unique index value for the Entity being saved
				if (event.getEntity() instanceof Entity) {
					((Entity) event.getEntity()).createIndexedValue();
					Object obj = event.getEntity();
					long timeStamp = event.getTimestamp();
					printAuditEventMessage(obj, Event.BeforeSaveEvent, "createUniqueValue()", timeStamp);
				}

				Object obj = event.getEntity();
				long timeStamp = event.getTimestamp();
				printAuditEvent(obj, Event.BeforeSaveEvent, timeStamp);
			}

		};

	}

	/**
	 * After save event application listener.
	 *
	 * @return the application listener
	 */
	@SuppressWarnings("rawtypes")
	@Bean
	ApplicationListener<AfterSaveEvent> afterSaveEventApplicationListener() {
		return new ApplicationListener<AfterSaveEvent>() {

			public void onApplicationEvent(AfterSaveEvent event) {
				Object obj = event.getEntity();
				long timeStamp = event.getTimestamp();
				printAuditEvent(obj, Event.AfterSaveEvent, timeStamp);
			}

		};
	}

	/**
	 * Before delete event application listener.
	 *
	 * @return the application listener
	 */
	@SuppressWarnings("rawtypes")
	@Bean
	ApplicationListener<BeforeDeleteEvent> beforeDeleteEventApplicationListener() {
		return new ApplicationListener<BeforeDeleteEvent>() {

			public void onApplicationEvent(BeforeDeleteEvent event) {
				Object obj = event.getEntity();
				long timeStamp = event.getTimestamp();
				printAuditEvent(obj, Event.BeforeDeleteEvent, timeStamp);
			}

		};
	}

	/**
	 * After delete event application listener.
	 *
	 * @return the application listener
	 */
	@SuppressWarnings("rawtypes")
	@Bean
	ApplicationListener<AfterDeleteEvent> afterDeleteEventApplicationListener() {
		return new ApplicationListener<AfterDeleteEvent>() {

			public void onApplicationEvent(AfterDeleteEvent event) {
				Object obj = event.getEntity();
				long timeStamp = event.getTimestamp();
				printAuditEvent(obj, Event.AfterDeleteEvent, timeStamp);
			}

		};
	}

	/**
	 * Prints the audit event.
	 *
	 * @param obj the obj
	 * @param event the event
	 * @param timeStamp the time stamp
	 */
	private void printAuditEvent(Object obj, Event event, long timeStamp) {
		logger.info("{} @ {} : {}", event, new Date(timeStamp), obj);
	}

	/**
	 * Prints the audit event message.
	 *
	 * @param obj the obj
	 * @param event the event
	 * @param message the message
	 * @param timeStamp the time stamp
	 */
	private void printAuditEventMessage(Object obj, Event event, String message, long timeStamp) {
		logger.info("{} @ {} - {} : {}", event, message, new Date(timeStamp), obj);
	}
}
