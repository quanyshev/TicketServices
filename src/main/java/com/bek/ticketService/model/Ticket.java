package com.bek.ticketService.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    private int user_id;
    @Column(name = "ticket_class")
    @Enumerated(EnumType.STRING)
    private TicketClass clazz;
    @Column(name = "ticket_type")
    @Enumerated(EnumType.STRING)
    private TicketType type;
    @Column(name = "creation_date")
    private LocalDate startDate = LocalDate.now();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public enum TicketType {DAY, WEEK, MONTH, YEAR};
    public enum TicketClass {BUS, SUBWAY, VEHICLE};

    public Ticket() {}

    public Ticket(User user, TicketClass clazz, TicketType type)  {
        this.user = user;
        this.type = type;
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", user_id=" + user.getId() +
                ", type=" + type +
                ", clazz=" + clazz +
                ", startDate=" + startDate +
                '}';
    }
}
