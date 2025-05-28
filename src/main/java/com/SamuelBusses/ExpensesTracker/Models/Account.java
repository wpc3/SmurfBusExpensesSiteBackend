package com.SamuelBusses.ExpensesTracker.Models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String password;
    private String username;

    @OneToMany(mappedBy = "account")
    private Set<Expenses> expenses = new HashSet<>();


    public Account(){}

    public Account(Long id, String password, String username) {
        this.id = id;
        this.password = password;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Expenses> getExpenses() {
        return expenses;
    }

    public void setExpenses(Set<Expenses> expenses) {
        this.expenses = expenses;
    }


}
