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
public class TeamLeader extends Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tl_id;

    @ManyToOne
    private Admin admin;

    @OneToMany(mappedBy = "teamLeader")
    private List<Recruiter> recruiters;

    @OneToMany(mappedBy = "teamLeader")
    private List<Company> companies;

    @OneToMany(mappedBy = "teamLeader")
    private List<Assignment> assignments;

    private String role = "Team Leader";

}
