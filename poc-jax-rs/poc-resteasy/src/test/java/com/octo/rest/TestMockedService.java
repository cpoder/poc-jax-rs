package com.octo.rest;

import static org.junit.Assert.assertEquals;

import java.net.URISyntaxException;

import javax.servlet.http.HttpServletResponse;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:WEB-INF/applicationContext.xml" })
public class TestMockedService {
	private Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();
	
	@Autowired
	private SomeService someService;

	@Before
	public void before() {
		dispatcher.getRegistry().addSingletonResource(someService);
	}

	@Test
	public void testGet1() throws URISyntaxException {
		MockHttpRequest request = MockHttpRequest.get("/someResource/1");
		MockHttpResponse response = new MockHttpResponse();
		dispatcher.invoke(request, response);

		assertEquals(HttpServletResponse.SC_OK, response.getStatus());

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
"],\"someInteger\":1,\"someString\":\"hello1\"}",
				response.getContentAsString());
	}
}
