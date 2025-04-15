package com.SamuelBusses.ExpensesTracker.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Expenses {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long expenseId;

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
        return category;
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
}
