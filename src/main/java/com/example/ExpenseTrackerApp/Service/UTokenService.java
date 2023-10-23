package com.example.ExpenseTrackerApp.Service;

import com.example.ExpenseTrackerApp.Model.Dto.AuthenticationInputDto;
import com.example.ExpenseTrackerApp.Model.UserAuthenticationToken;
import com.example.ExpenseTrackerApp.Repo.IUTokenRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UTokenService {


    @Autowired
    IUTokenRepo iuTokenRepo;

    public void createToken(UserAuthenticationToken token) {
        iuTokenRepo.save(token);
    }

    public void deleteToken(String tokenValue) {

        UserAuthenticationToken token =  iuTokenRepo.findFirstByTokenValue(tokenValue);
        iuTokenRepo.delete(token);

    }

    public boolean authenticate(AuthenticationInputDto authInfo) {

        String email = authInfo.getEmail();
        String tokenValue = authInfo.getTokenValue();

        //find thr actual token -> get the connected patient -> get its email-> verify with passed email

        //return ipTokenRepo.findFirstByTokenValue(tokenValue).getPatient().getPatientEmail().equals(email);

        UserAuthenticationToken token =  iuTokenRepo.findFirstByTokenValue(tokenValue);
        if(token!=null)
        {
            return token.getUser().getEmail().equals(email);
        }
        else
        {
            return false;
        }

    }
}
