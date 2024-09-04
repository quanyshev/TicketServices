package com.bek.ticketService.dao;

import com.bek.ticketService.hibernate.SessionFactoryProvider;
import com.bek.ticketService.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UserDAOImpl implements UserDAO {
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();
    @Override
    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            System.out.println("User successfully saved to database! ID: " + user.getId());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Failed to save to database! Transaction cancelled and rolled back.");
            e.printStackTrace();
        }
    }

    @Override
    public User getUserById(int id) {
        User user = null;
        try (Session session = sessionFactory.openSession()) {
            user = session.get(User.class, id);
            System.out.println("User successfully retrieved! ID: " + user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void deleteUserById(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            } else System.out.println("User is null.");
            System.out.println("User " + id + " successfully deleted");
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("Deletion failed. Rolled back.");
            }
            e.printStackTrace();
        }
    }
}
