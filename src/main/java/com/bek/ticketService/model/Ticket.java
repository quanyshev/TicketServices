package com.bek.ticketService.model;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Ticket {
    private int id;
    private int user_id;
    private TicketType type;
    private TicketClass clazz;
    private LocalDate startDate = LocalDate.now();

    public Ticket(User user, TicketClass clazz, TicketType type)  {
        this.type = type;
        this.clazz = clazz;
        this.user_id = user.getId();
    }

//    public Ticket(int user_id, TicketClass clazz, TicketType type)  {
//        this.type = type;
//        this.clazz = clazz;
//        this.user_id = user_id;
//    }


    public Ticket(int id, int user_id, TicketClass clazz, TicketType type, LocalDate startDate) {
        this.id = id;
        this.user_id = user_id;
        this.type = type;
        this.clazz = clazz;
        this.startDate = startDate;
    }

    public enum TicketType {DAY, WEEK, MONTH, YEAR};
    public enum TicketClass {BUS, SUBWAY, VEHICLE};

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", type=" + type +
                ", clazz=" + clazz +
                ", startDate=" + startDate +
                '}';
    }
}
