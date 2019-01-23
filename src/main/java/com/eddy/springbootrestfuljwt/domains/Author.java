package com.eddy.springbootrestfuljwt.domains;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Author {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String fullName;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private UserInfo user;
	
	@JsonIgnore
	@OneToMany(mappedBy="author")
	private List<AuthorArticle> authorArticles;

	public Author() {
		this.authorArticles = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public List<AuthorArticle> getAuthorArticles() {
		return authorArticles;
	}

	public void setAuthorArticles(List<AuthorArticle> authorArticles) {
		this.authorArticles = authorArticles;
	}
	
	public void addAuthorArticle(AuthorArticle authorArticle) {
		this.authorArticles.add(authorArticle);
		authorArticle.setAuthor(this);
	}
	
	
}
