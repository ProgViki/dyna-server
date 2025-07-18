package com.victorto.telcoservice.model;

// package com.telcoservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;

@Entity
@Table(name = "surveys")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    
    @Column(name = "site_location", nullable = false)
    private String siteLocation;
    
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "gps_coordinates")
    private GpsCoordinates gpsCoordinates;
    
    @Column(name = "survey_date", nullable = false)
    private LocalDate surveyDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conducted_by", nullable = false)
    private User conductedBy;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String findings;
    
    @Column(columnDefinition = "TEXT")
    private String recommendations;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SurveyStatus status;
}

public record GpsCoordinates(double latitude, double longitude) {}

public enum SurveyStatus {
    DRAFT, SUBMITTED, APPROVED, REJECTED
}
