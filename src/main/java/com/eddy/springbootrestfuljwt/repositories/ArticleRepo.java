package com.eddy.springbootrestfuljwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eddy.springbootrestfuljwt.domains.Article;

@Repository
public interface ArticleRepo extends JpaRepository<Article,Long>{

}
