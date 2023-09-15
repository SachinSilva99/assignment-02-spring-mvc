package com.sachin.projectmagement.api;


import com.sachin.projectmagement.dto.TechLeadDTO;
import com.sachin.projectmagement.service.TechLeadService;
import com.sachin.projectmagement.service.exception.NotFoundException;
import com.sachin.projectmagement.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("api/v1/techlead")
public class TechLeadController {
    private final TechLeadService leadService;

    public TechLeadController(TechLeadService leadService) {
        this.leadService = leadService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> save(
            @RequestPart String name,
            @RequestPart String address,
            @RequestPart byte[] profile
    ) {

        ResponseEntity<StandardResponse> BAD_REQUEST = validateFields(name, address, profile);
        if (BAD_REQUEST != null) return BAD_REQUEST;

        String sProfile = Base64.getEncoder().encodeToString(profile);
        TechLeadDTO techLeadDTO = new TechLeadDTO(name, address, sProfile);
        return new ResponseEntity<>(
                new StandardResponse(201, " created", leadService.save(techLeadDTO)),
                HttpStatus.CREATED);
    }


    @GetMapping(value = "/{techLeadId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> get(@PathVariable String techLeadId) throws NotFoundException {
        TechLeadDTO techLeadDTO = leadService.get(techLeadId);
        return new ResponseEntity<>(new StandardResponse(200, techLeadId, techLeadDTO), HttpStatus.OK);
    }

    @PutMapping("/{techLeadId}")
    public ResponseEntity<StandardResponse> update(
            @RequestPart String name,
            @RequestPart String address,
            @RequestPart byte[] profile,
            @PathVariable String techLeadId) {
        ResponseEntity<StandardResponse> BAD_REQUEST = validateFields(name, address, profile);
        if (BAD_REQUEST != null) return BAD_REQUEST;

        String sProfile = Base64.getEncoder().encodeToString(profile);
        TechLeadDTO techLeadDTO = new TechLeadDTO(name, address, sProfile);
        leadService.update(techLeadId, techLeadDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{techLeadId}")
    public ResponseEntity<StandardResponse> delete(@PathVariable String techLeadId) throws NotFoundException {
        leadService.delete(techLeadId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<StandardResponse> getAll() throws NotFoundException {
        List<TechLeadDTO> techLeadDTOList = leadService.getAll();
        return new ResponseEntity<>(new StandardResponse(200, "ok", techLeadDTOList), HttpStatus.OK);
    }

    private ResponseEntity<StandardResponse> validateFields(String name, String address, byte[] profile) {
        if (name == null || address == null || profile == null) {
            return new ResponseEntity<>(
                    new StandardResponse(400, " invalid data", null),
                    HttpStatus.BAD_REQUEST
            );
        }
        return null;
    }
}
