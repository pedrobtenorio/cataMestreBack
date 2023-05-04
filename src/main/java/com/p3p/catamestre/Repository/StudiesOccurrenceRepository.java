package com.p3p.catamestre.Repository;

import com.p3p.catamestre.Domain.Studies;
import com.p3p.catamestre.Domain.StudiesOccurrence;
import com.p3p.catamestre.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudiesOccurrenceRepository extends JpaRepository<StudiesOccurrence, Long> {

    List<StudiesOccurrence> findAllByWeekDay(String weekDay);
    List<StudiesOccurrence> findAllByProfessor(User professor);
    List<StudiesOccurrence> findAllByStudies(Studies studies);
    List<StudiesOccurrence> findAllByTime(String time);
    List<StudiesOccurrence> findAllByClassroom(String classroom);
    List<StudiesOccurrence> findAllByType(StudiesOccurrence.StudyType type);
    List<StudiesOccurrence> findAllByActiveTrue();

}
