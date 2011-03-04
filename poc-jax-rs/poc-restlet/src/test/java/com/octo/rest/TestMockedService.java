package com.octo.rest;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.octo.rest.jaxrs.restlet.MyJaxrsApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:WEB-INF/applicationContext.xml" })
public class TestMockedService {
	
	@Autowired
	private MyJaxrsApplication	jaxrsApplication;

	@Test
	public void testGet1() throws URISyntaxException, IOException, ServletException {
		Request request = new Request(Method.GET, "/someResource/1");
		request.getResourceRef().setBaseRef("/");
		request.setOriginalRef(request.getResourceRef());
		request.setRootRef(request.getResourceRef().getBaseRef());
		Response response = new Response(request);
		jaxrsApplication.getJaxRsRestlet().handle(request, response);

		assertEquals(HttpServletResponse.SC_OK, response.getStatus().getCode());

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
				response.getEntity().getText());
	}
}
