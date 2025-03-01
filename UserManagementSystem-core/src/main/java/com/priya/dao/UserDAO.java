package com.priya.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.priya.model.User;
import com.priya.util.HibernateUtil;

public class UserDAO {
	
    private static SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

	public void addUser(User user) throws Exception {

		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.persist(user);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new Exception("Failed to add user.", e);
		} finally {
			session.close();
		}
	}

	public void updateUser(User user) throws Exception {

		Session session = null;
	    Transaction transaction = null;
	    try {
	        sessionFactory = HibernateUtil.buildSessionFactory();
	        session = sessionFactory.openSession();
	        transaction = session.beginTransaction();
	        session.merge(user);
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        throw new Exception("Failed to update user.", e);
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	}

	public void deleteUser(int id) throws Exception {

		Session session = null;
	    Transaction transaction = null;
	    try {
	        session = sessionFactory.openSession();
	        transaction = session.beginTransaction();
	        User user = session.get(User.class, id);
	        if (user != null) {
	            session.remove(user);
	            transaction.commit();
	        } else {
	            throw new Exception("User not found with ID: " + id);
	        }
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        throw new Exception("Failed to delete user.", e);
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	}

	public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User", User.class);
            return query.list();
        }
    }
}