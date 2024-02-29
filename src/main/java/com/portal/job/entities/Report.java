package com.portal.job.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private YearMonth reportMonth;

    private String reportData;  // Example field, replace with actual report data structure

    // Add other fields as needed

    // Constructors, getters, and setters
}
