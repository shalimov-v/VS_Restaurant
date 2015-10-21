package org.oa.vshalimov.restaurant.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.oa.vshalimov.restaurant.data.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    private final SessionFactory sessionFactory;

    public EmployeeRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Employee> loadAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Employee> employees = new ArrayList<>();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            employees = (List<Employee>) session.createQuery("FROM Employee ORDER BY employeeLastName").list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return employees;
    }

    public Employee findById(int itemId) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null;
        Employee employee = null;
        try {
            transaction = session.beginTransaction();
            employee = (Employee) session.createQuery("FROM Employee WHERE employeeId=" + itemId).uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return employee;
    }

    public boolean create(Employee itemToCreate) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(itemToCreate);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(Employee itemToUpdate) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(itemToUpdate);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(Employee itemToDelete) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(itemToDelete);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
