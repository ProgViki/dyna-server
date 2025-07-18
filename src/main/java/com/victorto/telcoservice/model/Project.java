package com.victorto.telcoservice.model;

// package com.telcoservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "project_code", nullable = false, unique = true)
    private String projectCode;
    
    @Column(nullable = false)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "project_type", nullable = false)
    private ProjectType projectType;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectStatus status;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectPriority priority;
    
    @Column(name = "start_date")
    private LocalDate startDate;
    
    @Column(name = "target_completion_date")
    private LocalDate targetCompletionDate;
    
    @Column(name = "actual_completion_date")
    private LocalDate actualCompletionDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;
    
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Survey> surveys = new ArrayList<>();
    
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobOrder> jobOrders = new ArrayList<>();
    
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @Column(name = "last_modified")
    private LocalDateTime lastModified;
}

public enum ProjectType {
    SURVEY, JOB_ORDER, MAINTENANCE
}

public enum ProjectStatus {
    DRAFT, PENDING, IN_PROGRESS, COMPLETED, CANCELLED
}

public enum ProjectPriority {
    LOW, MEDIUM, HIGH, CRITICAL
}
