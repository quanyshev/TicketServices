package com.bek.ticketService.dao;

import com.bek.ticketService.model.Ticket;
import com.bek.ticketService.model.User;

import java.sql.*;

public class UserDAOImpl implements UserDAO {

    protected Connection getConnection() {
        String url = "jdbc:postgresql://localhost:5432/ticket_services";
        String username = "postgres";
        String password = "java";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void saveUser(User user) {
        String saveUser_sql = "INSERT INTO users (name, creation_date) VALUES (?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(saveUser_sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setDate(2, Date.valueOf(user.getStartDate()));
            preparedStatement.executeUpdate();
            System.out.println("User successfully inserted to database!");

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public User getUserById(int id) {
        User user = null;
        String getUser_sql = "SELECT * FROM users WHERE id=?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getUser_sql)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    user = new User(resultSet.getString(2), resultSet.getInt(1),
                            resultSet.getDate(3).toLocalDate());
                    System.out.println("User with id " + id + " successfully retrieved from database!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void deleteUserById(int id) {
        String deleteUser_sql = "DELETE FROM users WHERE id=?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteUser_sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User with id " + id + " successfully deleted from database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUserAndTicket(User user, Ticket ticket) {
        String updateUser_sql = "UPDATE users SET name=? where id=?";
        String updateTicket_sql = "UPDATE tickets SET type=? where id=?";
        Connection connection = null;
        Savepoint userUpdateSavePoint = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateUser_sql)) {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setInt(2, user.getId());
                preparedStatement.executeUpdate();
            }

            userUpdateSavePoint = connection.setSavepoint();

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateTicket_sql)) {
                preparedStatement.setObject(1, ticket.getType(), java.sql.Types.OTHER);
                preparedStatement.setInt(2, ticket.getId());
                preparedStatement.executeUpdate();
            }

            connection.commit();
            System.out.println("Transaction committed successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
                try {
                    connection.rollback(userUpdateSavePoint);
                    System.out.println("Ticket type changing failed, but user name changed successfully!");
                } catch(SQLException ex) {
                    ex.printStackTrace();
                }
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);  // Restore auto-commit mode
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
