package com.example.projet.Repositories;

import com.example.projet.Entities.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserData, Long> {

    //Give me a repository for the User entity, where the primary key is of type Long

    UserData findUserById(Long id);
    UserData findByEmail(String email);


    //Spring Data JPA will translate this into the following SQL query: SELECT * FROM user WHERE username = ?;


}


