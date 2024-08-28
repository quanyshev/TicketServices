package com.bek.ticketService.dao;

import com.bek.ticketService.model.Ticket;

import java.util.ArrayList;

public interface TicketDAO {
    public void saveTicket(Ticket ticket);
    public Ticket getTicketByTicketId(int id);
    public ArrayList<Ticket> getTicketByUserId(int id);
    public void deleteTicketbyId(int id);
    public void updateTicketType(Ticket ticket, Ticket.TicketType ticketType);
}
