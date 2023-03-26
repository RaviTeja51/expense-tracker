package com.personal.expenseapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class ExpenseUser {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @OneToOne(targetEntity = Account.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "acc_id",referencedColumnName = "id")
    private Account account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ExpenseUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public ExpenseUser() {
    }
}
