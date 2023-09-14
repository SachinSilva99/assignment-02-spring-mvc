package com.sachin.projectmagement.service.impl;

import com.sachin.projectmagement.dto.TechLeadDTO;
import com.sachin.projectmagement.entity.TechLead;
import com.sachin.projectmagement.repo.TechLeadRepo;
import com.sachin.projectmagement.service.TechLeadService;
import com.sachin.projectmagement.service.exception.NotFoundException;
import com.sachin.projectmagement.util.idgenerator.IdGenerator;
import com.sachin.projectmagement.util.mapper.DTOConversion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TechLeadServiceImpl implements TechLeadService {
    private final TechLeadRepo techLeadRepo;
    private final IdGenerator idGenerator;

    private final DTOConversion conversion;

    public TechLeadServiceImpl(TechLeadRepo techLeadRepo, IdGenerator idGenerator, DTOConversion conversion) {
        this.techLeadRepo = techLeadRepo;
        this.idGenerator = idGenerator;
        this.conversion = conversion;
    }

    @Override
    public String save(TechLeadDTO techLeadDTO) {
        String id = idGenerator.generateRandomID(10);
        while (techLeadRepo.findById(id).isPresent()) {
            id = idGenerator.generateRandomID(10);
        }
        techLeadDTO.setId(id);
        return techLeadRepo.save(conversion.toTechLead(techLeadDTO)).getId();
    }

    @Override
    public void delete(String techLeadId) throws NotFoundException {
        Optional<TechLead> byId = techLeadRepo.findById(techLeadId);
        if (byId.isEmpty()) {
            throw new NotFoundException("teachlead id : "+ techLeadId + " not found");
        }
        techLeadRepo.delete(byId.get());
    }

    @Override
    public void update(String techLeadId, TechLeadDTO dto) throws NotFoundException {
        Optional<TechLead> byId = techLeadRepo.findById(techLeadId);
        if (byId.isEmpty()) {
            throw new NotFoundException("teachlead id : "+ techLeadId + " not found");
        }
        TechLead techLead = byId.get();
        techLead.setName(dto.getName());
        techLead.setAddress(dto.getAddress());
        techLead.setProfile(dto.getProfile());
        techLeadRepo.save(techLead);
    }

    @Override
    public Optional<TechLeadDTO> get(String techLeadId) {
        Optional<TechLead> byId = techLeadRepo.findById(techLeadId);
        return byId.map(conversion::toTechLeadDto);
    }

    @Override
    public List<TechLeadDTO> getAll() {
        return techLeadRepo
                .findAll()
                .stream()
                .map(conversion::toTechLeadDto)
                .collect(Collectors.toList());
    }
}
