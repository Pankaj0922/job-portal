package com.portal.job.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Admin extends Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long admin_id;

    @OneToMany(mappedBy = "admin")
    private List<TeamLeader> teamLeaders;

    @OneToMany(mappedBy = "admin")
    private List<Company> companies;
    
}
