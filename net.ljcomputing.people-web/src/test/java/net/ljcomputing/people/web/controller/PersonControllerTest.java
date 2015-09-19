package net.ljcomputing.people.web.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import net.ljcomputing.people.PeopleApplication;
import net.ljcomputing.people.domain.Person;
import net.ljcomputing.people.entity.PersonEntity;
import net.ljcomputing.people.entity.PersonalContactsEntity;
import net.ljcomputing.randomdata.config.RandomDataConfiguration;
import net.ljcomputing.randomdata.plugin.PersonPlugin;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PeopleApplication.class, RandomDataConfiguration.class })
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonControllerTest extends AbstractControllerTest {

	private static PersonEntity person;

	@Autowired
	private PersonPlugin personPlugin;

	@Test
	@Ignore
	public void test0CreatePeople() throws Exception {
		logBeginTest();

		person = new PersonEntity();

		person.setFirstName(personPlugin.firstName());
		person.setLastName(personPlugin.lastName());
		personPlugin.fullName();
		mockMvc.perform(post("/people/").content(gsonConverterService.toJson((Person) person))
				.contentType(MediaType.APPLICATION_JSON));

		logEndTest();
	}

	@SuppressWarnings({ "serial", "unchecked" })
	@Test
	public void test1GetAllPeople() throws Exception {
		logBeginTest();

		MvcResult results = mockMvc.perform(get("/people/")).andExpect(status().isOk()).andReturn();

		logResults("json results: " + results.getResponse().getContentAsString());
		JsonResponse jsonResponse = new Gson().fromJson(results.getResponse().getContentAsString(), JsonResponse.class);
		List<PersonalContactsEntity> entities = (List<PersonalContactsEntity>) new Gson().fromJson(
				new Gson().toJson(jsonResponse.getRows()), 
				new TypeToken<List<PersonalContactsEntity>>() {}.getType());

		logResults("entities results: " + entities);

		for (PersonalContactsEntity entity : entities) {
			logResults("entity results: " + entity);
			person = entity;
		}

		logEndTest();
	}

	@Test
	public void test2GetPerson() throws Exception {
		logBeginTest();

		MvcResult results = mockMvc.perform(get("/people/" + person.getUuid().toString())).andExpect(status().isOk())
				.andReturn();

		logResults("json results: " + results.getResponse().getContentAsString());

		PersonalContactsEntity entity = (PersonalContactsEntity) gsonConverterService.fromJson(results.getResponse().getContentAsString(),
				PersonalContactsEntity.class);

		logResults("entity results: " + entity);

		person = entity;

		logEndTest();
	}

	@Test
	public void test3UpdatePerson() throws Exception {
		logBeginTest();

		Person poster = (Person) person;
		poster.setFirstName(personPlugin.firstName());

		mockMvc.perform(post("/people/" + person.getUuid().toString()).content(gsonConverterService.toJson(poster))
				.contentType(MediaType.APPLICATION_JSON));

		MvcResult results = mockMvc.perform(get("/people/")).andExpect(status().isOk()).andReturn();

		logResults("json results: " + results.getResponse().getContentAsString());

		@SuppressWarnings({ "unchecked", "serial" })
		List<PersonEntity> entities = (List<PersonEntity>) gsonConverterService
				.fromJson(results.getResponse().getContentAsString(), new TypeToken<List<PersonEntity>>() {
				}.getType());

		logResults("entities results: " + entities);

		for (PersonEntity entity : entities) {
			logResults("entity results: " + entity);
			person = entity;
		}

		logEndTest();
	}

	@Test
	@Ignore
	public void test4GetPersonalContacts() {
		logBeginTest();
		fail("Not yet implemented");
		logEndTest();
	}

	@Test
	@Ignore
	public void test99DeletePerson() {
		logBeginTest();
		fail("Not yet implemented");
		logEndTest();
	}
}

class JsonResponse {
	private Integer total;
	private List<?> rows;
	
	public Integer getTotal() {
		return total;
	}
	
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	public List<?> getRows() {
		return rows;
	}
	
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
}