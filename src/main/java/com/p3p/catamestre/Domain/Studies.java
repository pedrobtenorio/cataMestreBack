package com.p3p.catamestre.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "studies")
@Getter
@Setter
public class Studies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;



    private String name;

    private String term;



    private String color;

    private String onlineLink;

    private String onlineClassroom;
    @OneToMany(mappedBy = "studies", cascade = CascadeType.ALL)
    private List<StudiesOccurrence> occurrences;


    public enum Modality {
        MANDATORY, ELECTIVE
    }

    @Enumerated(EnumType.STRING)
    private Modality modality;


}
