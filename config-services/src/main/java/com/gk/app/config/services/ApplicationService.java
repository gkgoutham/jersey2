package com.gk.app.config.services;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.gk.app.config.model.Application;

public class ApplicationService {

	@Inject
	private EntityManager entityManager;

	public List<Application> getApplications() {
		entityManager.getTransaction().begin();
		List<Application> applications = entityManager.createQuery("SELECT app FROM application app").getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return applications;
	}

	public Application createApplication(Application app) {
		entityManager.getTransaction().begin();
		entityManager.persist(app);
		entityManager.getTransaction().commit();
		Application application = getApplication(app.getId());
		entityManager.close();
		return application;
	}

	public Application getApplication(String appId) {
		entityManager.getTransaction().begin();
		Application application = entityManager.find(Application.class, appId);
		entityManager.getTransaction().commit();
		entityManager.close();
		return application;
	}

	public void deleteApplication(String appId) {
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.find(Application.class, appId));
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
