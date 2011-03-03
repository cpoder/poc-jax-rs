package com.octo.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.transport.servlet.ServletDestination;
import org.apache.cxf.transport.servlet.ServletTransportFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testMockContext.xml" })
public class TestMockedService {
	@Autowired
	private ServletTransportFactory servletTransportFactory;

	@Test
	public void testGet1() throws IOException, ServletException {
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/someResource/1");
		request.setContent(new byte[]{});
		MockHttpServletResponse response = new MockHttpServletResponse();
		ServletDestination destinationForPath = servletTransportFactory.getDestinationForPath("/");
		destinationForPath.invoke(new MockServletContext(), request, response);

		assertEquals(HttpServletResponse.SC_OK, response.getStatus());

		String result = response.getContentAsString();

		assertNotNull(result);
		
		assertEquals(
				"{\"otherBeans\":[{\"otherString\":\"coucou0\",\"otherInteger\":0},{\"otherString\":\"coucou1\",\"otherInteger\":1},{\"otherString\":\"coucou2\",\"otherInteger\":2},{\"otherString\":\"coucou3\",\"otherInteger\":3},{\"otherString\":\"coucou4\",\"otherInteger\":4},{\"otherString\":\"coucou5\",\"otherInteger\":5},{\"otherString\":\"coucou6\",\"otherInteger\":6},{\"otherString\":\"coucou7\",\"otherInteger\":7},{\"otherString\":\"coucou8\",\"otherInteger\":8},{\"otherString\":\"coucou9\",\"otherInteger\":9}],\"someInteger\":1,\"someString\":\"hello1\"}",
				result);
	}
}
