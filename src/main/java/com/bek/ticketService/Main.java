package com.bek.ticketService;


public class Main {

   public static void main(String[] args) {
       User user1 = new User("Anton");

       System.out.println(user1.getName());
       System.out.println(user1.getStartDate());

       user1.getTicket(Ticket.TicketType.YEAR, Ticket.TicketClass.BUS);
   }
}