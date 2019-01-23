package com.eddy.springbootrestfuljwt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eddy.springbootrestfuljwt.domains.Article;
import com.eddy.springbootrestfuljwt.domains.Author;
import com.eddy.springbootrestfuljwt.domains.AuthorArticle;
import com.eddy.springbootrestfuljwt.repositories.AuthorArticleRepo;

@Service
public class AuthorArticleService {
	
	@Autowired
	AuthorArticleRepo authorArticleRepo;

	public List<AuthorArticle> getAuthorArticles(){
		return authorArticleRepo.findAll();
	}
	
	public Optional<AuthorArticle> getAuthorArticle(Long id){
		return authorArticleRepo.findById(id);
	}
	
	public AuthorArticle create(AuthorArticle authorArticle) {
		AuthorArticle aa = authorArticleRepo.save(authorArticle);
		return aa;
	}
	public AuthorArticle getAuthorArticle(Author author, Article article) {
		AuthorArticle aa = authorArticleRepo.findByAuthorAndArticle(author, article);
		return aa;
	}

	public void deleteAuthorArticleById(Long id) {
		authorArticleRepo.deleteById(id);
	}
	
	public AuthorArticle update(AuthorArticle authorArticle) {
		AuthorArticle aa = authorArticleRepo.save(authorArticle);
		return aa;
	}
}
