package com.octo.rest;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

@Path("/someResource")
@Service
public class SomeService {
	@Resource
	private BeanRepo beanRepo;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<SomeBean> getSomeBeans() {
		return beanRepo.getAll();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{index}")
	public SomeBean getSomeBean(@PathParam("index") int index) {
		return beanRepo.get(index);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void saveSomeBean(SomeBean someBean) {
		beanRepo.save(someBean);
	}
}
