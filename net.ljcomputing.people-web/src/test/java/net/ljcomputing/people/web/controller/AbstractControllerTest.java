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

import net.ljcomputing.gson.converter.GsonConverterService;
import net.ljcomputing.logging.annotation.InjectLogging;

import org.junit.Before;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Abstract implementation of controller tests.
 * 
 * @author James G. Willmore
 *
 */
public abstract class AbstractControllerTest {
    @InjectLogging
    protected static Logger logger;

    /** The gson converter service. */
    @Autowired
    protected GsonConverterService gsonConverterService;

    /** The wac. */
    @Autowired
    protected WebApplicationContext wac;

    /** The mock mvc. */
    protected MockMvc mockMvc;

    /**
     * Set up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
	mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    
    /**
     * Log begin test.
     */
    protected void logBeginTest() {
	logger.debug("Begin test");
    }

    /**
     * Log end test.
     */
    protected void logEndTest() {
	logger.debug("End test");
    }
    
    /**
     * Log results.
     *
     * @param results the results
     */
    protected void logResults(Object results) {
	logger.debug("results: {}", results);
    }
}
