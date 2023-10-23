package com.example.ExpenseTrackerApp.Repo;

import com.example.ExpenseTrackerApp.Model.UserAuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUTokenRepo extends JpaRepository<UserAuthenticationToken,Integer> {
    UserAuthenticationToken findFirstByTokenValue(String tokenValue);
}
