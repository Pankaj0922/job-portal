package com.portal.job.service;

import com.portal.job.entities.Applicant;
import com.portal.job.repository.ApplicantRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class ApplicantServiceImpl implements ApplicantService {


    @Autowired
    private ApplicantRepository applicantRepository;

    @Override
    public Applicant saveApplicant(Applicant applicant) {

        return applicantRepository.save(applicant);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Applicant> applicant = applicantRepository.findByUsername(username);
        if (applicant.isPresent()) {
            var applicantObj = applicant.get();
            return User.builder()
                    .username(applicantObj.getUsername())
                    .password(applicantObj.getPassword())
                    .roles(getRoles(applicantObj))
                    .build();

        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    private String[] getRoles(Applicant applicantObj) {
        if (applicantObj.getRole() == null) {
            return new String[]{"USER"};
        }
        return applicantObj.getRole().split(",");
    }

}
