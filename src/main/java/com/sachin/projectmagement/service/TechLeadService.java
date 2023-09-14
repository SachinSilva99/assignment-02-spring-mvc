package com.sachin.projectmagement.service;

import com.sachin.projectmagement.dto.TechLeadDTO;
import com.sachin.projectmagement.service.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface TechLeadService {
    String save(TechLeadDTO techLeadDTO);

    void delete(String techLeadId) throws NotFoundException;

    void update(String techLeadId, TechLeadDTO techLeadDTO) throws NotFoundException;

    Optional<TechLeadDTO> get(String techLeadId);

    List<TechLeadDTO> getAll();
}
