package com.portal.job.controller;

import com.portal.job.entities.*;
import com.portal.job.service.ApplicantService;
import com.portal.job.service.EmailSenderService;
import com.portal.job.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private ApplicantService applicantService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/createRecruiter")
    public String showCreateRecruiterForm(Model model) {
        model.addAttribute("recruiter", new Recruiter());
        return "admin/createRecruiter"; // Assuming "createRecruiter" is the name of your Thymeleaf template
    }

    @PostMapping("/createRecruiter") // Update the mapping to match your form action
    public String createRecruiter(@ModelAttribute Recruiter recruiter, BindingResult bindingResult, Model model) {
        if (!isValidPassword(recruiter.getPassword())) {
            bindingResult.rejectValue("password", "error.teamLeader",
                    "Password must be at least 8 characters long and contain at least one letter and one digit.");
        }

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            model.addAttribute("recruiter", recruiter);
            // If there are errors, return to the form with the validation messages
            return "admin/createRecruiter";
        }
        // Process the form submission and save the recruiter
        adminService.saveRecruiter(recruiter);
        sendRecruiterCredentialsByEmail(recruiter.getEmail(), recruiter.getUsername(), recruiter.getPassword());
        // Redirect to a success page or another appropriate endpoint
        return "redirect:/dashboard";
    }

    private void sendRecruiterCredentialsByEmail(String toEmail, String username, String password) {
        String subject = "Credentials for Recruiter Account";
        String body = "Dear Recruiter,\n\nYour account has been created with the following credentials:\n\n"
                + "Username: " + username + "\nPassword: " + password + "\n\nPlease keep your credentials secure.";

        emailSenderService.sendSimpleEmail(toEmail, body, subject);
    }

    @GetMapping("/viewRecruiters")
    public String showAllRecruiters(Model model) {
        List<Recruiter> recruiters = adminService.getAllRecruiters();
        model.addAttribute("recruiters", recruiters);
        return "admin/viewRecruiters"; // Assuming "recruiters" is the Thymeleaf template name
    }

    @GetMapping("/createTeamleader")
    public String showCreateTeamLeaderForm(Model model) {
        model.addAttribute("teamLeader", new TeamLeader());
        return "admin/createTeamleader"; // Assuming "createRecruiter" is the name of your Thymeleaf template
    }

    @PostMapping("/createTeamleader") // Update the mapping to match your form action
    public String createTeamLeader(@ModelAttribute TeamLeader teamLeader, BindingResult bindingResult, Model model) {

        if (!isValidPassword(teamLeader.getPassword())) {
            bindingResult.rejectValue("password", "error.teamLeader",
                    "Password must be at least 8 characters long and contain at least one letter and one digit.");
        }

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            model.addAttribute("teamLeader", teamLeader);
            // If there are errors, return to the form with the validation messages
            return "admin/createTeamleader";
        }

        // Process the form submission and save the recruiter
        adminService.saveTeamLeader(teamLeader);

        sendCredentialsByEmail(teamLeader.getEmail(), teamLeader.getUsername(), teamLeader.getPassword());

        // Redirect to a success page or another appropriate endpoint
        return "redirect:admin/dashboard";
    }

    private boolean isValidPassword(String password) {
        // Add your password pattern validation logic here
        // For now, using a simple check for demonstration purposes
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    private void sendCredentialsByEmail(String toEmail, String username, String password) {
        String subject = "Credentials for Team Leader Account";
        String body = "Dear Team Leader,\n\nYour account has been created with the following credentials:\n\n"
                + "Username: " + username + "\nPassword: " + password + "\n\nPlease keep your credentials secure.";

        emailSenderService.sendSimpleEmail(toEmail, body, subject);
    }

    @GetMapping("/viewTeamLeaders")
    public String showAllTeamLeaders(Model model) {
        List<TeamLeader> teamLeaders = adminService.getAllTeamLeaders();
        model.addAttribute("teamLeaders", teamLeaders);
        return "admin/viewTeamLeaders";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        // Logic for the dashboard
        return "dashboard";
    }

    @GetMapping("/adminPanel")
    public String adminPanel() {
        return "admin/adminPanel";
    }

    @GetMapping("/teamLeaderPanel")
    public String teamLeaderPanel() {
        return "teamLeaderPanel";
    }

    @GetMapping("/recruiterPanel")
    public String recruiterPanel() {
        return "recruiterPanel";
    }

    @GetMapping("/loginPage")
    public String ApplicantLoginForm() {
        return "loginPage";
    }

    @PostMapping("/loginPage")
    public String processLoginForm(Admin admin, Applicant applicant) {

        return "dashboard";
    }

    @GetMapping("/register_form")
    public String Registration() {

        return "register";
    }

    @PostMapping("/register_form")
    public String SubmitRegistration(@ModelAttribute Applicant applicant, Model model) {
        sendApplicantCredentialsByEmail(applicant.getEmail(), applicant.getUsername(), applicant.getPassword());
        applicant.setPassword(passwordEncoder.encode(applicant.getPassword()));
        applicantService.saveApplicant(applicant);


        return "loginPage";
    }

    private void sendApplicantCredentialsByEmail(String toEmail, String username, String password) {
        String subject = "Credentials for Applicant Account";
        String body = "Dear Applicant,\n\nYour account has been created with the following credentials:\n\n"
                + "Username: " + username + "\nPassword: " + password + "\n\nPlease keep your credentials secure.";

        emailSenderService.sendSimpleEmail(toEmail, body, subject);
    }


    @GetMapping("/contact")
    public String Contact() {
        return "contact";
    }

}
