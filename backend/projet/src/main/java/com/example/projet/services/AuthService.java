package com.example.projet.services;


import com.example.projet.Entities.UserData;
import com.example.projet.Repositories.UserRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashSet;


@Component
@Path("/api/auth")
public class AuthService {

    @Autowired
    private UserRepository ur;

    private final BCryptPasswordEncoder pEncoder = new BCryptPasswordEncoder();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response login(UserData u) {
        if (u.getEmail() == null || u.getPassword() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ApiResponse("Enter you email and password !!!",null))
                    .build();
        }

        UserData user = ur.findByEmail(u.getEmail());
        if (user != null && pEncoder.matches(u.getPassword(), user.getPassword())) {
            return Response.ok(new ApiResponse("Login successful",user.getId())).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ApiResponse("Invalid email or password",null))
                    .build();
        }
    }

    public static class ApiResponse {
        private String message;
        private Long userId;

        public ApiResponse(String message, Long userId) {
            this.message = message;
            this.userId = userId;

        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/register")
    public Response register(UserData u) {
        u.setPassword(pEncoder.encode(u.getPassword()));
        UserData existingU = ur.findByEmail(u.getEmail());
        if (existingU != null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ApiResponse("You already have an account", null))
                    .build();
        }


        UserData savedU = ur.save(u);
        return Response.status(Response.Status.CREATED)
                .entity(new ApiResponse("User registered successfully", savedU.getId()))
                .build();
    }

}