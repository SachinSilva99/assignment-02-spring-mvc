package com.sachin.projectmagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TechLeadDTO {
    private String id;
    private String name;
    private String address;
    private String profile;

    public TechLeadDTO(String name, String address, String profile) {
        this.name = name;
        this.address = address;
        this.profile = profile;
    }
}
