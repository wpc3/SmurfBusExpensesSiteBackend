package com.SamuelBusses.ExpensesTracker.Controllers;

import com.SamuelBusses.ExpensesTracker.Models.Expenses;
import com.SamuelBusses.ExpensesTracker.Service.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpensesController {

    private ExpensesService expensesService;

    public ExpensesController(@Autowired ExpensesService expensesService) {
        this.expensesService = expensesService;
    }

    @PostMapping("/expense")
    public ResponseEntity<Expenses> postAnExpense(@RequestBody Expenses expense){

        return new ResponseEntity<>(expensesService.createAnExpense(expense), HttpStatus.CREATED);
    }

    @PostMapping("/expenses")
    public ResponseEntity<List<Expenses>> postMultipleExpenses(@RequestBody List<Expenses> expenses){

        return new ResponseEntity<>(expensesService.createMultipleExpenses(expenses), HttpStatus.CREATED);
    }

    @GetMapping("/expense/{expenseId}")
    public ResponseEntity<Expenses> getExpensebyId(@PathVariable Long expenseId){

        return new ResponseEntity<>(expensesService.retrieveExpensesById(expenseId), HttpStatus.OK);
    }

    @GetMapping("/expenses")
    public ResponseEntity<List<Expenses>> getAllExpenses(){

        return new ResponseEntity<>(expensesService.retrieveMultipleExpenses(), HttpStatus.OK);
    }

    @PutMapping("/expense/{expenseId}")
    public ResponseEntity<Expenses> editAnExpense(@PathVariable Long expenseId,
                                                  @RequestBody Expenses expense){

        Expenses expenses = expensesService.updateAnExpense(expense,expenseId);
        return ResponseEntity.ok(expenses) ;
    }


}
