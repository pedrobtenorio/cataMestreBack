package com.p3p.catamestre.Repository;

import com.p3p.catamestre.Domain.Studies;
import com.p3p.catamestre.Domain.StudiesOccurrence;
import com.p3p.catamestre.Domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudiesOccurrenceRepository extends JpaRepository<StudiesOccurrence, Long> {

    Page<StudiesOccurrence> findAll(Pageable pageable);
    List<StudiesOccurrence> findAllByWeekDay(String weekDay);
    List<StudiesOccurrence> findAllByProfessor(User professor);
    List<StudiesOccurrence> findAllByStudies(Studies studies);
    List<StudiesOccurrence> findAllByTime(String time);
    List<StudiesOccurrence> findAllByClassroom(String classroom);
    List<StudiesOccurrence> findAllByType(StudiesOccurrence.StudyType type);
    List<StudiesOccurrence> findAllByActiveTrue();

}
