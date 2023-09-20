package com.sachin.projectmagement.util.mapper.impl;

import com.sachin.projectmagement.dto.ProjectDTO;
import com.sachin.projectmagement.dto.TechLeadDTO;
import com.sachin.projectmagement.entity.Project;
import com.sachin.projectmagement.entity.TechLead;
import com.sachin.projectmagement.util.mapper.DTOConversion;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DTOConversionImpl implements DTOConversion {

    private final ModelMapper mapper;


    @Override
    public ProjectDTO toProjectDto(Project project) {
        return mapper.map(project, ProjectDTO.class);
    }

    @Override
    public Project toProject(ProjectDTO projectDTO) {
        return mapper.map(projectDTO, Project.class);
    }

    @Override
    public TechLeadDTO toTechLeadDto(TechLead techLead) {
        return mapper.map(techLead, TechLeadDTO.class);
    }

    @Override
    public TechLead toTechLead(TechLeadDTO techLeadDTO) {
        return mapper.map(techLeadDTO, TechLead.class);
    }
}
