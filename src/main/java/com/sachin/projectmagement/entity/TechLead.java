package com.sachin.projectmagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TechLead implements SuperEntity{
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column
    private String address;

    @Lob
    @Column(nullable = false, columnDefinition = "LongText")
    private String profile;

    @OneToMany(mappedBy = "techLead", cascade = CascadeType.ALL)
    private List<Project> projects = new ArrayList<>();
}
