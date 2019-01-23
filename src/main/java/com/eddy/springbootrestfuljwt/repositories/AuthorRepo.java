package com.eddy.springbootrestfuljwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eddy.springbootrestfuljwt.domains.Author;

@Repository
public interface AuthorRepo extends JpaRepository<Author,Long>{

}
