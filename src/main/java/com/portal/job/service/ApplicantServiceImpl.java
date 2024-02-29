package com.portal.job.service;

import com.portal.job.entities.Applicant;
import com.portal.job.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ApplicantServiceImpl implements ApplicantService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicantRepository applicantRepository;

    @Override
    public Applicant save(Applicant applicant) {
        Applicant app = new Applicant(applicant.getEmail(), passwordEncoder.encode(applicant.getPassword()), applicant.getRole(), applicant.getUsername());
        return applicantRepository.save(app);
    }

}
