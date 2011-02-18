package com.octo.rest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.LowLevelAppDescriptor;
import com.sun.jersey.test.framework.spi.container.inmemory.InMemoryTestContainerFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:WEB-INF/applicationContext.xml" })
public class TestServiceInMemory {
	private JerseyTest jersey;
	
	@Autowired
	SomeService someService;
	
	@Before
	public void before() throws Exception {
		jersey = new JerseyTest(new InMemoryTestContainerFactory()) {
			protected com.sun.jersey.test.framework.AppDescriptor configure() {
				DefaultResourceConfig rc = new DefaultResourceConfig();
				rc.getSingletons().add(someService);
				rc.getClasses().add(org.codehaus.jackson.jaxrs.JacksonJsonProvider.class);
				return new LowLevelAppDescriptor.Builder(rc).contextPath("poc-jersey").build();
			};
		};
		jersey.setUp();
	}

	@Test
	public void testGet1() {
		String result = jersey.resource().path("/someResource/1").get(String.class);
		assertEquals(
"{\"otherBeans\":[" +
	"{\"otherString\":\"coucou0\",\"otherInteger\":0}," +
	"{\"otherString\":\"coucou1\",\"otherInteger\":1}," +
	"{\"otherString\":\"coucou2\",\"otherInteger\":2}," +
	"{\"otherString\":\"coucou3\",\"otherInteger\":3}," +
	"{\"otherString\":\"coucou4\",\"otherInteger\":4}," +
	"{\"otherString\":\"coucou5\",\"otherInteger\":5}," +
	"{\"otherString\":\"coucou6\",\"otherInteger\":6}," +
	"{\"otherString\":\"coucou7\",\"otherInteger\":7}," +
	"{\"otherString\":\"coucou8\",\"otherInteger\":8}," +
	"{\"otherString\":\"coucou9\",\"otherInteger\":9}" +
"]," +
"\"someInteger\":1," +
"\"someString\":\"hello1\"}", result);
	}
	
	@After
	public void after() throws Exception {
		jersey.tearDown();
	}
}
