package com.aws.codestar.projecttemplates.repository;

import com.aws.codestar.projecttemplates.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
	 Role findByRole(String role);
}