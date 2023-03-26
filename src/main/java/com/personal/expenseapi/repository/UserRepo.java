package com.personal.expenseapi.repository;

import com.personal.expenseapi.entity.ExpenseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<ExpenseUser,Long> {

    public ExpenseUser findByEmail(String email);
}
