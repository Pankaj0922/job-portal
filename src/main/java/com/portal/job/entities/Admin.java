package com.portal.job.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
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
public class Admin extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long admin_id;

    @OneToMany(mappedBy = "admin")
    private List<TeamLeader> teamLeaders;

    @OneToMany(mappedBy = "admin")
    private List<Company> companies;
}
