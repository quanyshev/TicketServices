package com.bek.ticketService;

import com.bek.ticketService.service.TicketService;
import com.bek.ticketService.service.UserService;
import com.bek.ticketService.service.UserTicketService;

public class Main {

   public static void main(String[] args) {
       // HIBERNATE check
       UserService userService = new UserService();

       // >> check saveUser
//       User user1 = new User("Bekzat");
//       userService.saveUser(user1);
//       System.out.println(user1.getId());

       // >> check getUserById
//       User user2 = userService.getUserById(29);

       // >> check deleteUserById
//        userService.deleteUserById(24);

       UserTicketService userTicketService = new UserTicketService();

       // >> check updateUserAndTicket
//       User user4 = userService.getUserById(29);
//       userTicketService.updateUserAndTicket(user4, "Daniyar", 9, Ticket.TicketClass.VEHICLE, Ticket.TicketType.YEAR);

       TicketService ticketService = new TicketService();

       // >> check saveTicket
//       User user3 = new User("Bekasyl");
//       userService.saveUser(user3);
//       ticketService.buyTicket(user3, Ticket.TicketClass.SUBWAY, Ticket.TicketType.WEEK);

       // >> check getTicketById
//       Ticket ticket1 = ticketService.getTicketByTicketId(8);
//       System.out.println(ticket1.toString());

       // >> check getTicketByUserId
//       ArrayList<Ticket> tickets = ticketService.getTicketByUserId(29);
//       for (Ticket t : tickets) {
//           System.out.println(t.toString());
//       }

       // >> check deleteTicketById
//       ticketService.deleteTicketbyId(6);

       // >> check updateTicketType
//       Ticket ticket = ticketService.getTicketByTicketId(7);
//       ticketService.updateTicketType(ticket, Ticket.TicketType.DAY);
   }
}