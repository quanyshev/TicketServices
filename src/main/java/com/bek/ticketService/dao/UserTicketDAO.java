package com.bek.ticketService.dao;

import com.bek.ticketService.model.Ticket;
import com.bek.ticketService.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UserTicketDAO {
    private final Configuration conf = new Configuration().configure().addAnnotatedClass(User.class).addAnnotatedClass(Ticket.class);
    private final SessionFactory sf = conf.buildSessionFactory();
    public void updateUserAndTicket(User user, String newUserName, int ticketID, Ticket.TicketClass ticketClass, Ticket.TicketType ticketType) {
        Transaction transaction = null;

        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            user.setName(newUserName);
            session.update(user);
            Ticket ticket = session.get(Ticket.class, ticketID);
            ticket.setClazz(ticketClass);
            ticket.setType(ticketType);
            transaction.commit();
            System.out.println("Name and ticket type, class of user " + user.getName() + " successfully updated!");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
                System.out.println("Transaction failed. Rolled back.");
            }
        }
    }
}
