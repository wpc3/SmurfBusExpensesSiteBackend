package com.SamuelBusses.ExpensesTracker.Service;

import com.SamuelBusses.ExpensesTracker.Exceptions.UnauthorizedException;
import com.SamuelBusses.ExpensesTracker.Models.Account;
import com.SamuelBusses.ExpensesTracker.Models.Expenses;
import com.SamuelBusses.ExpensesTracker.Repositories.AccountRepository;
import com.SamuelBusses.ExpensesTracker.Repositories.ExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AccountService {

   private AccountRepository accountRepository;
   private ExpensesRepository expensesRepository;

    public AccountService(@Autowired AccountRepository accountRepository,
                          @Autowired ExpensesRepository expensesRepository) {
        this.accountRepository = accountRepository;
        this.expensesRepository = expensesRepository;
    }

    public Account createAnAccount(Account account){

        String username = account.getUsername();
        String password = account.getPassword();

        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username must not be blank.");
        }

        if (password == null || password.length() < 4) {
            throw new IllegalArgumentException("Password must be at least 4 characters.");
        }

        if(accountRepository.existsByUsername(username)){
            throw new IllegalArgumentException("usernameAlreadyExists");
        }

        return accountRepository.save(account);
    }

    public Account userLogin(Account account) {

        String username = account.getUsername();
        String password = account.getPassword();

        if (username == null || password == null) {

            throw new IllegalArgumentException("Username must not be blank.");
        }

        return accountRepository.findByUsername(username)
                .filter(acc -> acc.getPassword().equals(password))
                .orElseThrow(() -> new UnauthorizedException("Invalid username or password"));


    }

    public Account saveAnExpenseToAccount(Long accountId, Expenses expenses){

        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("account id does not exist"));
//        Expenses expenses = expensesRepository.findById(expenseId).orElseThrow(() -> new RuntimeException("expense id does not exist"));

       expenses.setAccount(account);

       account.getExpenses().add(expenses);


        return accountRepository.save(account);
    }

    public Account updateAnAccount(long accountId, Account account){

        Account accountToUpdate = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("account id does not exist"));

        accountToUpdate.setExpenses(account.getExpenses());
        accountToUpdate.setUsername(account.getUsername());
        accountToUpdate.setPassword(account.getPassword());


        return accountRepository.save(accountToUpdate);
    }










}
