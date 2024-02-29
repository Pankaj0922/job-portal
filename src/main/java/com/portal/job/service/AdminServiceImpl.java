package com.portal.job.service;

import com.portal.job.entities.*;
import com.portal.job.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RecruiterRepository recruiterRepository;

    @Autowired
    private TeamLeaderRepository teamLeaderRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private VacancyRepository vacancyRepository;

    @Override
    public void createRecruiterLogin(Recruiter recruiter) {
        recruiterRepository.save(recruiter);
    }

    @Override
    public void createTeamLeaderLogin(TeamLeader teamLeader) {

        teamLeaderRepository.save(teamLeader);
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public void postVacancy(Company company, Vacancy vacancy) {
        vacancy.setCompany(company);
        vacancyRepository.save(vacancy);
    }

    @Override
    public void assignTeamLeaderToCompany(TeamLeader teamLeader, Company company) {
        List<Company> companies = teamLeader.getCompanies();
        if (companies == null) {
            companies = new ArrayList<>();  // Initialize the list if null
        }
        companies.add(company);
        teamLeader.setCompanies(companies);
        teamLeaderRepository.save(teamLeader);
    }

    @Override
    public void saveRecruiter(Recruiter recruiter) {
        recruiter.getEmail();
        recruiter.getPassword();
        recruiterRepository.save(recruiter);
    }


    @Override
    public List<Recruiter> getAllRecruiters() {
        return recruiterRepository.findAll();
    }

    @Override
    public TeamLeader saveTeamLeader(TeamLeader teamLeader) {
        teamLeader.getEmail();
        teamLeader.getPassword();
        teamLeaderRepository.save(teamLeader);
        return teamLeader;
    }

    @Override
    public List<TeamLeader> getAllTeamLeaders() {
        return teamLeaderRepository.findAll();
    }

    public void removeSessionMessage() {
        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest().getSession();
        session.removeAttribute("msg");
    }

}
