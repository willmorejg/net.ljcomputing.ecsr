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

import org.springframework.stereotype.Service;

import net.ljcomputing.core.service.impl.AbstractEntityServiceImpl;
import net.ljcomputing.team.domain.Team;
import net.ljcomputing.team.entity.TeamEntity;
import net.ljcomputing.team.repository.TeamEntityRepository;
import net.ljcomputing.team.service.TeamService;

/**
 * Implementation of team service.
 * 
 * @author James G. Willmore
 *
 */
@Service
public class TeamServiceImpl extends AbstractEntityServiceImpl<Team, TeamEntity, TeamEntityRepository>
		implements TeamService {
}
