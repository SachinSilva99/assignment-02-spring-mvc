package com.sachin.projectmagement.service.impl;

import com.sachin.projectmagement.dto.TechLeadDTO;
import com.sachin.projectmagement.entity.TechLead;
import com.sachin.projectmagement.repo.TechLeadRepo;
import com.sachin.projectmagement.service.TechLeadService;
import com.sachin.projectmagement.exception.NotFoundException;
import com.sachin.projectmagement.util.mapper.DTOConversion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TechLeadServiceImpl implements TechLeadService {

    private final TechLeadRepo techLeadRepo;
    private final DTOConversion conversion;


    @Override
    public String save(TechLeadDTO techLeadDTO) {

        techLeadDTO.setId(UUID.randomUUID().toString());
        return techLeadRepo.save(conversion.toTechLead(techLeadDTO)).getId();
    }

    @Override
    public void delete(String techLeadId) throws NotFoundException {
        Optional<TechLead> byId = techLeadRepo.findById(techLeadId);
        if (byId.isEmpty()) {
            throw new NotFoundException("teachlead id : " + techLeadId + " not found");
        }
        techLeadRepo.delete(byId.get());
    }

    @Override
    public void update(String techLeadId, TechLeadDTO dto) throws NotFoundException {
        Optional<TechLead> byId = techLeadRepo.findById(techLeadId);
        if (byId.isEmpty()) {
            throw new NotFoundException("teachlead id : " + techLeadId + " not found");
        }
        TechLead techLead = byId.get();
        techLead.setName(dto.getName());
        techLead.setAddress(dto.getAddress());
        techLead.setProfile(dto.getProfile());
        techLeadRepo.save(techLead);
    }

    @Override
    public TechLeadDTO get(String techLeadId) throws NotFoundException {
        Optional<TechLead> byId = techLeadRepo.findById(techLeadId);
        if (byId.isEmpty()) {
            throw new NotFoundException("teachlead id : " + techLeadId + " not found");
        }
        return conversion.toTechLeadDto(byId.get());
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
