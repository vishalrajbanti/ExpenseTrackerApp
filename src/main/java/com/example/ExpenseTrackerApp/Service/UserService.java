package com.example.ExpenseTrackerApp.Service;

import com.example.ExpenseTrackerApp.Model.Dto.AuthenticationInputDto;
import com.example.ExpenseTrackerApp.Model.Dto.SignInInputDto;
import com.example.ExpenseTrackerApp.Model.Expense;
import com.example.ExpenseTrackerApp.Model.User;
import com.example.ExpenseTrackerApp.Model.UserAuthenticationToken;
import com.example.ExpenseTrackerApp.Repo.IExpenseRepo;
import com.example.ExpenseTrackerApp.Repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {
    @Autowired
    IExpenseRepo iExpenseRepo;
    @Autowired
    IUserRepo iUserRepo;
    @Autowired
    ExpenseService expenseService;


    @Autowired
    UTokenService uTokenService;
    public String userSignUp(User user) {
        //check if already exist -> Not allowed : try logging in

        String newEmail = user.getEmail();

       User existingUser = iUserRepo.findFirstByEmail(newEmail);

        if(existingUser != null)
        {
            return "email already in use";
        }

        // passwords are encrypted before we store it in the table

        String signUpPassword = user.getPassword();

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(signUpPassword);

            user.setPassword(encryptedPassword);


            // patient table - save patient
            iUserRepo.save(user);
            return "user registered";

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }

    }

    public String userSignIn(SignInInputDto signInInput) {
        String email = signInInput.getEmail();

        User existingUser = iUserRepo.findFirstByEmail(email);

        if(existingUser == null)
        {
            return "Not a valid email, Please sign up first !!!";
        }

        //password should be matched

        String password = signInInput.getPassword();

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);

            if(existingUser.getPassword().equals(encryptedPassword))
            {
                // return a token for this sign in
                UserAuthenticationToken token  = new UserAuthenticationToken(existingUser);

                if(EmailService.sendEmail(email,"otp after login", token.getTokenValue())) {
                    uTokenService.createToken(token);
                    return "check email for otp/token!!!";
                }
                else {
                    return "error while generating token!!!";
                }

            }
            else {
                //password was wrong!!!
                return "Invalid Credentials!!!";
            }

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }


    }

    public String userSignOut(AuthenticationInputDto authInfo) {
        if(uTokenService.authenticate(authInfo)) {
            String tokenValue = authInfo.getTokenValue();
            uTokenService.deleteToken(tokenValue);
            return "Sign Out successful!!";
        }
        else {
            return "Un Authenticated access!!!";
        }

    }

    public List<Expense> getExpenseByDate(LocalDate date) {
        return expenseService.getExpense(date);
    }

    public List<Expense> getExpensesForMonth(int year, int month) {
        return expenseService.getExpenseForMonth(year,month);
    }
}

