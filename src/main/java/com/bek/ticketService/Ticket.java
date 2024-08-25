package com.bek.ticketService;
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
    private final LocalDate startDate = LocalDate.now();

    public Ticket(TicketType type, TicketClass clazz) {
        this.type = type;
        this.clazz = clazz;
    }

    public enum TicketType {DAY, WEEK, MONTH, YEAR};
    public enum TicketClass {BUS, SUBWAY, VEHICLE};
}
