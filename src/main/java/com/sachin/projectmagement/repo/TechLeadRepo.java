package com.sachin.projectmagement.repo;

import com.sachin.projectmagement.entity.TechLead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechLeadRepo extends JpaRepository<TechLead, String> {
}
