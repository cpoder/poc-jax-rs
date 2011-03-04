package com.octo.rest.jaxrs.restlet;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Path;
import javax.ws.rs.core.Application;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.restlet.ext.jaxrs.InstantiateException;
import org.restlet.ext.jaxrs.JaxRsApplication;
import org.restlet.ext.jaxrs.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component("jaxrsApp")
public class MyJaxrsApplication extends JaxRsApplication {
	@Autowired
	public MyJaxrsApplication(final ApplicationContext context) {
		super();
		add(new Application() {
			private Set<Class<?>> classes = new HashSet<Class<?>>();
			private Set<Object> singletons = new HashSet<Object>();
			{
				if (context.getBeansWithAnnotation(Path.class) != null)
					for (Object bean : context.getBeansWithAnnotation(Path.class).values())
						classes.add(bean.getClass());
				singletons.add(new JacksonJsonProvider());
			}

			@Override
			public Set<Class<?>> getClasses() {
				return classes;
			}

			@Override
			public Set<Object> getSingletons() {
				return singletons;
			}
		});

		setObjectFactory(new ObjectFactory() {
			public <T> T getInstance(Class<T> jaxRsClass)
					throws InstantiateException {
				return context.getBean(jaxRsClass);
			}
		});
	}
}
