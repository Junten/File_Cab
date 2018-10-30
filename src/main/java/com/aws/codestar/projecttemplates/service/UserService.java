package com.aws.codestar.projecttemplates.service;

import com.aws.codestar.projecttemplates.model.User;

public interface UserService {
    void save(User user);   
    User findByUsername(String username);
}