package com.portal.job.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.job.entities.Vacancy;
import com.portal.job.repository.VacancyRepository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Optional;

@Service
public class VacancyServiceImpl implements VacancyService {

    @Autowired
    private VacancyRepository vacancyRepository;

    @Override
    public Vacancy saveJob(Vacancy vacancy) {
        vacancy.getTitle();
        vacancy.getLocation();
        vacancy.getCategory();
        vacancy.getDescription();
        vacancy.getStatus();
        return vacancyRepository.save(vacancy);
    }

    @Override
    public List<Vacancy> getAllVacancies() {
        return vacancyRepository.findAll();
    }

    @Override
    public Vacancy getJobByVacancy_Id(Long id) {
        Optional<Vacancy> vacancy = vacancyRepository.findById(id);
        return vacancy.orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        Optional<Vacancy> optionalVacancy = vacancyRepository.findById(id);
        if (optionalVacancy.isPresent()) {
            vacancyRepository.delete(optionalVacancy.get());
            return true;
        }
        return false;
    }

    public void removeSessionMessage() {
        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest().getSession();
        session.removeAttribute("msg");
    }
}
