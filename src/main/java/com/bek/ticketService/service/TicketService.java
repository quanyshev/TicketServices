package com.bek.ticketService.service;

import com.bek.ticketService.dao.TicketDAO;
import com.bek.ticketService.dao.TicketDAOImpl;
import com.bek.ticketService.model.Ticket;
import com.bek.ticketService.model.User;

import java.util.ArrayList;

public class TicketService {
    TicketDAO ticketDAO = new TicketDAOImpl();

    public void buyTicket(User user, Ticket.TicketClass ticketClass, Ticket.TicketType ticketType) {
        Ticket ticket = new Ticket(user ,ticketClass, ticketType);
        ticketDAO.saveTicket(ticket);
        user.addTicket(ticket);
    }

    public Ticket getTicketByTicketId(int id) {
        return ticketDAO.getTicketByTicketId(id);
    }

    public ArrayList<Ticket> getTicketByUserId(int id) {
        return ticketDAO.getTicketByUserId(id);
    }

    public void deleteTicketbyId(int id) {
        ticketDAO.deleteTicketbyId(id);
    }

    public void updateTicketType(Ticket ticket, Ticket.TicketType ticketType) {
        ticketDAO.updateTicketType(ticket, ticketType);
    }
}

