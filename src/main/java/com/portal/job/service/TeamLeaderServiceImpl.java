package com.portal.job.service;

import com.portal.job.entities.Assignment;
import com.portal.job.entities.Company;
import com.portal.job.entities.Recruiter;
import com.portal.job.entities.Vacancy;
import com.portal.job.repository.AssignmentRepository;
import com.portal.job.repository.CompanyRepository;
import com.portal.job.repository.RecruiterRepository;
import com.portal.job.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TeamLeaderServiceImpl implements TeamLeaderService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private RecruiterRepository recruiterRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Override
    public void postVacancy(Company company, Vacancy vacancy) {
        vacancy.setCompany(company);
        vacancyRepository.save(vacancy);
    }

    @Override
    public void assignVacancyToRecruiter(Recruiter recruiter, Vacancy vacancy, Date tentativeDate) {
        Assignment assignment = new Assignment();
        assignment.setTeamLeader(recruiter.getTeamLeader());
        assignment.setVacancy(vacancy);
        assignment.setAssignmentDate(new Date());
        assignmentRepository.save(assignment);
    }

    // Other methods for viewing reports
}

