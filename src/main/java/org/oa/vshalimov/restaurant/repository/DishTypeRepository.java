package org.oa.vshalimov.restaurant.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.oa.vshalimov.restaurant.data.DishType;

import java.util.ArrayList;
import java.util.List;

public class DishTypeRepository {

    private final SessionFactory sessionFactory;

    public DishTypeRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<DishType> loadAll() {
        Session session = sessionFactory.getCurrentSession();
        List<DishType> dishTypes = new ArrayList<>();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            dishTypes = (List<DishType>) session.createQuery("FROM DishType ORDER BY dishTypeName").list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return dishTypes;
    }

    public DishType findById(int itemId) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null;
        DishType dishType = null;
        try {
            transaction = session.beginTransaction();
            dishType = (DishType) session.createQuery("FROM DishType WHERE dishTypeId=" + itemId).uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return dishType;
    }

    public boolean create(DishType itemToCreate) {
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

    public boolean update(DishType itemToUpdate) {
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

    public boolean delete(DishType itemToDelete) {
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