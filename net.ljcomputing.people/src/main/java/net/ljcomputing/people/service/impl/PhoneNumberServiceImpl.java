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

package net.ljcomputing.people.service.impl;

import org.springframework.stereotype.Service;

import net.ljcomputing.core.service.impl.AbstractEntityServiceImpl;
import net.ljcomputing.people.domain.PhoneNumber;
import net.ljcomputing.people.entity.PhoneNumberEntity;
import net.ljcomputing.people.repository.PhoneNumberRepository;
import net.ljcomputing.people.service.PhoneNumberService;

/**
 * Implementation of postal address service.
 * 
 * @author James G. Willmore
 *
 */
@Service
public class PhoneNumberServiceImpl
		extends AbstractEntityServiceImpl<PhoneNumber, PhoneNumberEntity, PhoneNumberRepository>
		implements PhoneNumberService {
}
