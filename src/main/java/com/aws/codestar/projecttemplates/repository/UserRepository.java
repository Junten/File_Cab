package com.aws.codestar.projecttemplates.repository;

import com.aws.codestar.projecttemplates.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}