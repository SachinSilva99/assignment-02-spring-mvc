package com.sachin.projectmagement.entity;

import com.sachin.projectmagement.entity.enums.PRIORITY;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Project implements SuperEntity{

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PRIORITY priority;

    @ManyToOne
    @JoinColumn(name = "teach_lead_id", referencedColumnName = "id")
    private TechLead techLead;
}
