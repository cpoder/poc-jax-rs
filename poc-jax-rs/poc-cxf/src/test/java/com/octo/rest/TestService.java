package com.octo.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.cxf.jaxrs.spring.JAXRSServerFactoryBeanDefinitionParser.SpringJAXRSServerFactoryBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testContext.xml" })
public class TestService {
	@Autowired
	private SpringJAXRSServerFactoryBean someService;
	
	@Before
	public void before() {
		someService.setAddress("http://localhost:9080/");
		someService.create();
	}

	@Test
	public void testGet1() {
		URL url = null;
		try {
			url = new URL("http://localhost:9080/someResource/1");
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
	}
}
