package io.zipcoder.casino.Models;

public class GuestAccount {

    private String name;
    //private String password;
    private Double accountBalance;
    private Integer id;


    public GuestAccount(String name, Integer id, Double accountBalance) {
        this.name = name;
        this.id = id;
        this.accountBalance = (double) Math.round(accountBalance * 100d)/100d;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }


    public Double getAccountBalance() {
        return this.accountBalance;
    }

    /*public void setAccountBalance(Double balance) {
        this.accountBalance = accountBalance;
    }*/


    public String toString() {

        return String.format("Name: %s, ID: %d, Balance: $%.2f\n", name, id, accountBalance);

    }

    public Double addFunds(Double funds){
        Double result = this.accountBalance + (double) Math.round(funds * 100d)/100d;
        this.accountBalance = (double) Math.round(result * 100d)/100d;
        return this.accountBalance;
    }

    public Double removeFunds(Double funds){
        Double result = this.accountBalance - (double) Math.round(funds * 100d)/100d;
        this.accountBalance = (double) Math.round(result * 100d)/100d;
        return this.accountBalance;
    }
}

