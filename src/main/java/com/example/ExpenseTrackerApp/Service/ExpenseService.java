package com.example.ExpenseTrackerApp.Service;

import com.example.ExpenseTrackerApp.Model.Expense;
import com.example.ExpenseTrackerApp.Repo.IExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    IExpenseRepo iExpenseRepo;



    public List<Expense> getExpense(LocalDate date) {
        return iExpenseRepo.findByDate(date);
    }

    public List<Expense> getExpenseForMonth(int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);
     return iExpenseRepo.findByDateBetween(startDate,endDate);
    }

    public String addExpense(Expense expense) {
         iExpenseRepo.save(expense);
         return "expense added";
    }
}
