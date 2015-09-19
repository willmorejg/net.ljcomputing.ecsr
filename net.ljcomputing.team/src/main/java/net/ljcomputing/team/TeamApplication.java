package net.ljcomputing.team;
/**
           Copyright 2015 James G. Willmore

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import net.ljcomputing.gson.config.GsonConfiguration;
import net.ljcomputing.logging.config.LoggingConfiguration;
import net.ljcomputing.people.config.Neo4JConfig;
import net.ljcomputing.rhino.RhinoConfiguration;

/**
 * People application CLI class.
 */
@Configuration
@ComponentScan(basePackages = { "net.ljcomputing.core", "net.ljcomputing.people", "net.ljcomputing.team" })
@Import({ LoggingConfiguration.class, Neo4JConfig.class, GsonConfiguration.class, RhinoConfiguration.class })
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class TeamApplication implements CommandLineRunner {
	private static Logger logger = LoggerFactory.getLogger(TeamApplication.class);

	/**
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	@Override
	public void run(String... args) {
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws Exception
	 *             the exception
	 */
	public static void main(String[] args) throws Exception {
		SpringApplication.run(TeamApplication.class, args);
	}
}
