package com.portal.job.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long company_id;
    private String company_name;
    private String placement_terms;
    @OneToMany(mappedBy = "company")
    private List<Vacancy> vacancies;
    @ManyToOne
    private Admin admin;
    @ManyToOne
    private TeamLeader teamLeader;

}
