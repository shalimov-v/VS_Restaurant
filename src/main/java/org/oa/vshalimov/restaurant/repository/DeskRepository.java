package org.oa.vshalimov.restaurant.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.oa.vshalimov.restaurant.data.Desk;

import java.util.ArrayList;
import java.util.List;

public class DeskRepository {

    private final SessionFactory sessionFactory;

    public DeskRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Desk> loadAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Desk> desks = new ArrayList<>();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            desks = (List<Desk>) session.createQuery("FROM Desk ORDER BY deskName").list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return desks;
    }

    public Desk findById(int itemId) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null;
        Desk desk = null;
        try {
            transaction = session.beginTransaction();
            desk = (Desk) session.createQuery("FROM Desk WHERE deskId=" + itemId).uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return desk;
    }

    public boolean create(Desk itemToCreate) {
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

    public boolean update(Desk itemToUpdate) {
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

    public boolean delete(Desk itemToDelete) {
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