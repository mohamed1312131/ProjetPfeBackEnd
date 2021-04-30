package com.example.springsocial.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categorie_id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name="parent_id")
    @JsonIgnore
    // @ColumnDefault("0")
    private Categorie parentId;
    @OneToMany(mappedBy="parentId")
    private List<Categorie> subCategories=new ArrayList<>();


    @OneToMany(mappedBy = "categories",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Article> articles = new ArrayList<Article>();
    public Integer getId() {
        return id;
    }
    public Categorie() {
    }

    public Categorie(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Categorie getParentId() {
        return parentId;
    }

    public void setParentId(Categorie parentId) {
        this.parentId = parentId;
    }

    public List<Categorie> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Categorie> subCategories) {
        this.subCategories = subCategories;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
