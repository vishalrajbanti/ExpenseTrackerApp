package com.example.ExpenseTrackerApp.Model.Dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationInputDto {

    @Email
    private String email;

    private String tokenValue;
}
