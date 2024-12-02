package com.example.projet.services;

import com.example.projet.Entities.CategoryData;
import com.example.projet.Repositories.CategoryRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@Path("/api/categories")
public class CategoryService {

    @Autowired
    private CategoryRepository cr;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addCat")
    public CategoryData addCategory(CategoryData c) {
        return cr.save(c);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listCat")
    public List<CategoryData> list() {
        return cr.findAll();
    }

}
