package com.example.springsocial.controller;

import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.*;
import com.example.springsocial.payload.ArticleRequest;
import com.example.springsocial.repository.UserRepository;
import com.example.springsocial.service.ArticleService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/data")
public class loginController {
    @Autowired
    private UserRepository UR;
    @Autowired
    private ArticleService articleService;
    @GetMapping("/user/{email}")
    public User getUserById(@PathVariable(name = "email") String email) {
        Optional<User> user = UR.findByEmail(email);

       return user.get();
    }
    @GetMapping("/article-order/{id}")
    public List<Article> getOrdersByUserId(@PathVariable(name = "id") long id){
        List<Order> order = this.UR.findById(id).get().getOrders();
        List<Article> articles = new ArrayList<>();
        for (Order order1 : order)
        {
            articles.add(order1.getArticle());
        }
        return articles;
    }
    @GetMapping("/order/{id}")
    public List<Order> getInOrdersByUserId(@PathVariable(name = "id") long id){
        return this.UR.findById(id).get().getOrders();

    }
    @GetMapping("/non-order/{id}")
    public List<Article> getNonOrdersByUserId(@PathVariable(name = "id") long id){
        List<Order> order = this.UR.findById(id).get().getOrders();
        List<Article> articles = new ArrayList<>();
        for (Order order1 : order)
        {
            articles.add(order1.getArticle());
        }
        return articles;
    }
    @PutMapping("/update1/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        User user1 = UR.findById(id).get();

        if (user.getEmail()!=null){
            user1.setEmail(user.getEmail());
        }
        if (user.getFacebook()!=null){
            user1.setFacebook(user.getFacebook());
        }
        if(user.getImageUrl()!=null){
            user1.setImageUrl(user.getImageUrl());
        }
        if(user.getInstagram()!=null){
            user1.setInstagram(user.getInstagram());
        }
        if(user.getTwitter()!=null){
            user1.setTwitter(user.getTwitter());
        }

        User updatedUser = UR.save(user1);
        return ResponseEntity.ok(updatedUser);
    }
    @PutMapping("/update-boutique/{id}")
    public ResponseEntity<User> updateBoutique(@PathVariable Long id, @RequestBody User user){
        User user1 = UR.findById(id).get();

        if (user.getNomBoutique()!=null){
            user1.setNomBoutique(user.getNomBoutique());
        }
        if (user.getDescBoutique()!=null){
            user1.setDescBoutique(user.getDescBoutique());
        }
        if (user.getAcceptMail()!=null){
            user1.setAcceptMail(user.getAcceptMail());
        }

        User updatedUser = UR.save(user1);
        return ResponseEntity.ok(updatedUser);
    }
    @PutMapping("/update2/{id}")
    public ResponseEntity<User> updateUser2(@PathVariable Long id, @RequestBody User user){
        User user1 = UR.findById(id).get();
        if (user.getAdress1()!=null){
            user1.setAdress1(user.getAdress1());
        }
        if (user.getAdress2()!=null){
            user1.setAdress2(user.getAdress2());
        }
        if (user.getCode_postal()>0){
            user1.setCode_postal(user.getCode_postal());
        }
        if (user.getName()!=null){
            user1.setName(user.getName());
        }
        if (user.getRegion()!=null){
            user1.setRegion(user.getRegion());
        }
        if (user.getTel()>0){
            user1.setTel(user.getTel());
        }
        if (user.getVille()!=null){
            user1.setVille(user.getVille());
        }
        User updatedUser = UR.save(user1);
        return ResponseEntity.ok(updatedUser);
    }
    @GetMapping("/accueil/{id}")
    public List<Article> getArticleByFriends(@PathVariable(name = "id") long id) {
        List<User> users = this.UR.findById(id).get().getFriends();
        List<Article> articles = new ArrayList<>();
        List<List<Poster>> posters = new ArrayList<>();
        int k =0;
        for (User user : users) {
            posters.add(user.getPoster());
            for (int i=k; i < posters.size(); i++) {
                for (int j = 0; j < posters.get(i).size(); j++) {
                    if(posters.get(i).get(j).getArticle().isStatue()==false){
                        articles.add(posters.get(i).get(j).getArticle());
                    }
                }
                k=i+1;

            }


        }
        return articles;

    }
    @GetMapping("/article-booster/{l}/{o}")
    public List<Article> getArticleBooster(@PathVariable(name = "l") int limit,@PathVariable(name = "o") int offsets){
         return articleService.findByBooster(limit,offsets);

    }
    @GetMapping("non-booster-article/{l}/{o}")
    public List<Article> getArticle(@PathVariable(name = "l") int limit,@PathVariable(name = "o") int offsets) {
        return articleService.findByNonBooster(limit,offsets);

    }
}
