package com.sachin.projectmagement.service;

import com.sachin.projectmagement.dto.ProjectDTO;
import com.sachin.projectmagement.exception.NotFoundException;
import java.util.List;

public interface ProjectService {

    String save(ProjectDTO projectDTO);

    void delete(String projectId) throws NotFoundException;

    void update(String projectId, ProjectDTO projectDTO) throws NotFoundException;

    ProjectDTO get(String projectId)throws NotFoundException;

    List<ProjectDTO> getAll();

}
