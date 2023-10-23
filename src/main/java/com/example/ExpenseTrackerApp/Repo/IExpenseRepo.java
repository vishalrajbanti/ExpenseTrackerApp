package com.example.ExpenseTrackerApp.Repo;

import com.example.ExpenseTrackerApp.Model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IExpenseRepo extends JpaRepository<Expense,Long> {


    List<Expense> findByDate(LocalDate date);

    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
