package com.sachin.projectmagement.dto;

import com.sachin.projectmagement.entity.enums.PRIORITY;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectDTO {
    private String id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private PRIORITY priority;
    private String techLeadId;
}
