package com.eddy.springbootrestfuljwt.domains;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Article {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String title;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date publishDate;
	
	@JsonIgnore
	@OneToMany(mappedBy="article")
	private List<AuthorArticle> authorArticles;

	public Article() {
		this.authorArticles = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public List<AuthorArticle> getAuthorArticles() {
		return authorArticles;
	}

	public void setAuthorArticles(List<AuthorArticle> authorArticles) {
		this.authorArticles = authorArticles;
	}
	public void addAuthorArticle(AuthorArticle authorArticle) {
		this.authorArticles.add(authorArticle);
		authorArticle.setArticle(this);
	}
	
	
	
}
