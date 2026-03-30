package com.studyplanet.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.studyplanet.jobportal.model.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
}