package com.victorto.telcoservice.repository;

import com.victorto.telcoservice.model.Project;
import com.victorto.telcoservice.model.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByStatus(ProjectStatus status);
    
    @Query("SELECT p FROM Project p WHERE p.createdBy.id = :userId")
    List<Project> findByCreator(@Param("userId") Long userId);
    
    boolean existsByProjectCode(String projectCode);
}
