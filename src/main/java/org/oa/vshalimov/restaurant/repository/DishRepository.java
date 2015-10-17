package org.oa.vshalimov.restaurant.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.oa.vshalimov.restaurant.data.Dish;

import java.util.ArrayList;
import java.util.List;

public class DishRepository {

    private final SessionFactory sessionFactory;

    public DishRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Dish> loadAll() {
        Session session = sessionFactory.openSession();
        List<Dish> dishes = new ArrayList<>();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            dishes = (List<Dish>) session.createQuery("FROM Dish ORDER BY dishType.dishTypeId, dishName").list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return dishes;
    }

    public Dish findById(int itemId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Dish dish = null;
        try {
            transaction = session.beginTransaction();
            dish = (Dish) session.createQuery("FROM Dish WHERE dishId=" + itemId).uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return dish;
    }

    public boolean create(Dish itemToCreate) {
        Session session = sessionFactory.openSession();
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

    public boolean update(Dish itemToUpdate) {
        Session session = sessionFactory.openSession();
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

    public boolean delete(Dish itemToDelete) {
        Session session = sessionFactory.openSession();
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