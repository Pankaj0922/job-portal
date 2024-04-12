package com.portal.job.repository;

import com.portal.job.entities.Applicant;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

    Applicant findByEmail(String email);

    Optional<Applicant> findByUsername(String username);
}
