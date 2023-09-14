package com.sachin.projectmagement.service.impl;

import com.sachin.projectmagement.dto.TechLeadDTO;
import com.sachin.projectmagement.service.TechLeadService;
import com.sachin.projectmagement.service.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

public class TechLeadServiceImpl implements TechLeadService {
    @Override
    public String save(TechLeadDTO techLeadDTO) {
        return null;
    }

    @Override
    public void delete(String techLeadId) throws NotFoundException {

    }

    @Override
    public void update(String techLeadId, TechLeadDTO techLeadDTO) throws NotFoundException {

    }

    @Override
    public Optional<TechLeadDTO> get(String techLeadId) {
        return Optional.empty();
    }

    @Override
    public List<TechLeadDTO> getAll() {
        return null;
    }
}
