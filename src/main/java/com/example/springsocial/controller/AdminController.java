package com.example.springsocial.controller;;


import com.example.springsocial.model.Categorie;
import com.example.springsocial.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CategorieRepository CR;


    @GetMapping("/all")
    public List<Categorie> getAll()
    {

        return this.CR.findAll();
    }

    @GetMapping("/cat/{name}")
    public List<Categorie> getCat(@PathVariable(name = "name") String name)
    {
        return this.CR.findByName(name).get().getSubCategories();
    }
    @PostMapping("/add-categorie")
    public ResponseEntity<Categorie> createCategories(@RequestBody Categorie categorie) {
        try {
            Categorie categorie1 = CR.save(new Categorie(categorie.getName()));


            return new ResponseEntity<>(categorie1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/categorie/{id}/{id2}")
    public ResponseEntity<Categorie> addParentCategorie(@PathVariable("id") int id,@PathVariable("id2") int id2){
        Optional<Categorie> categorie = CR.findById(id);
        if (categorie.isPresent()){
            Categorie categorie1 = categorie.get();
            categorie1.setParentId(CR.findById(id2).get());
            return new ResponseEntity<>(CR.save(categorie1), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/parent")
    public List<Categorie> getAllParent()
    {
        List<Categorie> categories1 = new ArrayList<>();
        List<Categorie> categories = this.CR.findAll();
        for (Categorie categorie : categories) {
            if (categorie.getParentId() == null){
                categories1.add(categorie);
            }
        }
        return categories1;
    }



}
