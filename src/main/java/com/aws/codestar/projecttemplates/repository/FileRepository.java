package com.aws.codestar.projecttemplates.repository;

import com.aws.codestar.projecttemplates.model.File;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findByUserId(long id);
    
}
