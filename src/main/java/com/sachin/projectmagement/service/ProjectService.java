package com.sachin.projectmagement.service;

import com.sachin.projectmagement.dto.ProjectDTO;
import com.sachin.projectmagement.service.exception.NotFoundException;
import java.util.List;
import java.util.Optional;

public interface ProjectService {

    String save(ProjectDTO projectDTO);

    void delete(String projectId) throws NotFoundException;

    void update(String projectId, ProjectDTO projectDTO) throws NotFoundException;

    Optional<ProjectDTO> get(String projectId);

    List<ProjectDTO> getAll();

}
