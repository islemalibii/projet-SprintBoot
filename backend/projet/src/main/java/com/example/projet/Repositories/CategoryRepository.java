package com.example.projet.Repositories;

import com.example.projet.Entities.CategoryData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryData, Long> {
    CategoryData findByIdCat(Long idCat);
}
