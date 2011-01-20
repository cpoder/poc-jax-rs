package com.octo.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.jboss.resteasy.plugins.server.tjws.TJWSEmbeddedJaxrsServer;
import org.jboss.resteasy.plugins.spring.SpringBeanProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:WEB-INF/applicationContext.xml" })
public class TestService extends AbstractJUnit4SpringContextTests {
	@Test
	public void testGet1() {
		TJWSEmbeddedJaxrsServer server = new TJWSEmbeddedJaxrsServer();
		server.setPort(9088);
		server.getDeployment().getResources().add(applicationContext.getBean("someService"));
		server.start();
		
		SpringBeanProcessor processor = new SpringBeanProcessor(server.getDeployment().getDispatcher());
		((ConfigurableApplicationContext)applicationContext).addBeanFactoryPostProcessor(processor);

		URL url = null;
		try {
			url = new URL("http://localhost:9088/someService/1");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		assertNotNull(url);
		String result = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader((InputStream)url.getContent()));
			result = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertNotNull(result);
		
		assertEquals(
				"{\"otherBeans\":[{\"otherString\":\"coucou0\",\"otherInteger\":0},{\"otherString\":\"coucou1\",\"otherInteger\":1},{\"otherString\":\"coucou2\",\"otherInteger\":2},{\"otherString\":\"coucou3\",\"otherInteger\":3},{\"otherString\":\"coucou4\",\"otherInteger\":4},{\"otherString\":\"coucou5\",\"otherInteger\":5},{\"otherString\":\"coucou6\",\"otherInteger\":6},{\"otherString\":\"coucou7\",\"otherInteger\":7},{\"otherString\":\"coucou8\",\"otherInteger\":8},{\"otherString\":\"coucou9\",\"otherInteger\":9}],\"someInteger\":1,\"someString\":\"hello1\"}",
				result);
		
		server.stop();
	}
}
