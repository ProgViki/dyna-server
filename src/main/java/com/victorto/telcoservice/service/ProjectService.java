package com.victorto.telcoservice.service;

import com.victorto.telcoservice.dto.request.ProjectRequest;
import com.victorto.telcoservice.dto.response.ProjectResponse;
import com.victorto.telcoservice.exception.ResourceNotFoundException;
import com.victorto.telcoservice.model.Project;
import com.victorto.telcoservice.model.ProjectStatus;
import com.victorto.telcoservice.model.User;
import com.victorto.telcoservice.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final WorkflowService workflowService;
    private final AuditLogService auditLogService;

    @Transactional
    public ProjectResponse createProject(ProjectRequest request, User creator) {
        Project project = new Project();
        project.setProjectCode(generateProjectCode());
        project.setName(request.name());
        project.setDescription(request.description());
        project.setProjectType(request.projectType());
        project.setStatus(ProjectStatus.DRAFT);
        project.setPriority(request.priority());
        project.setStartDate(request.startDate());
        project.setTargetCompletionDate(request.targetCompletionDate());
        project.setCreatedBy(creator);
        
        Project savedProject = projectRepository.save(project);
        workflowService.initiateWorkflow(savedProject, creator);
        auditLogService.logCreation(creator, "PROJECT", savedProject.getId());
        
        return mapToResponse(savedProject);
    }

    public ProjectResponse getProjectById(Long id) {
        Project project = projectRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        return mapToResponse(project);
    }

    public List<ProjectResponse> getAllProjects() {
        return projectRepository.findAll().stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Transactional
    public ProjectResponse updateProjectStatus(Long id, ProjectStatus status, User updater) {
        Project project = projectRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        
        ProjectStatus oldStatus = project.getStatus();
        project.setStatus(status);
        
        Project updatedProject = projectRepository.save(project);
        auditLogService.logStatusChange(updater, "PROJECT", id, oldStatus, status);
        
        return mapToResponse(updatedProject);
    }

    private String generateProjectCode() {
        return "PRJ-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private ProjectResponse mapToResponse(Project project) {
        return ProjectResponse.builder()
            .id(project.getId())
            .projectCode(project.getProjectCode())
            .name(project.getName())
            .description(project.getDescription())
            .projectType(project.getProjectType())
            .status(project.getStatus())
            .priority(project.getPriority())
            .startDate(project.getStartDate())
            .targetCompletionDate(project.getTargetCompletionDate())
            .createdBy(project.getCreatedBy().getUsername())
            .createdAt(project.getCreatedAt())
            .build();
    }
}