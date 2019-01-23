package com.eddy.springbootrestfuljwt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eddy.springbootrestfuljwt.domains.Author;
import com.eddy.springbootrestfuljwt.repositories.AuthorRepo;

@Service
public class AuthorService {
	
	@Autowired
	AuthorRepo authorRepo;

	public List<Author> getAuthors(){
		return authorRepo.findAll();
	}
	
	public Optional<Author> getAuthor(Long id){
		return authorRepo.findById(id);
	}
	
	public Author create(Author author) {
		Author a = authorRepo.save(author);
		return a;
	}

	public void deleteAuthorById(Long id) {
		authorRepo.deleteById(id);
	}
	
	public Author update(Author author) {
		Author a = authorRepo.save(author);
		return a;
	}
}
