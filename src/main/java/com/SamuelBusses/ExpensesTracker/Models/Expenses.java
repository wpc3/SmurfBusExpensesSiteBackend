package com.SamuelBusses.ExpensesTracker.Models;

import jakarta.persistence.*;

import java.util.Locale;

@Entity
public class Expenses {

@Id
@GeneratedValue(strategy =  GenerationType.IDENTITY)
private Long expenseId;

@ManyToOne
@JoinColumn(name = "accountId")
private Account account;

private String category;
private Double cost;
private String description;
private int month;
private int year;

    public Expenses(Long expenseId, String category, Double cost, String description, int month, int year) {
        this.expenseId = expenseId;
        this.category = category;
        this.cost = cost;
        this.description = description;
        this.month = month;
        this.year = year;
    }

    public Expenses(){}

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public String getCategory() {
        return category.toLowerCase();
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
