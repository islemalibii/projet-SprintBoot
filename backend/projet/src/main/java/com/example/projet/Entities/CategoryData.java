package com.example.projet.Entities;

import jakarta.persistence.*;

@Entity
public class CategoryData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCat;
    private String name;

    public CategoryData(){}

    public Long getIdCat() {
        return idCat;
    }

    public void setIdCat(Long idcat) {
        this.idCat = idcat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
