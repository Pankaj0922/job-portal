package com.portal.job.service;

import com.portal.job.entities.Vacancy;

import java.util.List;
import java.util.Optional;

public interface VacancyService {
    public Vacancy saveJob(Vacancy vacancy);

    public List<Vacancy> getAllVacancies();

    public Vacancy getJobByVacancy_Id(Long id);

    public boolean deleteJobById(Long id);
}