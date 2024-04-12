package com.portal.job.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recruiter extends Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recruiter_id;

    @ManyToOne
    private TeamLeader teamLeader;

    @OneToMany(mappedBy = "recruiter")
    private List<Applicant> applicants;

    private String role = "Recruiter";
}
