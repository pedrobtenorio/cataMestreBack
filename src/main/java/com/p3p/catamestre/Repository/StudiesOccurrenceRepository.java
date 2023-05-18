package com.p3p.catamestre.Repository;

import com.p3p.catamestre.Domain.Studies;
import com.p3p.catamestre.Domain.StudiesOccurrence;
import com.p3p.catamestre.Domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudiesOccurrenceRepository extends JpaRepository<StudiesOccurrence, Long> {

    Page<StudiesOccurrence> findAll(Pageable pageable);

//    @Query("SELECT o FROM StudiesOccurrence o WHERE " +
//            "(:weekDay IS NULL OR o.weekDay = :weekDay) AND " +
//            "(:professor IS NULL OR o.professor = :professor) AND " +
//            "(:studies IS NULL OR o.studies = :studies)")
//    List<StudiesOccurrence> findWithOptionalParameters(String weekDay, User professor, Studies studies);


    @Query("""
        SELECT o FROM StudiesOccurrence o WHERE o.studies.id = :studiesId
""")
    List<StudiesOccurrence> findAllByStudies(@Param("studiesId") Long studiesId);

    List<StudiesOccurrence> findAllByWeekDay(String weekDay);
    List<StudiesOccurrence> findAllByProfessor(User professor);
//    List<StudiesOccurrence> findAllByStudies(Studies studies);
    List<StudiesOccurrence> findAllByTime(String time);
    List<StudiesOccurrence> findAllByClassroom(String classroom);
    List<StudiesOccurrence> findAllByType(StudiesOccurrence.StudyType type);
    List<StudiesOccurrence> findAllByActiveTrue();

}
