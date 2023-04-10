package com.p3p.catamestre.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "studies")
@Getter
@Setter
public class Studies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    private User professor;


    private String name;

    private String classroom;

    @Enumerated(EnumType.STRING)
    private StudyType type;

    private String time;

    private String onlineLink;

    private String onlineClassroom;

    private String notes;

    private String color;

    private boolean active;

    public enum StudyType {
        PRESENCIAL, ONLINE, CANCELED
    }
}
