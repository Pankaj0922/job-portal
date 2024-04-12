package com.portal.job.controller;

import com.portal.job.entities.Admin;
import com.portal.job.entities.Applicant;
import com.portal.job.repository.AdminRepository;
import com.portal.job.repository.ApplicantRepository;
import com.portal.job.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {
    @Autowired
    private ApplicantService applicantService;
    @Autowired
    private AdminRepository repository;

    @Autowired
    private ApplicantRepository applicantRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register/user")
    public Admin createAdmin(@RequestBody Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return repository.save(admin);
    }


}
