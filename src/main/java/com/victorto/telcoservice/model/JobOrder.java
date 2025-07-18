package com.victorto.telcoservice.model;

// package com.telcoservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "job_orders")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class JobOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    private Survey survey;
    
    @Column(name = "work_description", columnDefinition = "TEXT", nullable = false)
    private String workDescription;
    
    @Column(name = "required_materials", columnDefinition = "TEXT")
    private String requiredMaterials;
    
    @Column(name = "estimated_hours")
    private Double estimatedHours;
    
    @Column(name = "assigned_team")
    private String assignedTeam;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobOrderStatus status;
    
    @OneToMany(mappedBy = "jobOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobOrderDevice> devices = new ArrayList<>();
    
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @Column(name = "last_modified")
    private LocalDateTime lastModified;
}

public enum JobOrderStatus {
    PENDING, ASSIGNED, IN_PROGRESS, COMPLETED, CANCELLED
}
