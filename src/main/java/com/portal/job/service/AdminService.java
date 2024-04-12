package com.portal.job.service;

import com.portal.job.entities.*;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AdminService extends UserDetailsService {

    void createRecruiterLogin(Recruiter recruiter);

    void createTeamLeaderLogin(TeamLeader teamLeader);

    void createCompany(Company company);

    void postVacancy(Company company, Vacancy vacancy);

    void assignTeamLeaderToCompany(TeamLeader teamLeader, Company company);

    void saveRecruiter(Recruiter recruiter);

    List<Recruiter> getAllRecruiters();

    TeamLeader saveTeamLeader(TeamLeader teamLeader);

    List<TeamLeader> getAllTeamLeaders();

    public Recruiter getRecruiterById(Long id);


}
