package com.gk.app.config.controller;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gk.app.config.model.Application;
import com.gk.app.config.services.ApplicationService;

@Path("/application")
public class ApplicationController {

	@Inject
	private ApplicationService appService;

	@Inject
	private EntityManager entityManager;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Application> getApplications() {
		entityManager.getTransaction().begin();
		List<Application> applications = entityManager.createQuery("SELECT app FROM application app").getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return applications;
	}

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Application createApplication(Application app) {
		return appService.createApplication(app);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Application getApplication(@PathParam("id") String appId) {
		return appService.getApplication(appId);
	}

	@DELETE
	@Path("/{id}")
	public void deleteApplication(@PathParam("id") String appId) {
		appService.deleteApplication(appId);
	}
}
