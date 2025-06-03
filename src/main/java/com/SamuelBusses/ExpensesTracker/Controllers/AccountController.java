package com.SamuelBusses.ExpensesTracker.Controllers;

import com.SamuelBusses.ExpensesTracker.Exceptions.UnauthorizedException;
import com.SamuelBusses.ExpensesTracker.Models.Account;
import com.SamuelBusses.ExpensesTracker.Models.Expenses;
import com.SamuelBusses.ExpensesTracker.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    private AccountService accountService;

    public AccountController(@Autowired AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/account")
    public ResponseEntity<Account> postAccount(@RequestBody Account account){

        return new ResponseEntity<>(accountService.createAnAccount(account), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Account> postLogin(@RequestBody Account account){
        try {
            Account loggedInAccount = accountService.userLogin(account);
            return ResponseEntity.ok(loggedInAccount);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{accountId}/expenses")
    public ResponseEntity<Account> postExpense(@PathVariable Long accountId, @RequestBody Expenses expenses){

        return new ResponseEntity<>(accountService.saveAnExpenseToAccount(accountId,expenses), HttpStatus.OK);


    }



    @PutMapping("/account/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable long accountId, @RequestBody Account account){

        return new ResponseEntity<>(accountService.updateAnAccount(accountId,account), HttpStatus.OK);
    }


}
