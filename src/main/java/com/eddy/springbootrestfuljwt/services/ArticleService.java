package com.eddy.springbootrestfuljwt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eddy.springbootrestfuljwt.domains.Article;
import com.eddy.springbootrestfuljwt.repositories.ArticleRepo;


@Service
public class ArticleService {
	
	@Autowired
	ArticleRepo articleRepo;

	public List<Article> getArticles(){
		return articleRepo.findAll();
	}
	
	public Optional<Article> getArticle(Long id){
		return articleRepo.findById(id);
	}
	
	public Article create(Article article) {
		Article a = articleRepo.save(article);
		return a;
	}

	public void deleteArticleById(Long id) {
		articleRepo.deleteById(id);
	}
	
	public Article update(Article article) {
		Article a = articleRepo.save(article);
		return a;
	}
}
