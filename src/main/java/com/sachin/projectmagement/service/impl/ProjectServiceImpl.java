package com.sachin.projectmagement.service.impl;

import com.sachin.projectmagement.dto.ProjectDTO;
import com.sachin.projectmagement.entity.Project;
import com.sachin.projectmagement.repo.ProjectRepo;
import com.sachin.projectmagement.service.ProjectService;
import com.sachin.projectmagement.service.exception.NotFoundException;
import com.sachin.projectmagement.util.idgenerator.IdGenerator;
import com.sachin.projectmagement.util.idgenerator.IdGeneratorImpl;
import com.sachin.projectmagement.util.mapper.DTOConversion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepo projectRepo;
    private final IdGenerator idGenerator;

    private final DTOConversion conversion;

    public ProjectServiceImpl(ProjectRepo projectRepo, IdGeneratorImpl idGeneratorImpl, DTOConversion conversion) {
        this.projectRepo = projectRepo;
        this.idGenerator = idGeneratorImpl;
        this.conversion = conversion;
    }

    @Override
    public String save(ProjectDTO projectDTO) {
        String id = idGenerator.generateRandomID(10);
        while (projectRepo.findById(id).isPresent()) {
            id = idGenerator.generateRandomID(10);
        }
        projectDTO.setId(id);
        return projectRepo.save(conversion.toProject(projectDTO)).getId();
    }

    @Override
    public void delete(String projectId) throws NotFoundException {
        Optional<Project> byId = projectRepo.findById(projectId);
        if (byId.isEmpty()) {
            throw new NotFoundException(projectId + " not found");
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
        projectRepo.save(project);
    }

    @Override
    public Optional<ProjectDTO> get(String projectId) {
        Optional<Project> byId = projectRepo.findById(projectId);
        return byId.map(conversion::toProjectDto);
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
