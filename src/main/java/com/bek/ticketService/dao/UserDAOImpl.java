package com.bek.ticketService.dao;

import com.bek.ticketService.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UserDAOImpl implements UserDAO {
    private final Configuration conf = new Configuration().configure().addAnnotatedClass(User.class);
    private final SessionFactory sf = conf.buildSessionFactory();

//    protected Connection getConnection() {
//        String url = "jdbc:postgresql://localhost:5432/ticket_services";
//        String username = "postgres";
//        String password = "java";
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
    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = sf.openSession()) {
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

//    @Override
//    public void saveUser(User user) {
//        String saveUser_sql = "INSERT INTO users (name, creation_date) VALUES (?, ?)";
//
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(saveUser_sql, Statement.RETURN_GENERATED_KEYS)) {
//            preparedStatement.setString(1, user.getName());
//            preparedStatement.setDate(2, Date.valueOf(user.getStartDate()));
//            preparedStatement.executeUpdate();
//            System.out.println("User successfully inserted to database!");
//
//            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
//                if (generatedKeys.next()) {
//                    user.setId(generatedKeys.getInt(1));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
    @Override
    public User getUserById(int id) {
        User user = null;
        try (Session session = sf.openSession()) {
            user = session.get(User.class, id);
            System.out.println("User successfully retrieved! ID: " + user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

//    @Override
//    public User getUserById(int id) {
//        User user = null;
//        String getUser_sql = "SELECT * FROM users WHERE id=?";
//
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(getUser_sql)) {
//            preparedStatement.setInt(1, id);
//
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                if(resultSet.next()) {
//                    user = new User(resultSet.getString(2), resultSet.getInt(1),
//                            resultSet.getDate(3).toLocalDate());
//                    System.out.println("User with id " + id + " successfully retrieved from database!");
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    @Override
    public void deleteUserById(int id) {
        Transaction transaction = null;
        try (Session session = sf.openSession()) {
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
//    @Override
//    public void deleteUserById(int id) {
//        String deleteUser_sql = "DELETE FROM users WHERE id=?";
//
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(deleteUser_sql)) {
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
//            System.out.println("User with id " + id + " successfully deleted from database!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
