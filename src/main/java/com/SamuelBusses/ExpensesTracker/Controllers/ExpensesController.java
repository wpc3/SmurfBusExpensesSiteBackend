package com.SamuelBusses.ExpensesTracker.Controllers;

import com.SamuelBusses.ExpensesTracker.Models.Expenses;
import com.SamuelBusses.ExpensesTracker.Service.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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


}
