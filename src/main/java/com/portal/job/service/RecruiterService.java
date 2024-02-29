package com.portal.job.service;

import com.portal.job.entities.Applicant;
import com.portal.job.entities.Recruiter;
import com.portal.job.entities.Vacancy;

import java.util.List;

public interface RecruiterService {
    void uploadApplicantData(Vacancy vacancy, Applicant applicant);

    void updateApplicantStatus(Applicant applicant, String status, String remarks);

    void moveApplicantToAnotherVacancy(Applicant applicant, Vacancy newVacancy);


    List<Recruiter> getAllRecruiters();
}
