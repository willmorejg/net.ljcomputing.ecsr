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

package net.ljcomputing.people.web.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import net.ljcomputing.gson.config.GsonWebMvcConfigurerAdapter;
import net.ljcomputing.people.PeopleApplication;

/**
 * People deployment configuration class.
 * 
 * @author James G. Willmore
 *
 */
@Configuration
@ComponentScan(basePackages = { "net.ljcomputing.core", "net.ljcomputing.people" })
@EnableWebMvc
@Import({ GsonWebMvcConfigurerAdapter.class, PeopleApplication.class })
public class PeopleWebConfiguration extends SpringBootServletInitializer implements WebApplicationInitializer {
	private static Logger logger = LoggerFactory.getLogger(PeopleWebConfiguration.class);

	@Bean
	protected ServletContextListener listener() {
		return new ServletContextListener() {

			@Override
			public void contextInitialized(ServletContextEvent sce) {
				System.out.println("contextInitialized");
				logger.info("ServletContext initialized");
			}

			@Override
			public void contextDestroyed(ServletContextEvent sce) {
				System.out.println("contextDestroyed");
				logger.info("ServletContext destroyed");
			}

		};
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(PeopleWebConfiguration.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		System.out.println("configure");
		logger.debug("Configuring ... ");
		return builder.showBanner(true).sources(PeopleWebConfiguration.class).web(true).logStartupInfo(true);
	}

	public void onStartup(ServletContext container) throws ServletException {
		logger.info("onStartup ...");
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(PeopleWebConfiguration.class);
		ctx.setServletContext(container);

		DispatcherServlet ds = new DispatcherServlet(ctx);
		ServletRegistration.Dynamic servlet = container.addServlet("dispatcherServlet", ds);

		servlet.setInitParameter("throwExceptionIfNoHandlerFound", "true");
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");

		logger.warn("servlet name: {}", servlet.getName());
		logger.warn("servlet throwExceptionIfNoHandlerFound: {}",
				servlet.getInitParameter("throwExceptionIfNoHandlerFound"));
	}

}
