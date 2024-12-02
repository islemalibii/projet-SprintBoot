package com.example.projet.services;

import com.example.projet.Entities.CategoryData;
import com.example.projet.Entities.ExpenseData;
import com.example.projet.Entities.UserData;
import com.example.projet.Repositories.CategoryRepository;
import com.example.projet.Repositories.ExpenseRepository;
import com.example.projet.Repositories.UserRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.lang.model.util.Elements;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@Path("/api/expenses")
public class ExpenseService {
    @Autowired
    private ExpenseRepository er;
    @Autowired
    private CategoryRepository cr;
    @Autowired
    private UserRepository ur;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    public Response add(ExpenseData e) {
        Long userId = e.getUser().getId();
        UserData user = ur.findUserById(userId);
        if (user == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("User not found for ID: " + userId)
                    .build();
        }
        Long categoryId = e.getCategory().getIdCat();
        CategoryData category = cr.findByIdCat(categoryId);
        if (category == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Category not found for ID: " + categoryId)
                    .build();
        }

        e.setUser(user);
        e.setCategory(category);

        ExpenseData savedExpense = er.save(e);


        return Response.status(Response.Status.CREATED)
                .entity(savedExpense)
                .build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public List<ExpenseData> listUserExpenses(@QueryParam("userId") Long userId) {
        UserData user = ur.findUserById(userId);
        List<ExpenseData> expenses = er.findByUser(user);
        return expenses;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listbyCat")
    public List<ExpenseData> byCategory(Long catId) {
        List<ExpenseData> expensesByCat = er.findByCategory_IdCat(catId);
        return expensesByCat;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/delete/{id}")
    public void delete(@PathParam("id")Long id) {
        er.deleteById(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update")
    public ExpenseData update(ExpenseData e) {
        ExpenseData expense = er.findByIdExpen(e.getId_expen());
        CategoryData category = cr.findByIdCat(e.getCategory().getIdCat());
        expense.setNameExp(e.getNameExp());
        expense.setAmount(e.getAmount());
        expense.setDate(e.getDate());
        expense.setDescription(e.getDescription());
        expense.setCategory(category);
        expense.setUser(e.getUser());
        return er.save(expense);
    }


}
