package com.example.abecruz.cinepolistestia.Perfil.model;

/**
 * Created by albertocruz on 28/09/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Loyalty {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("balance_list")
    @Expose
    private List<BalanceList> balanceList = null;
    @SerializedName("level")
    @Expose
    private Level level;
    @SerializedName("transactions")
    @Expose
    private List<Transaction> transactions = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<BalanceList> getBalanceList() {
        return balanceList;
    }

    public void setBalanceList(List<BalanceList> balanceList) {
        this.balanceList = balanceList;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

}