package com.SamuelBusses.ExpensesTracker.Controllers;

import com.SamuelBusses.ExpensesTracker.CustomQueryInterfaces.CostOfExpenseByDate;
import com.SamuelBusses.ExpensesTracker.Models.Expenses;
import com.SamuelBusses.ExpensesTracker.Service.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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

    @GetMapping("/expenses/{month}/{year}")
    public ResponseEntity<List<CostOfExpenseByDate>> getExpenseByMonthYear(@PathVariable int month, @PathVariable int year){

        System.out.println("Received month: " + month + ", year: " + year);

        return new ResponseEntity<>(expensesService.retrieveExpensesByMonthAndYear(month,year), HttpStatus.OK);
    }

    @GetMapping("/expenses/{year}")
    public ResponseEntity<List<CostOfExpenseByDate>> getExpensesByYear(@PathVariable int year){

        return new ResponseEntity<>(expensesService.retrieveExpensesByYear(year),HttpStatus.OK);
    }

    @GetMapping("/expenses/category/{category}")
    public ResponseEntity<List<Expenses>> getExpensesByCategory(@PathVariable String category){

        return new ResponseEntity<>(expensesService.retrieveExpensesByCategory(category), HttpStatus.OK);
    }

    @GetMapping("/expenses/categories")
    public ResponseEntity<List<String>> getAllCategories(){
        try {
            List<String> categories = expensesService.retrieveCategories();
            if (categories.isEmpty()) {
                return ResponseEntity.noContent().build(); // Return 204 No Content if no categories found
            }
            return ResponseEntity.ok(categories); // Return 200 OK with categories in the body
        } catch (Exception e) {
            // Log the error
            System.out.println("Error fetching categories: " + e.getMessage());
            e.printStackTrace(); // Log the full stack trace for debugging

            // Return 500 Internal Server Error with a custom error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonList("Error fetching categories"));
        }
    }

    @PutMapping("/expense/{expenseId}")
    public ResponseEntity<Expenses> editAnExpense(@PathVariable Long expenseId,
                                                  @RequestBody Expenses expense){

        Expenses expenses = expensesService.updateAnExpense(expense,expenseId);
        return ResponseEntity.ok(expenses) ;
    }

    @DeleteMapping("/expense/{expenseId}")
    public ResponseEntity<Void> deleteAnExpense(@PathVariable Long expenseId){

        expensesService.removeAnExpense(expenseId);

        return ResponseEntity.noContent().build();
    }




}
