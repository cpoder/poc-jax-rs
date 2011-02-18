package com.octo.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.web.context.ContextLoaderListener;

import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import com.sun.jersey.test.framework.spi.container.grizzly.web.GrizzlyWebTestContainerFactory;

public class TestService extends JerseyTest {
	public TestService() {
		// Use EmbeddedGlassFishTestContainerFactory for GlassFish
		super(new GrizzlyWebTestContainerFactory());
	}
	
	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder()
        .contextPath("poc-jersey")
        .servletClass(SpringServlet.class)
        .initParam("com.sun.jersey.api.json.POJOMappingFeature", "true")
        .contextListenerClass(ContextLoaderListener.class)
        .build();
	}

	@Test
	public void testGet1() {
		String result = resource().path("/someResource/1").get(String.class);
		assertEquals("{\"otherBeans\":[{\"otherString\":\"coucou0\",\"otherInteger\":0},{\"otherString\":\"coucou1\",\"otherInteger\":1},{\"otherString\":\"coucou2\",\"otherInteger\":2},{\"otherString\":\"coucou3\",\"otherInteger\":3},{\"otherString\":\"coucou4\",\"otherInteger\":4},{\"otherString\":\"coucou5\",\"otherInteger\":5},{\"otherString\":\"coucou6\",\"otherInteger\":6},{\"otherString\":\"coucou7\",\"otherInteger\":7},{\"otherString\":\"coucou8\",\"otherInteger\":8},{\"otherString\":\"coucou9\",\"otherInteger\":9}],\"someInteger\":1,\"someString\":\"hello1\"}", result);
	}
}
