package com.example.springsocial.repository;

import com.example.springsocial.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie,Integer> {

    Optional<Categorie> findByParentId(Integer parentId);
    Optional<Categorie> findByName(String name);
}
