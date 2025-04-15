package com.SamuelBusses.ExpensesTracker.Service;

import com.SamuelBusses.ExpensesTracker.Models.Expenses;
import com.SamuelBusses.ExpensesTracker.Repositories.ExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpensesService {

private ExpensesRepository expensesRepository;

    public ExpensesService(@Autowired ExpensesRepository expensesRepository) {
        this.expensesRepository = expensesRepository;
    }

  public Expenses createAnExpense(Expenses expense){

        return expensesRepository.save(expense);
  }

  public List<Expenses> createMultipleExpenses(List<Expenses> expenses){
        return (List<Expenses>) expensesRepository.saveAll(expenses);
  }

  public Expenses retrieveExpensesById(Long expenseId){

        return expensesRepository.findById(expenseId).orElseThrow(() -> new RuntimeException("expense not found"));
  }

    public List<Expenses> retrieveMultipleExpenses(List<Expenses> expenses){
        return (List<Expenses>) expensesRepository.findAll();
    }





}
