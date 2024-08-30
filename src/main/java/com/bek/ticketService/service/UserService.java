package com.bek.ticketService.service;

import com.bek.ticketService.dao.UserDAO;
import com.bek.ticketService.dao.UserDAOImpl;
import com.bek.ticketService.model.User;

public class UserService {
    UserDAO userDAO = new UserDAOImpl();

    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    public void deleteUserById(int id) {
        userDAO.deleteUserById(id);
    }

}
