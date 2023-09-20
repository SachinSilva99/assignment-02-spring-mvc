package com.sachin.projectmagement.service.impl;

import com.sachin.projectmagement.dto.ProjectDTO;
import com.sachin.projectmagement.entity.Project;
import com.sachin.projectmagement.entity.TechLead;
import com.sachin.projectmagement.repo.ProjectRepo;
import com.sachin.projectmagement.repo.TechLeadRepo;
import com.sachin.projectmagement.service.ProjectService;
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
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepo projectRepo;
    private final TechLeadRepo techLeadRepo;
    private final DTOConversion conversion;



    @Override
    public String save(ProjectDTO projectDTO) {
        projectDTO.setId(UUID.randomUUID().toString());
        String techLeadId = projectDTO.getTechLeadId();
        Optional<TechLead> byId = techLeadRepo.findById(techLeadId);
        if (byId.isEmpty()) {
            throw new NotFoundException("TechLead id : " + techLeadId + " not found");
        }
        TechLead techLead = byId.get();
        Project project = conversion.toProject(projectDTO);
        project.setTechLead(techLead);
        return projectRepo.save(project).getId();
    }

    @Override
    public void delete(String projectId) throws NotFoundException {
        Optional<Project> byId = projectRepo.findById(projectId);
        if (byId.isEmpty()) {
            throw new NotFoundException("project id : " + projectId + " not found");
        }
        projectRepo.delete(byId.get());
    }

    @Override
    public void update(String projectId, ProjectDTO dto) throws NotFoundException {
        Optional<Project> byId = projectRepo.findById(projectId);
        if (byId.isEmpty()) {
            throw new NotFoundException(projectId + " not found");
        }
        Project project = byId.get();
        project.setDescription(dto.getDescription());
        project.setName(dto.getName());
        project.setPriority(dto.getPriority());
        project.setStartDate(dto.getStartDate());
        project.setEndDate(dto.getEndDate());

        String techLeadId = dto.getTechLeadId();
        Optional<TechLead> techLeadById = techLeadRepo.findById(techLeadId);
        if (techLeadById.isEmpty()) {
            throw new NotFoundException("TechLead id : " + techLeadId + " not found");
        }
        TechLead techLead = techLeadById.get();
        project.setTechLead(techLead);
//        projectRepo.save(project);
    }

    @Override
    public ProjectDTO get(String projectId) throws NotFoundException{
        Optional<Project> byId = projectRepo.findById(projectId);
        if (byId.isEmpty()) {
            throw new NotFoundException("project id : " + projectId + " not found");
        }
        return conversion.toProjectDto(byId.get());
    }

    @Override
    public List<ProjectDTO> getAll() {
        return projectRepo
                .findAll()
                .stream()
                .map(conversion::toProjectDto)
                .collect(Collectors.toList());
    }
}
