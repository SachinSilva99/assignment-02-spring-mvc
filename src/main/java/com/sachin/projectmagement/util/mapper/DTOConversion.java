package com.sachin.projectmagement.util.mapper;

import com.sachin.projectmagement.dto.ProjectDTO;
import com.sachin.projectmagement.dto.TechLeadDTO;
import com.sachin.projectmagement.entity.Project;
import com.sachin.projectmagement.entity.TechLead;

public interface DTOConversion {
    ProjectDTO toProjectDto(Project project);

    Project toProject(ProjectDTO projectDTO);

    TechLeadDTO toTechLeadDto(TechLead techLead);

    TechLead toTechLead(TechLeadDTO techLeadDTO);
}
