package org.oa.vshalimov.restaurant.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.oa.vshalimov.restaurant.data.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuRepository {

    private final SessionFactory sessionFactory;

    public MenuRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Menu> loadAll() {
        Session session = sessionFactory.openSession();
        List<Menu> menus = new ArrayList<>();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            menus = (List<Menu>) session.createQuery("FROM Menu").list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return menus;
    }

    public Menu findById(int itemId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Menu menu = null;
        try {
            transaction = session.beginTransaction();
            menu = (Menu) session.createQuery("FROM Menu WHERE menuId=" + itemId).uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return menu;
    }

    public List<Menu> findByDishType(int dishTypeId) {
        Session session = sessionFactory.openSession();
        List<Menu> menus = new ArrayList<>();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            menus = (List<Menu>) session.createQuery("FROM Menu WHERE menuDish.dishType.dishTypeId IN (FROM DishType WHERE dishTypeId=" + dishTypeId + ") ORDER BY menuDish.dishName").list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return menus;
    }

    public boolean create(Menu itemToCreate) {
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

    public boolean update(Menu itemToUpdate) {
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

    public boolean delete(Menu itemToDelete) {
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