package org.oa.vshalimov.restaurant.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class FacadeRepository {

    private static final FacadeRepository INSTANCE = new FacadeRepository();

    private static SessionFactory sessionFactory;

    private EmployeeRepository employeeRepository;

    private FacadeRepository() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable e) {
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static FacadeRepository getInstance() {
        return INSTANCE;
    }

    public EmployeeRepository getEmployeeRepository() {
        if (employeeRepository == null) {
            employeeRepository = new EmployeeRepository(sessionFactory);
        }
        return employeeRepository;
    }
}