package com.octo.rest;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

@Path("/someService")
@Produces(MediaType.APPLICATION_JSON)
@Service
public class SomeService {
	@Resource
	private BeanRepo beanRepo;

	@GET
	public List<SomeBean> getSomeBeans() {
		return beanRepo.getAll();
	}
	
	@GET
	@Path("{index}")
	public SomeBean getSomeBean(@PathParam("index") int index) {
		return beanRepo.get(index);
	}
	
	@PUT
	public void saveSomeBean(SomeBean someBean) {
		beanRepo.save(someBean);
	}
}
