package com.example.ExpenseTrackerApp.Controller;

import com.example.ExpenseTrackerApp.Model.Expense;
import com.example.ExpenseTrackerApp.Service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class ExpenseController {
    @Autowired
    ExpenseService expenseService;

    @PostMapping("/expense")
    public String addExpense(@Valid @RequestBody Expense expense){
        return expenseService.addExpense(expense);
    }
}
