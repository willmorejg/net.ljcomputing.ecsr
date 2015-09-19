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

package net.ljcomputing.team.service.impl;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.ljcomputing.logging.annotation.InjectLogging;
import net.ljcomputing.team.TeamApplication;
import net.ljcomputing.team.entity.TeamEntity;
import net.ljcomputing.team.service.TeamService;

/**
 * Team service implementation test.
 * 
 * @author James G. Willmore
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TeamApplication.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TeamServiceImplTest {

	/** The logger. */
	@InjectLogging
	private Logger logger;

	/** The team service. */
	@Autowired
	private TeamService teamService;

	/** The team. */
	private static TeamEntity team;

	/**
	 * Test method for
	 * {@link net.ljcomputing.core.service.impl.AbstractEntityServiceImpl#save(net.ljcomputing.core.domain.AbstractDomain)}
	 * .
	 */
	@Test
	public void test01Save() {
		logger.debug("begin");

		team = new TeamEntity();

		team.setName("Test team");
		team.setDescription("A testing team");

		team = (TeamEntity) teamService.save(team);
		
		logger.debug("team: {}" , team.toString());

		logger.debug("end");
	}

	/**
	 * Test method for
	 * {@link net.ljcomputing.core.service.impl.AbstractEntityServiceImpl#findAll()}
	 * .
	 */
	@Test
	public void test02FindAll() {
		logger.debug("begin");

		for (TeamEntity t : teamService.findAll()) {
			logger.debug("team: {}", t);
		}

		logger.debug("end");
	}

	/**
	 * Test method for
	 * {@link net.ljcomputing.core.service.impl.AbstractEntityServiceImpl#findByUuid(java.util.UUID)}
	 * .
	 */
	@Test
	@Ignore
	public void test03FindByUuid() {
		logger.debug("begin");

		logger.debug("team: {}", teamService.findByUuid(team.getUuid()));

		logger.debug("end");
	}

	/**
	 * Test method for
	 * {@link net.ljcomputing.core.service.impl.AbstractEntityServiceImpl#update(net.ljcomputing.core.domain.AbstractDomain)}
	 * .
	 */
	@Test
	public void test04Update() {
		logger.debug("begin");

		team.setDescription("UPDATED - " + team.getDescription());

		teamService.update(team);

		logger.debug("updated team: {}", teamService.findByUuid(team.getUuid()));

		logger.debug("end");
	}

	/**
	 * Test method for
	 * {@link net.ljcomputing.core.service.impl.AbstractEntityServiceImpl#delete(net.ljcomputing.core.domain.AbstractDomain)}
	 * .
	 */
	@Test
	public void test05Delete() {
		logger.debug("begin");

		teamService.delete(team);

		logger.debug("after delete: {}", teamService.findAll().toString());

		logger.debug("end");
	}

}
