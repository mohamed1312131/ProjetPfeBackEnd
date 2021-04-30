package com.example.springsocial.controller;


import com.example.springsocial.model.Article;

import com.example.springsocial.model.OffsetBasedPageRequest;
import com.example.springsocial.model.Order;
import com.example.springsocial.model.Poster;
import com.example.springsocial.payload.ArticleRequest;
import com.example.springsocial.repository.ArticleRepository;
import com.example.springsocial.repository.PosterRepository;
import com.example.springsocial.repository.UserRepository;
import com.example.springsocial.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleRepository AR;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserRepository UR;
    @Autowired
    private PosterRepository PR;

    @GetMapping("/all")
    public List<Article> getAll()
    {
        return this.AR.findAll();
    }
    @PostMapping("/add")
    public void save(@RequestBody Article article) {
        AR.save(article);
    }

    @PostMapping("/poster/{id}")
    public  void poster(@RequestBody Article article,@PathVariable(name = "id") long id){
        Poster poster = new Poster();
        this.AR.save(article);
        poster.setArticle(article);
        Date date = new Date();
        poster.setPublishedDate(date);
        poster.setUsers(this.UR.findById(id).get());
        this.PR.save(poster);
    }
    @GetMapping("/getBy/{id}")
    public List<Article> getArticleByUserId(@PathVariable(name = "id") long id){
        List<Poster> poster = this.UR.findById(id).get().getPoster();
        List<Article> articles = new ArrayList<>();
        for (Poster poster1 : poster)
            {
                articles.add(poster1.getArticle());
            }
        return articles;
    }
    @GetMapping("count-article/{id}")
    public long getNumberOfArticleById(@PathVariable(name = "id") long id){
        List<Poster> poster = this.UR.findById(id).get().getPoster();
        int i=0;
        for (Poster poster1 : poster)
        {
            i++;
        }
        return i;
    }
    @GetMapping("/vendu/{id}")
    public List<Article> getVenduByUserId(@PathVariable(name = "id") long id){
        List<Poster> poster = this.UR.findById(id).get().getPoster();
        List<Article> articles = new ArrayList<>();
        for (Poster poster1 : poster)
        {
            if (poster1.getArticle().isStatue()) {
                articles.add(poster1.getArticle());
            }

        }
        return articles;
    }
    @GetMapping("/getByOrder/{id}")
    public List<Article> getOrderArticleByUserId(@PathVariable(name = "id") long id){
        List<Order> orders = this.UR.findById(id).get().getOrders();
        List<Article> articles = new ArrayList<>();
        for (Order order : orders)
        {
            articles.add(order.getArticle());
        }
        return articles;
    }
}
