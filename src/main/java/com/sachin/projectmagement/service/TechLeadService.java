package com.sachin.projectmagement.service;

import com.sachin.projectmagement.dto.TechLeadDTO;
import com.sachin.projectmagement.exception.NotFoundException;

import java.util.List;

public interface TechLeadService {
    String save(TechLeadDTO techLeadDTO);

    void delete(String techLeadId) throws NotFoundException;

    void update(String techLeadId, TechLeadDTO techLeadDTO) throws NotFoundException;

    TechLeadDTO get(String techLeadId);

    List<TechLeadDTO> getAll();
}
