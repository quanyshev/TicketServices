package com.bek.ticketService.dao;

import com.bek.ticketService.model.Ticket;
import com.bek.ticketService.model.User;

public interface UserDAO {
    public void saveUser(User user);
    public User getUserById(int id);
    public void deleteUserById(int id);
    public void updateUserAndTicket(User user, Ticket ticket);

}
