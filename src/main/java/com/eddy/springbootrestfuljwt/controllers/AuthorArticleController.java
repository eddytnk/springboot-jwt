package com.eddy.springbootrestfuljwt.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.eddy.springbootrestfuljwt.domains.Article;
import com.eddy.springbootrestfuljwt.domains.Author;
import com.eddy.springbootrestfuljwt.domains.AuthorArticle;
import com.eddy.springbootrestfuljwt.domains.UserInfo;
import com.eddy.springbootrestfuljwt.services.ArticleService;
import com.eddy.springbootrestfuljwt.services.AuthorArticleService;
import com.eddy.springbootrestfuljwt.services.AuthorService;
import com.eddy.springbootrestfuljwt.services.UserService;

@RestController
@RequestMapping("api/")
public class AuthorArticleController {

	@Autowired
	UserService userService;
	
	@Autowired
	AuthorService authorService;
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	AuthorArticleService authorArticleService;
	
	@GetMapping("/authors")
	public ResponseEntity<List<Author>> getAuthors(){ 
		List<Author> authors =  authorService.getAuthors();
		if(authors.isEmpty()) {
			return new ResponseEntity<List<Author>>(HttpStatus.NO_CONTENT);
		} 
		return new ResponseEntity<List<Author>>(authors,HttpStatus.OK);
	}
	
	@GetMapping("/authors/{id}")
	public ResponseEntity<Author> getAuthor(@PathVariable Long id){ 
		Optional<Author> author =  authorService.getAuthor(id);
		if(!author.isPresent()) {
			return new ResponseEntity<Author>(HttpStatus.NO_CONTENT);
		} 
		return new ResponseEntity<Author>(author.get(),HttpStatus.OK);
	}
	
	@GetMapping("/articles")
	public ResponseEntity<List<Article>> getArticles(){ 
		List<Article> articles =  articleService.getArticles();
		if(articles.isEmpty()) {
			return new ResponseEntity<List<Article>>(HttpStatus.NO_CONTENT);
		} 
		return new ResponseEntity<List<Article>>(articles,HttpStatus.OK);
	}
	
	@GetMapping("/articles/{id}")
	public ResponseEntity<Article> getArticle(@PathVariable Long id){ 
		Optional<Article> article =  articleService.getArticle(id);
		if(!article.isPresent()) {
			return new ResponseEntity<Article>(HttpStatus.NO_CONTENT);
		} 
		return new ResponseEntity<Article>(article.get(),HttpStatus.OK);
	}
	
	@GetMapping("authors/{authorId}/articles")
	public ResponseEntity<List<Article>> getAuthorArticles(@PathVariable Long authorId){ 
		Optional<Author> author =  authorService.getAuthor(authorId);
		if(!author.isPresent()) {
			return new ResponseEntity<List<Article>>(HttpStatus.NO_CONTENT);
		} 
		List<AuthorArticle> authorArticles = author.get().getAuthorArticles();
		List<Article> articles = new ArrayList<>();
		for(AuthorArticle aa: authorArticles) {
			articles.add(aa.getArticle());
		}
		return new ResponseEntity<List<Article>>(articles,HttpStatus.OK);
	}
	
	@GetMapping("authors/{authorId}/articles/{articleId}")
	public ResponseEntity<Article> getAuthorArticle(@PathVariable Long authorId,@PathVariable Long articleId){ 
		Optional<Author> author =  authorService.getAuthor(authorId);
		if(!author.isPresent()) {
			return new ResponseEntity<Article>(HttpStatus.NO_CONTENT);
		} 
		Optional<Article> article =  articleService.getArticle(articleId);
		if(!article.isPresent()) {
			return new ResponseEntity<Article>(HttpStatus.NO_CONTENT);
		} 
		AuthorArticle authorArticle = authorArticleService.getAuthorArticle(author.get(), article.get());
		if(authorArticle == null) {
			return new ResponseEntity<Article>(HttpStatus.NO_CONTENT);
		} 
		return new ResponseEntity<Article>(authorArticle.getArticle(),HttpStatus.OK);
	}
	
	@GetMapping("articles/{articleId}/authors")
	public ResponseEntity<List<Author>> getrArticleAuthors(@PathVariable Long articleId){ 
		Optional<Article> article =  articleService.getArticle(articleId);
		if(!article.isPresent()) {
			return new ResponseEntity<List<Author>>(HttpStatus.NO_CONTENT);
		} 
		List<AuthorArticle> articleAuthors = article.get().getAuthorArticles();
		List<Author> authors = new ArrayList<>();
		for(AuthorArticle aa: articleAuthors) {
			authors.add(aa.getAuthor());
		}
		return new ResponseEntity<List<Author>>(authors,HttpStatus.OK);
	}
	
	@PostMapping("/authors")
	public ResponseEntity<Void> createAuthors(@RequestBody Author author, UriComponentsBuilder ucBuilder){ 
		Long userId = 1l;//from token
		Optional<UserInfo> user = userService.getUser(userId);
		author.setUser(user.get());
		Author a = authorService.create(author);
		
		HttpHeaders header = new HttpHeaders();
		header.setLocation(ucBuilder.path("authors/{id}").buildAndExpand(a.getId()).toUri());
		return new ResponseEntity<Void>(header,HttpStatus.CREATED);
		
	}
	@PostMapping("/articles")
	public ResponseEntity<Void> createArticle(@RequestBody Article article, UriComponentsBuilder ucBuilder){ 
		Long userId = 1l;//from token
		Optional<UserInfo> user = userService.getUser(userId);
		Author author = user.get().getAuthor();
		
		AuthorArticle authorArticle = new AuthorArticle();
		authorArticle.setArticle(article);
		authorArticle.setAuthor(author);
		
		article.addAuthorArticle(authorArticle);
		Article a = articleService.create(article);
		
		HttpHeaders header = new HttpHeaders();
		header.setLocation(ucBuilder.path("articles/{id}").buildAndExpand(a.getId()).toUri());
		return new ResponseEntity<Void>(header,HttpStatus.CREATED);	
	}
	
	
}
