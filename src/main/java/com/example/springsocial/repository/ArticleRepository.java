package com.example.springsocial.repository;

import com.example.springsocial.model.Article;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.net.ContentHandler;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {


//
//    @Query("SELECT u FROM User u WHERE u.booster = 1")
//    <S extends Article> Page<S> findByBooster(Pageable pageable);

    @Query(value="select a from Article a WHERE a.booster = 1 and a.statue=0", countQuery="select count(a) from Article a")
    Page<Article> findBoosterArticle(Pageable pageable);

    @Query(value="select a from Article a WHERE a.booster = 0 and a.statue=0", countQuery="select count(a) from Article a")
    Page<Article> findNonBoosterArticle(Pageable pageable);

}
