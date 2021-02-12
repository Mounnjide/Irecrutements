package com.irecrutements.spring.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irecrutements.spring.models.Role;

public interface RoleRepository extends JpaRepository<Role, String>{

}
