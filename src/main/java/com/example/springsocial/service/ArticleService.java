package com.example.springsocial.service;

import com.example.springsocial.model.Article;
import com.example.springsocial.model.OffsetBasedPageRequest;
import com.example.springsocial.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository AR;

    public List<Article> findAll(int limit,int offset) {
        Pageable pageable = new OffsetBasedPageRequest(offset, limit);
        return AR.findAll(pageable).getContent();
    }
    public List<Article> findByBooster(int limit,int offset){
        Pageable pageable = new OffsetBasedPageRequest(offset, limit);
        return AR.findBoosterArticle(pageable).getContent();
    }
    public List<Article> findByNonBooster(int limit,int offset){
        Pageable pageable = new OffsetBasedPageRequest(offset, limit);
        return AR.findNonBoosterArticle(pageable).getContent();
    }
}
