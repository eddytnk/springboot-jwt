package com.eddy.springbootrestfuljwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eddy.springbootrestfuljwt.domains.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long>{

}
