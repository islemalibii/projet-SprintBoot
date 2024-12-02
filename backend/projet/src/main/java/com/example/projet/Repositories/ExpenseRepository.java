package com.example.projet.Repositories;

import com.example.projet.Entities.ExpenseData;
import com.example.projet.Entities.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<ExpenseData, Long> {
    ExpenseData findByIdExpen(Long idExpen);
    List<ExpenseData> findByUser(UserData user);

    List<ExpenseData> findByCategory_IdCat(Long categoryId);
}

