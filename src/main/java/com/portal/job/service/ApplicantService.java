package com.portal.job.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.portal.job.entities.Applicant;

public interface ApplicantService extends UserDetailsService {
    Applicant saveApplicant(Applicant applicant);
}
