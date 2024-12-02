package com.example.projet.Entities;

import com.example.projet.Entities.CategoryData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
public class ExpenseData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExpen;


    private String nameExp;
    private Double amount;
    private String description;
    @ManyToOne
    private CategoryData category;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserData user;


    public ExpenseData(){}
    public Long getId_expen() {
        return idExpen;
    }

    public void setId_expen(Long idexpen) {
        this.idExpen = idexpen;
    }


    public String getNameExp() {
        return nameExp;
    }

    public void setNameExp(String nameExp) {
        this.nameExp = nameExp;
    }


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public CategoryData getCategory() {
        return category;
    }

    public void setCategory(CategoryData category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }


}
