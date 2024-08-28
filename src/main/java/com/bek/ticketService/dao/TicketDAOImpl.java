package com.bek.ticketService.dao;

import com.bek.ticketService.model.Ticket;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TicketDAOImpl implements TicketDAO {
    private final String url = "jdbc:postgresql://localhost:5432/ticket_services";
    private final String username = "postgres";
    private final String password = "java";

    protected Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void saveTicket(Ticket ticket) {
        String saveTicket_sql = "INSERT INTO tickets (user_id, clazz, type, creation_date) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(saveTicket_sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, ticket.getUser_id());
            preparedStatement.setObject(2, ticket.getClazz(), java.sql.Types.OTHER);
            preparedStatement.setObject(3, ticket.getType(), java.sql.Types.OTHER);
            preparedStatement.setDate(4, Date.valueOf(ticket.getStartDate()));
            preparedStatement.executeUpdate();
            System.out.println("Ticket successfully inserted to database!");

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    ticket.setId(generatedKeys.getInt(1));
                    System.out.println("Your ticket id is " + generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Ticket getTicketByTicketId(int id) {
        Ticket ticket = null;
        String getTicketById_sql = "SELECT * FROM tickets WHERE id=?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getTicketById_sql)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int ticketId = resultSet.getInt(1);
                    int userId = resultSet.getInt(2);
                    String t_class = resultSet.getString(3);
                    String t_type = resultSet.getString(4);
                    Ticket.TicketClass ticketClass = Ticket.TicketClass.valueOf(t_class);
                    Ticket.TicketType ticketType = Ticket.TicketType.valueOf(t_type);
                    LocalDate startDate = resultSet.getDate(5).toLocalDate();

                    ticket = new Ticket(ticketId, userId, ticketClass, ticketType, startDate);
                    System.out.println("Successfully retrieved ticket with id " + id + " from database!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticket;
    }

    @Override
    public ArrayList<Ticket> getTicketByUserId(int id) {
        ArrayList<Ticket> ticketList = new ArrayList<>();
        Ticket ticket = null;
        String getTicketByUserId_sql = "SELECT * FROM tickets WHERE user_id=?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getTicketByUserId_sql)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int ticketId = resultSet.getInt(1);
                    int userId = resultSet.getInt(2);
                    String t_class = resultSet.getString(3);
                    String t_type = resultSet.getString(4);
                    Ticket.TicketClass ticketClass = Ticket.TicketClass.valueOf(t_class);
                    Ticket.TicketType ticketType = Ticket.TicketType.valueOf(t_type);
                    LocalDate startDate = resultSet.getDate(5).toLocalDate();

                    ticket = new Ticket(ticketId, userId, ticketClass, ticketType, startDate);
                    ticketList.add(ticket);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!ticketList.isEmpty()) {
            System.out.println("Successfully retrieved tickets from database! Amount: " + ticketList.size());
        } else {
            System.out.println("No tickets found");
        }
        return ticketList;
    }

    @Override
    public void deleteTicketbyId(int id) {
        String deleteUser_sql = "DELETE FROM tickets WHERE id=?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteUser_sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Successfully deleted ticket with id " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTicketType(Ticket ticket, Ticket.TicketType ticketType) {
        String updateTicketType = "UPDATE tickets SET type=? WHERE id=?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateTicketType)) {
            preparedStatement.setObject(1, ticketType, java.sql.Types.OTHER);
            preparedStatement.setInt(2, ticket.getId());
            preparedStatement.executeUpdate();
            System.out.println("Type of ticket successfully updated!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


