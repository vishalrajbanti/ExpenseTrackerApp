package com.example.ExpenseTrackerApp.Controller;

import com.example.ExpenseTrackerApp.Model.Dto.AuthenticationInputDto;
import com.example.ExpenseTrackerApp.Model.Dto.SignInInputDto;
import com.example.ExpenseTrackerApp.Model.Expense;
import com.example.ExpenseTrackerApp.Model.User;
import com.example.ExpenseTrackerApp.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping("user/signUp")
    public String userSignUp(@Valid @RequestBody User user){
        return userService.userSignUp(user);
    }

    @PostMapping("user/signIn")
    public String userSignIn(@RequestBody SignInInputDto signInInput)
    {
        return userService.userSignIn(signInInput);
    }

    @DeleteMapping("user/signOut")
    public String userSignOut(@RequestBody AuthenticationInputDto authInfo)
    {
        return userService.userSignOut(authInfo);
    }
    @GetMapping("expense/date/{date}")
    public List<Expense> getExpenseByDate(@PathVariable LocalDate date){
        return userService.getExpenseByDate(date);
    }
    @GetMapping("/monthly")
    public List<Expense> getExpensesForMonth(
            @RequestParam(name = "year") int year,
            @RequestParam(name = "month") int month) {
        List<Expense> expenses = userService.getExpensesForMonth(year, month);
        return expenses;
    }
}
