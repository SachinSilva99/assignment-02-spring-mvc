package com.sachin.projectmagement.api;

import com.sachin.projectmagement.dto.ProjectDTO;
import com.sachin.projectmagement.service.ProjectService;
import com.sachin.projectmagement.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> save(@RequestBody ProjectDTO projectDTO) {
        String id = projectService.save(projectDTO);
        return new ResponseEntity<>(new StandardResponse(201, id, null), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> get(@PathVariable String projectId) {
        ProjectDTO projectDTO = projectService.get(projectId);
        return new ResponseEntity<>(new StandardResponse(200, "ok", projectDTO), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> getAll() {
        return new ResponseEntity<>(new StandardResponse(200, "ok", projectService.getAll()), HttpStatus.OK);
    }

    @PutMapping(value = "/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> update(@PathVariable String projectId, @RequestBody ProjectDTO projectDTO) {
        projectService.update(projectId, projectDTO);
        return new ResponseEntity<>(new StandardResponse(), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> delete(@PathVariable String projectId) {
        projectService.delete(projectId);
        return new ResponseEntity<>(new StandardResponse(), HttpStatus.NO_CONTENT);
    }
}
