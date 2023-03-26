package com.personal.expenseapi.service;

import com.personal.expenseapi.dto.UserReg;
import com.personal.expenseapi.entity.Account;
import com.personal.expenseapi.entity.ExpenseUser;
import com.personal.expenseapi.exception.BadRequestException;
import com.personal.expenseapi.repository.AccountRepo;
import com.personal.expenseapi.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    private AccountRepo accountRepo;
    private UserRepo userRepo;

    @Autowired
    public SignUpService(AccountRepo accountRepo, UserRepo userRepo) {
        this.accountRepo = accountRepo;
        this.userRepo = userRepo;
    }

    public void signUp(UserReg userReg) throws BadRequestException{
        if(userReg.getEmail() == null){
               throw new BadRequestException("INVALID EMAIL",
                       "email not found in payload", HttpStatus.BAD_GATEWAY.toString());
        }

        if(userRepo.findByEmail(userReg.getEmail()) != null){
            throw new BadRequestException("NOT UNIQUE EMAIL",
                    "User with same email exists", HttpStatus.BAD_GATEWAY.toString());
        }

        if(userReg.getPassword() == null){
            throw new BadRequestException("PASSWORD NOT FOUND",
                    "Password missing",HttpStatus.BAD_GATEWAY.toString());
        }

        if(userReg.getHandBal() != null &&  userReg.getHandBal().signum() < 0 ){
            throw new BadRequestException("BALANCE CAN'T  BE NEGATIVE",
                    "Given hand Balance is negative",HttpStatus.BAD_GATEWAY.toString());
        }

        if(userReg.getAccBal() != null && userReg.getAccBal().signum() < 0){
            throw new BadRequestException("BALANCE CAN'T  BE NEGATIVE",
                    "Given account balance is negative",HttpStatus.BAD_GATEWAY.toString());
        }

        ExpenseUser user = new ExpenseUser();
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());

        Account userAcc = new Account();
        if(userReg.getHandBal() !=  null){
           userAcc.setHandBal(userReg.getHandBal());
        }

        if(userReg.getAccBal() != null){
            userAcc.setAccBal(userReg.getAccBal());
        }

        user.setAccount(userAcc);
        userAcc.setExpenseUser(user);

        ExpenseUser savedUser = userRepo.save(user);
        accountRepo.save(userAcc);



    }
}
