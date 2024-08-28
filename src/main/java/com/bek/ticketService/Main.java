package com.bek.ticketService;

import com.bek.ticketService.model.Ticket;
import com.bek.ticketService.model.User;
import com.bek.ticketService.service.TicketService;
import com.bek.ticketService.service.UserService;

import java.util.ArrayList;

public class Main {

   public static void main(String[] args) {

       UserService userService = new UserService();

       // >> check saveUser method
//       User user = new User("Abzal");
//       userService.saveUser(user);
//       System.out.println(user.getId());

       // >> check getUserById method
//       User userFromDB = userService.getUserById(2);
//       System.out.println(userFromDB.toString());

       // >> check deleteUserById method
       //userService.deleteUserById(14);

       TicketService ticketService = new TicketService();

       // >> check ticket buy method
//       User user = new User("Serik");
//       userService.saveUser(user);
//       ticketService.buyTicket(userFromDB, Ticket.TicketClass.VEHICLE, Ticket.TicketType.YEAR);

//        >> check getTicketById service
//       Ticket ticket = ticketService.getTicketByTicketId(7);
//       System.out.println(ticket);

       // >> check getTicketByUserId service
//       ArrayList<Ticket> ticketList = ticketService.getTicketByUserId(11);
//
//       for (Ticket t : ticketList) {
//           System.out.println(t.toString());
//       }

       // >> check deleteTicketById
//       ticketService.deleteTicketbyId(2);
//       ticketService.deleteTicketbyId(3);

       // >> check updateTicketType
//       ticketService.updateTicketType(ticket, Ticket.TicketType.MONTH);

       // >> check updateUserAndTicket transaction method
//       User u = userService.getUserById(2);
//       Ticket t = ticketService.getTicketByTicketId(7);
//
//       System.out.println(u.toString());
//       System.out.println(t.toString());
//
//       u.setName("Patrik");
//       t.setType(Ticket.TicketType.YEAR);
//
//       userService.updateUserAndTicket(u, t);

   }
}