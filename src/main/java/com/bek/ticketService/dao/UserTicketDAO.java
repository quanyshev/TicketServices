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

    //    public void updateUserAndTicket(User user, Ticket ticket) {
//        String updateUser_sql = "UPDATE users SET name=? where id=?";
//        String updateTicket_sql = "UPDATE tickets SET type=? where id=?";
//        Connection connection = null;
//        Savepoint userUpdateSavePoint = null;
//
//        try {
//            connection = getConnection();
//            connection.setAutoCommit(false);
//
//            try (PreparedStatement preparedStatement = connection.prepareStatement(updateUser_sql)) {
//                preparedStatement.setString(1, user.getName());
//                preparedStatement.setInt(2, user.getId());
//                preparedStatement.executeUpdate();
//            }
//
//            userUpdateSavePoint = connection.setSavepoint();
//
//            try (PreparedStatement preparedStatement = connection.prepareStatement(updateTicket_sql)) {
//                preparedStatement.setObject(1, ticket.getType(), java.sql.Types.OTHER);
//                preparedStatement.setInt(2, ticket.getId());
//                preparedStatement.executeUpdate();
//            }
//
//            connection.commit();
//            System.out.println("Transaction committed successfully!");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//                try {
//                    connection.rollback(userUpdateSavePoint);
//                    System.out.println("Ticket type changing failed, but user name changed successfully!");
//                } catch(SQLException ex) {
//                    ex.printStackTrace();
//                }
//        } finally {
//            if (connection != null) {
//                try {
//                    connection.setAutoCommit(true);  // Restore auto-commit mode
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
