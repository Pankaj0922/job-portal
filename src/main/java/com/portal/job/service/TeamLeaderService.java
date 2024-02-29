package com.portal.job.service;

import com.portal.job.entities.Company;
import com.portal.job.entities.Recruiter;
import com.portal.job.entities.Vacancy;

import java.util.Date;

public interface TeamLeaderService {
    void postVacancy(Company company, Vacancy vacancy);

    void assignVacancyToRecruiter(Recruiter recruiter, Vacancy vacancy, Date tentativeDate);
}
