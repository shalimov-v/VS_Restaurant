package org.oa.vshalimov.restaurant.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class FacadeRepository {

    private static final FacadeRepository INSTANCE = new FacadeRepository();

    private static SessionFactory sessionFactory;

    private EmployeeRepository employeeRepository;
    private DeskRepository deskRepository;
    private DishTypeRepository dishTypeRepository;
    private DishRepository dishRepository;
    private MenuRepository menuRepository;
    private DiscountRepository discountRepository;
    private ClientRepository clientRepository;

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

    public EmployeeRepository getEmployeeRepository() {
        if (employeeRepository == null) {
            employeeRepository = new EmployeeRepository(sessionFactory);
        }
        return employeeRepository;
    }

    public DeskRepository getDeskRepository() {
        if (deskRepository == null) {
            deskRepository = new DeskRepository(sessionFactory);
        }
        return deskRepository;
    }

    public DishTypeRepository getDishTypeRepository() {
        if (dishTypeRepository == null) {
            dishTypeRepository = new DishTypeRepository(sessionFactory);
        }
        return dishTypeRepository;
    }

    public DishRepository getDishRepository() {
        if (dishRepository == null) {
            dishRepository = new DishRepository(sessionFactory);
        }
        return dishRepository;
    }

    public MenuRepository getMenuRepository() {
        if (menuRepository == null) {
            menuRepository = new MenuRepository(sessionFactory);
        }
        return menuRepository;
    }

    public DiscountRepository getDiscountRepository() {
        if (discountRepository == null) {
            discountRepository = new DiscountRepository(sessionFactory);
        }
        return discountRepository;
    }

    public ClientRepository getClientRepository() {
        if (clientRepository == null) {
            clientRepository = new ClientRepository(sessionFactory);
        }
        return clientRepository;
    }

    public void destroySession() {
        sessionFactory.close();
    }
}