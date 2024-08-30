package com.bek.ticketService.service;

import com.bek.ticketService.dao.UserTicketDAO;
import com.bek.ticketService.model.Ticket;
import com.bek.ticketService.model.User;

public class UserTicketService {
    UserTicketDAO userTicketDAO = new UserTicketDAO();
    public void updateUserAndTicket(User user, String newUserName, int ticketID, Ticket.TicketClass ticketClass, Ticket.TicketType ticketType) {
        userTicketDAO.updateUserAndTicket(user, newUserName, ticketID, ticketClass, ticketType);
    }
}
