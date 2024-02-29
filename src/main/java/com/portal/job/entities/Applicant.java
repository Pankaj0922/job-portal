package com.portal.job.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Applicant extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicant_id;
    @ManyToOne
    private Recruiter recruiter;
    private String role = "Applicant";

    public Applicant(String email, String encode, String role, String username) {
    }
}
