package com.portal.job.entities;

import groovy.transform.builder.Builder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Applicant extends Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicant_id;
    @ManyToOne
    private Recruiter recruiter;
    

    public Applicant(String email, String encode, String role, String username) {
    }
}
