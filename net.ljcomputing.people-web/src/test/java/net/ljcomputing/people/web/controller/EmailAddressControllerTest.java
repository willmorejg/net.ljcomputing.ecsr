package net.ljcomputing.people.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.util.List;

import net.ljcomputing.people.PeopleApplication;
import net.ljcomputing.people.domain.EmailAddress;
import net.ljcomputing.people.entity.EmailAddressEntity;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PeopleApplication.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmailAddressControllerTest extends AbstractControllerTest {
    @Autowired
    private EmailAddressController emailAddressController;

    private static EmailAddressEntity emailAddress;

    @Test
    @Ignore
    public void test0Create() throws Exception {
	logBeginTest();

	emailAddress = new EmailAddressEntity();
	emailAddress.setLocalPart("testing1");
	emailAddress.setDomain("tesing.org");

	MvcResult results = mockMvc
		.perform(
			post("/emails/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(
					gsonConverterService
						.toJson((EmailAddress) emailAddress)))
		.andExpect(status().isOk()).andReturn();

	logResults(results);

	emailAddress = (EmailAddressEntity) gsonConverterService.fromJson(
		results.getResponse().getContentAsString(),
		EmailAddressEntity.class);
	logEndTest();
    }

    @Test
    public void test1GetAll() throws Exception {
	logBeginTest();

	MvcResult results = mockMvc.perform(get("/emails/"))
		.andExpect(status().isOk()).andReturn();

	logResults("json results: "
		+ results.getResponse().getContentAsString());

	@SuppressWarnings({ "unchecked", "serial" })
	List<EmailAddressEntity> entities = (List<EmailAddressEntity>) gsonConverterService
		.fromJson(results.getResponse().getContentAsString(),
			new TypeToken<List<EmailAddressEntity>>() {
			}.getType());

	logResults(results);

	for (EmailAddressEntity entity : entities) {
	    logResults("entity results: " + entity);
	    emailAddress = entity;
	}

	logEndTest();
    }

    @Test
    public void test2Get() throws Exception {
	logBeginTest();

	MvcResult results = mockMvc
		.perform(get("/emails/" + emailAddress.getUuid().toString()))
		.andExpect(status().isOk()).andReturn();

	logResults(results);

	EmailAddressEntity entity = (EmailAddressEntity) gsonConverterService
		.fromJson(results.getResponse().getContentAsString(),
			EmailAddressEntity.class);

	logResults(entity);

	logEndTest();
    }

    @Test
    public void test3Update() throws Exception {
	logBeginTest();

	EmailAddress poster = (EmailAddress) emailAddress;
	poster.setDomain("thisworks.com");

	MvcResult results = mockMvc.perform(
		post("/emails/" + emailAddress.getUuid().toString()).content(
			gsonConverterService.toJson(poster)).contentType(
			MediaType.APPLICATION_JSON)).andReturn();

	logResults(results.getResponse().getContentAsString());

	results = mockMvc.perform(get("/emails/")).andExpect(status().isOk())
		.andReturn();

	logResults(results.getResponse().getContentAsString());

	@SuppressWarnings({ "unchecked", "serial" })
	List<EmailAddressEntity> entities = (List<EmailAddressEntity>) gsonConverterService
		.fromJson(results.getResponse().getContentAsString(),
			new TypeToken<List<EmailAddressEntity>>() {
			}.getType());

	logResults(entities);

	for (EmailAddressEntity entity : entities) {
	    logResults("entity: " + entity);
	}

	logEndTest();
    }

    @Test
    @Ignore
    public void test99Delete() throws Exception {
	logBeginTest();

	logger.debug("url: /emails/{}", emailAddress.getUuid().toString());

	MvcResult results = mockMvc
		.perform(
			delete(URI.create("/emails/"
				+ emailAddress.getUuid().toString())))
		.andExpect(status().isOk()).andReturn();

	logResults(results);

	logEndTest();
    }
}
