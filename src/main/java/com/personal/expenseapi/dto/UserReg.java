package com.personal.expenseapi.dto;

import java.math.BigDecimal;

public class UserReg {
    private String email;
    private String password;
    private BigDecimal handBal;
    private BigDecimal accBal;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getHandBal() {
        return handBal;
    }

    public void setHandBal(BigDecimal handBal) {
        this.handBal = handBal;
    }

    public BigDecimal getAccBal() {
        return accBal;
    }

    public void setAccBal(BigDecimal accBal) {
        this.accBal = accBal;
    }
}
