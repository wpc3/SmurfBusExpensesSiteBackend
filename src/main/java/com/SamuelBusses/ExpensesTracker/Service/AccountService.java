package com.SamuelBusses.ExpensesTracker.Service;

import com.SamuelBusses.ExpensesTracker.Exceptions.UnauthorizedException;
import com.SamuelBusses.ExpensesTracker.Models.Account;
import com.SamuelBusses.ExpensesTracker.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

   private AccountRepository accountRepository;

    public AccountService(@Autowired AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
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










}
