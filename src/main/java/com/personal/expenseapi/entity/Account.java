package com.personal.expenseapi.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="account")
public class Account {

    @Id
    @Column(name = "acc_id")
    private Long id;

    @Column(name="hand_bal")
    private BigDecimal handBal;
    @Column(name="acc_bal")
    private BigDecimal accBal;

    @Column(name="total_bal")
    private BigDecimal totalBal;

    @OneToOne(mappedBy = "account")
    private ExpenseUser expenseUser;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getTotalBal() {
        return totalBal;
    }

    public void setTotalBal(BigDecimal totalBal) {
        this.totalBal = totalBal;
    }

    public ExpenseUser getExpenseUser() {
        return expenseUser;
    }

    public void setExpenseUser(ExpenseUser expenseUser) {
        this.expenseUser = expenseUser;
    }

    public Account(BigDecimal handBal, BigDecimal accBal, BigDecimal totalBal, ExpenseUser expenseUser) {
        this.handBal = handBal;
        this.accBal = accBal;
        this.totalBal = totalBal;
        this.expenseUser = expenseUser;
    }

    public Account(BigDecimal handBal, BigDecimal accBal, BigDecimal totalBal) {
        this.handBal = handBal;
        this.accBal = accBal;
        this.totalBal = totalBal;
    }

    public Account(BigDecimal handBal) {
        this.handBal = handBal;
        this.accBal  = BigDecimal.ZERO;
        this.totalBal = this.handBal.add(accBal);
    }

    public Account(BigDecimal  handBal,BigDecimal accBal){
        this.handBal=handBal;
        this.accBal=accBal;
        this.totalBal = this.handBal.add(accBal);
    }


    public Account() {
        this.handBal = BigDecimal.ZERO;
        this.accBal = BigDecimal.ZERO;
        this.totalBal = handBal.add(accBal);
    }
}
