package com.gk.app.config;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ResourceConfig;

import com.gk.app.config.controller.ApplicationController;
import com.gk.app.config.services.ApplicationService;

@ApplicationPath("/")
public class ApplicationConfig extends ResourceConfig {

	@Inject
	public ApplicationConfig() {
		packages( "com.gk.app.config");
		register(new AbstractBinder() {
			@Override
			public void configure() {

				bindFactory(EMFFactory.class).to(EntityManagerFactory.class).in(Singleton.class);
				bindFactory(EMFactory.class).to(EntityManager.class).in(RequestScoped.class);
				bind(new ApplicationService()).to(ApplicationService.class);
				bind(new ApplicationController()).to(ApplicationController.class);
			}
		});
	}
}
