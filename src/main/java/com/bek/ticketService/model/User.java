package com.bek.ticketService.model;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class User {
    private String name;
    private int id;
    private LocalDate startDate = LocalDate.now();

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
