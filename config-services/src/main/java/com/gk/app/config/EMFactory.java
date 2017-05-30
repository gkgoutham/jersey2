package com.gk.app.config;

import java.io.Closeable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.glassfish.hk2.api.Factory;
import org.glassfish.jersey.server.CloseableService;

public class EMFactory implements Factory<EntityManager> {

	@Inject
	private final EntityManagerFactory emf;
	private final CloseableService closeService;

	@Override
	public void dispose(EntityManager em) {
		if (em.isOpen()) {
			em.close();
		}
	}

	@Inject
	public EMFactory(EntityManagerFactory emf, CloseableService closeService) {
		this.emf = emf;
		this.closeService = closeService;
	}

	public EntityManager provide() {
		final EntityManager em = emf.createEntityManager();
		this.closeService.add(new Closeable() {
			@Override
			public void close() {
				em.close();
			}
		});
		return em;
	}

}
