package com.gk.app.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.glassfish.hk2.api.Factory;

public class EMFFactory implements Factory<EntityManagerFactory> {

	private final EntityManagerFactory emf;
	private static final String persistenceUnit = "gk";

	public EMFFactory() {
		emf = Persistence.createEntityManagerFactory(persistenceUnit);
	}

	@Override
	public EntityManagerFactory provide() {
		return emf;
	}

	@Override
	public void dispose(EntityManagerFactory arg0) {
		if (emf.isOpen())
			emf.close();
	}

}
