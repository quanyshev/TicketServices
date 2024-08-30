package com.bek.ticketService.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "creation_date")
    private LocalDate startDate = LocalDate.now();

    public User() {

    }
    public User(String name) {
        this.name = name;
    }

    public User(String name, int id, LocalDate startDate) {
        this.name = name;
        this.id = id;
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", startDate=" + startDate +
                '}';
    }

}
