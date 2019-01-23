package com.eddy.springbootrestfuljwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eddy.springbootrestfuljwt.domains.Article;
import com.eddy.springbootrestfuljwt.domains.Author;
import com.eddy.springbootrestfuljwt.domains.AuthorArticle;

@Repository
public interface AuthorArticleRepo extends JpaRepository<AuthorArticle,Long>{
	public AuthorArticle findByAuthorAndArticle(Author author, Article article); 
}
