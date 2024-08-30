package com.bek.ticketService.dao;

import com.bek.ticketService.model.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class TicketDAOImpl implements TicketDAO {
    Configuration conf = new Configuration().configure().addAnnotatedClass(Ticket.class);
    SessionFactory sf = conf.buildSessionFactory();

    @Override
    public void saveTicket(Ticket ticket) {
        try(Session session = sf.openSession()) {
            session.save(ticket);
            System.out.println("Ticket successfully saved in database. ID: " + ticket.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Ticket getTicketByTicketId(int id) {
        Ticket ticket = null;
        try(Session session = sf.openSession()) {
            ticket = session.get(Ticket.class, id);
            System.out.println("Ticket " + id + " successfully retrieved!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticket;
    }


    @Override
    public ArrayList<Ticket> getTicketByUserId(int id) {
        ArrayList<Ticket> tickets = new ArrayList<>();
        try (Session session = sf.openSession()) {
            String hql = "FROM Ticket WHERE user_id = :id";
            List<Ticket> list = session.createQuery(hql, Ticket.class).setParameter("id", id).list();
            tickets.addAll(list);
            if (!tickets.isEmpty()) {
                System.out.println("Tickets with userID " + id + " successfully retrieved");
            } else {
                System.out.println("No tickets found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public void deleteTicketbyId(int id) {
        Transaction transaction = null;

        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            session.delete(ticket);
            transaction.commit();
            System.out.println("Ticket " + id + " successfully deleted!");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Transaction failed. Rolled back.");
        }
    }

    @Override
    public void updateTicketType(Ticket ticket, Ticket.TicketType ticketType) {
        Transaction transaction = null;
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            ticket.setType(ticketType);
            session.update(ticket);
            transaction.commit();
            System.out.println("Type of ticket " + ticket.getId() + " successfully updated");
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }
}


