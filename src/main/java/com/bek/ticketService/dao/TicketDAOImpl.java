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
//    private final String url = "jdbc:postgresql://localhost:5432/ticket_services";
//    private final String username = "postgres";
//    private final String password = "java";
//
//    protected Connection getConnection() {
//        Connection connection = null;
//
//        try {
//            connection = DriverManager.getConnection(url, username, password);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }
    @Override
    public void saveTicket(Ticket ticket) {
        try(Session session = sf.openSession()) {
            session.save(ticket);
            System.out.println("Ticket successfully saved in database. ID: " + ticket.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void saveTicket(Ticket ticket) {
//        String saveTicket_sql = "INSERT INTO tickets (user_id, clazz, type, creation_date) VALUES (?, ?, ?, ?)";
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(saveTicket_sql, Statement.RETURN_GENERATED_KEYS)) {
//            preparedStatement.setInt(1, ticket.getUser_id());
//            preparedStatement.setObject(2, ticket.getClazz(), java.sql.Types.OTHER);
//            preparedStatement.setObject(3, ticket.getType(), java.sql.Types.OTHER);
//            preparedStatement.setDate(4, Date.valueOf(ticket.getStartDate()));
//            preparedStatement.executeUpdate();
//            System.out.println("Ticket successfully inserted to database!");
//
//            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
//                if (generatedKeys.next()) {
//                    ticket.setId(generatedKeys.getInt(1));
//                    System.out.println("Your ticket id is " + generatedKeys.getInt(1));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
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

//    @Override
//    public Ticket getTicketByTicketId(int id) {
//        Ticket ticket = null;
//        String getTicketById_sql = "SELECT * FROM tickets WHERE id=?";
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(getTicketById_sql)) {
//            preparedStatement.setInt(1, id);
//
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                if (resultSet.next()) {
//                    int ticketId = resultSet.getInt(1);
//                    int userId = resultSet.getInt(2);
//                    String t_class = resultSet.getString(3);
//                    String t_type = resultSet.getString(4);
//                    Ticket.TicketClass ticketClass = Ticket.TicketClass.valueOf(t_class);
//                    Ticket.TicketType ticketType = Ticket.TicketType.valueOf(t_type);
//                    LocalDate startDate = resultSet.getDate(5).toLocalDate();
//
//                    ticket = new Ticket(ticketId, userId, ticketClass, ticketType, startDate);
//                    System.out.println("Successfully retrieved ticket with id " + id + " from database!");
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

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
//    @Override
//    public ArrayList<Ticket> getTicketByUserId(int id) {
//        ArrayList<Ticket> ticketList = new ArrayList<>();
//        Ticket ticket = null;
//        String getTicketByUserId_sql = "SELECT * FROM tickets WHERE user_id=?";
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(getTicketByUserId_sql)) {
//            preparedStatement.setInt(1, id);
//
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                if (resultSet.next()) {
//                    int ticketId = resultSet.getInt(1);
//                    int userId = resultSet.getInt(2);
//                    String t_class = resultSet.getString(3);
//                    String t_type = resultSet.getString(4);
//                    Ticket.TicketClass ticketClass = Ticket.TicketClass.valueOf(t_class);
//                    Ticket.TicketType ticketType = Ticket.TicketType.valueOf(t_type);
//                    LocalDate startDate = resultSet.getDate(5).toLocalDate();
//
//                    ticket = new Ticket(ticketId, userId, ticketClass, ticketType, startDate);
//                    ticketList.add(ticket);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        if (!ticketList.isEmpty()) {
//            System.out.println("Successfully retrieved tickets from database! Amount: " + ticketList.size());
//        } else {
//            System.out.println("No tickets found");
//        }
//        return null;
//    }
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
//    @Override
//    public void deleteTicketbyId(int id) {
//        String deleteUser_sql = "DELETE FROM tickets WHERE id=?";
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(deleteUser_sql)) {
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
//            System.out.println("Successfully deleted ticket with id " + id);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
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
//    @Override
//    public void updateTicketType(Ticket ticket, Ticket.TicketType ticketType) {
//        String updateTicketType = "UPDATE tickets SET type=? WHERE id=?";
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(updateTicketType)) {
//            preparedStatement.setObject(1, ticketType, java.sql.Types.OTHER);
//            preparedStatement.setInt(2, ticket.getId());
//            preparedStatement.executeUpdate();
//            System.out.println("Type of ticket successfully updated!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}


