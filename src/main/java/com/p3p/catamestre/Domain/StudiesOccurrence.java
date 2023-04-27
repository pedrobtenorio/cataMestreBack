package com.p3p.catamestre.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "studies_occurrence")
public class StudiesOccurrence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String weekDay;

    private String time;

    private String notes;

    private boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    private Studies studie;


    public enum StudyType {
        PRESENTIAL, ONLINE, CANCELED
    }

    @Enumerated(EnumType.STRING)
    private StudyType type;
}
