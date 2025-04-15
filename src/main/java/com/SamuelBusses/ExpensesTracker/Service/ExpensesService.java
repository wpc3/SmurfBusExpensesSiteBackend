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

    public Expenses updateAnExpense(Expenses expenses, Long expenseId){

        Expenses expenses1 = expensesRepository.findById(expenseId).orElseThrow(() -> new RuntimeException("expense not found"));
        expenses1.setCategory(expenses.getCategory());
        expenses1.setCost(expenses.getCost());
        expenses1.setDescription(expenses.getDescription());
        expenses1.setMonth(expenses.getMonth());

        return expenses1;
    }

    public void removeAnExpense(Long expenseId){

        Expenses expenses = expensesRepository.findById(expenseId).orElseThrow(() -> new RuntimeException("expense not found"));

        expensesRepository.delete(expenses);

        System.out.println(expenses + "Has been deleted");
    }





}
