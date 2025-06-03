package com.SamuelBusses.ExpensesTracker.Service;

import com.SamuelBusses.ExpensesTracker.CustomQueryInterfaces.CostOfExpenseByDate;
import com.SamuelBusses.ExpensesTracker.Models.Account;
import com.SamuelBusses.ExpensesTracker.Models.Expenses;
import com.SamuelBusses.ExpensesTracker.Repositories.AccountRepository;
import com.SamuelBusses.ExpensesTracker.Repositories.ExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpensesService {

private ExpensesRepository expensesRepository;
private AccountRepository accountRepository;

    public ExpensesService(@Autowired ExpensesRepository expensesRepository, @Autowired AccountRepository accountRepository) {
        this.expensesRepository = expensesRepository;
        this.accountRepository = accountRepository;
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

    public List<Expenses> retrieveMultipleExpenses(){
        return (List<Expenses>) expensesRepository.findAll();
    }

    public List<Expenses> retrieveMultipleExpensesByAccount(long accountId){
        return (List<Expenses>) expensesRepository.findAllExpensesByAccountId(accountId);
    }


    public Expenses updateAnExpense(Expenses expenses, Long expenseId){

        Expenses expenses1 = expensesRepository.findById(expenseId).orElseThrow(() -> new RuntimeException("expense not found"));
        expenses1.setCategory(expenses.getCategory());
        expenses1.setCost(expenses.getCost());
        expenses1.setDescription(expenses.getDescription());
        expenses1.setMonth(expenses.getMonth());
        expenses1.setYear(expenses.getYear());
        expenses1.setAccount(expenses.getAccount());

        return expensesRepository.save(expenses1);
    }

    public void removeAnExpense(Long expenseId){

        Expenses expenses = expensesRepository.findById(expenseId).orElseThrow(() -> new RuntimeException("expense not found"));

        expensesRepository.delete(expenses);

        System.out.println("Expense id  " + expenseId + " has been deleted.");
    }

    public List<CostOfExpenseByDate> retrieveExpensesByMonthAndYear(int month, int year){

        return expensesRepository.findSumOfExpensesByMonthAndYear(month,year);
    }

    public List<CostOfExpenseByDate> retrieveExpensesByMonthAndYearAndAccount(int month, int year, long accountId){

        return expensesRepository.findSumOfExpensesByMonthAndYearAndAccount(month,year,accountId);
    }

    public List<CostOfExpenseByDate> retrieveExpensesByYearByAccount(int year, long accountId){

        return expensesRepository.findSumOfExpensesByYearByAccount(year,accountId);
    }


    public List<CostOfExpenseByDate> retrieveExpensesByYear(int year){

        return expensesRepository.findSumOfExpensesByYear(year);
    }


    public List<Expenses> retrieveExpensesByCategory(String category){

        return expensesRepository.findExpensesByCategory(category);
    }

    public List<Expenses> retrieveExpensesByCategoryByAccount(String category, long accountId){

        return expensesRepository.findExpensesByCategoryAndAccount(category, accountId);
    }


    public List<String> retrieveCategories(){
        return expensesRepository.findAllCategories();
    }

    public List<String> retrieveCategoriesbyAccount(long accountId){
        return expensesRepository.findAllCategoriesByAccount(accountId);
    }

    public Expenses addAccountToExpense(Long accountId, Expenses expenses){

        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("account id does not exist"));
        expenses.setAccount(account);

        return expensesRepository.save(expenses);

    }


}
