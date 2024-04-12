package com.portal.job.controller;

import com.portal.job.entities.Recruiter;
import com.portal.job.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.portal.job.entities.Vacancy;
import com.portal.job.repository.VacancyRepository;
import com.portal.job.service.VacancyService;

import java.util.List;
import java.util.Optional;

@Controller
public class TeamLeaderController {

    @Autowired
    private VacancyService vacancyService;

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private AdminService adminService;


    @GetMapping("/viewJob")
    public String viewJob(Model model) {
        List<Vacancy> vacancies = vacancyService.getAllVacancies();
        model.addAttribute("vacancies", vacancies);
        return "viewJob";
    }

    @GetMapping("/postJob")
    public String showPostJobForm(Model model) {
        model.addAttribute("vacancy", new Vacancy());
        return "postJob";
    }

    @PostMapping("/postJob")
    public String submitJob(@ModelAttribute Vacancy vacancy, Model model, HttpSession session) {
        vacancyService.saveJob(vacancy);
        if (vacancy != null) {
//            System.out.println("save success");
            session.setAttribute("msg", "Job Published Successfully");
        } else {
//            System.out.println("save no success");
            session.setAttribute("msg", "Some Error Occurred");
        }

        return "redirect:/postJob";
    }

    @GetMapping("/editJob/{id}")
    public String editJob(@PathVariable(value = "id") Long id, Model model) {
        Vacancy vacancy = vacancyService.getJobByVacancy_Id(id);
        model.addAttribute("vacancy", vacancy);
        return "editJob";
    }


    @PostMapping("/editJob/{id}")
    public String updateEmp(@PathVariable(value = "id") Long id, @ModelAttribute Vacancy updatedVacancy, HttpSession session) {
        // Retrieve the existing job from the database
        Vacancy existingVacancy = vacancyService.getJobByVacancy_Id(id);

        // Update the attributes of the existing job with the new values
        existingVacancy.setTitle(updatedVacancy.getTitle());
        existingVacancy.setActive_date(updatedVacancy.getActive_date());
        existingVacancy.setLocation(updatedVacancy.getLocation());
        existingVacancy.setCategory(updatedVacancy.getCategory());
        existingVacancy.setStatus(updatedVacancy.getStatus());
        existingVacancy.setDescription(updatedVacancy.getDescription());

        // Save the updated job back to the database
        vacancyService.saveJob(existingVacancy);
        if (existingVacancy != null) {
//            System.out.println("save success");
            session.setAttribute("msg", "Updated Successfully");
        } else {
//            System.out.println("save no success");
            session.setAttribute("msg", "not updated Successfully");
        }


        return "redirect:/viewJob";
    }

    @GetMapping("/deleteJob/{id}")
    public String deleteJob(@PathVariable(value = "id") Long id, HttpSession session) {
        boolean f = vacancyService.deleteJobById(id);
        if (f) {
            session.setAttribute("msg", "Job Deleted Successfully");
        } else {
            session.setAttribute("msg", "Some error occurred while deleting");
        }
        return "redirect:/viewJob";
    }

    @GetMapping("/teamLeaderDashboard")
    public String teamLeaderDashboard() {

        return "teamLeader/teamLeaderDashboard";
    }

    @GetMapping("/viewDetails/{id}")
    public String viewDetails(@PathVariable(value = "id") Long id, Model model) {
        Recruiter recruiter = adminService.getRecruiterById(id);
        model.addAttribute("recruiter", recruiter);
        adminService.getRecruiterById(id);
        return "viewDetails";
    }


}
