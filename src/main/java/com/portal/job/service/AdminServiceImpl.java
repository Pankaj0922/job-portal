package com.portal.job.service;

import com.portal.job.entities.*;
import com.portal.job.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Autowired
    private ApplicantRepository applicantRepository;

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

    @Override
    public Recruiter getRecruiterById(Long id) {
        Optional<Recruiter> recruiter = recruiterRepository.findById(id);
        return recruiter.orElse(null);
    }

    public void removeSessionMessage() {
        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest().getSession();
        session.removeAttribute("msg");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> admin = adminRepository.findByUsername(username);
        Optional<Applicant> applicant = applicantRepository.findByUsername(username);
        if (admin.isPresent()) {
            var adminObj = admin.get();
            return User.builder()
                    .username(adminObj.getUsername())
                    .password(adminObj.getPassword())
                    .roles(getRoles(adminObj))
                    .build();

        } else if (applicant.isPresent()) {
            var applicantObj = applicant.get();
            return User.builder()
                    .username(applicantObj.getUsername())
                    .password(applicantObj.getPassword())
                    .roles(getRole(applicantObj))
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    private String[] getRoles(Admin adminObj) {
        if (adminObj.getRole() == null) {
            return new String[]{"ADMIN"};
        }
        return adminObj.getRole().split(",");
    }

    private String getRole(Applicant applicantObj) {
        if (applicantObj.getRole() == null) {
            return new String("USER");
        }
        return applicantObj.getRole();
    }
}
