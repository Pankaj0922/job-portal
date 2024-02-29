package com.portal.job.repository;

import com.portal.job.entities.TeamLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamLeaderRepository extends JpaRepository<TeamLeader, Long> {
}
