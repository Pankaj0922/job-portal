package com.portal.job.entities;

import groovy.transform.builder.Builder;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@Builder
public class Users {

    @Email(message = "Please provide a valid email address")
    private String email;

    private String password;

    private String username;

    private String role;

}
