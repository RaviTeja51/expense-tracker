package com.personal.expenseapi.service;

import com.personal.expenseapi.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class ExpenseUserService {

    private UserRepo userRepo;

    public ExpenseUserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
}
