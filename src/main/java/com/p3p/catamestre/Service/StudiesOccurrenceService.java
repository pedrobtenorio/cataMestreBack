package com.p3p.catamestre.Service;

import com.p3p.catamestre.Domain.Studies;
import com.p3p.catamestre.Domain.StudiesOccurrence;
import com.p3p.catamestre.Domain.User;
import com.p3p.catamestre.Repository.StudiesOccurrenceRepository;
import com.p3p.catamestre.Repository.StudiesRepository;
import com.p3p.catamestre.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class StudiesOccurrenceService {


    final private StudiesOccurrenceRepository occurrenceRepository;
    final private StudiesRepository studiesRepository;
    final private UserRepository userRepository;

    @Autowired
    public StudiesOccurrenceService(StudiesOccurrenceRepository occurrenceRepository, StudiesRepository studiesRepository, UserRepository userRepository) {
        this.occurrenceRepository = occurrenceRepository;
        this.studiesRepository = studiesRepository;
        this.userRepository = userRepository;
    }

    public Page<StudiesOccurrence> getAll(Pageable pageable) {
        return occurrenceRepository.findAll(pageable);
    }

    public List<StudiesOccurrence> getOccurrencesByWeekDay(String weekDay) {
        return occurrenceRepository.findAllByWeekDay(weekDay);
    }

    public List<StudiesOccurrence> getAllByProfessor(Long professor_id) {
        return occurrenceRepository.findAllByProfessor(
                userRepository.getReferenceById(professor_id)
        );
    }

    public List<StudiesOccurrence> getAllByStudies(Long studies_id) {
        return occurrenceRepository.findAllByStudies(studies_id);
    }

    public List<StudiesOccurrence> getAllByTime(String time) {
        return occurrenceRepository.findAllByTime(time);
    }

    public List<StudiesOccurrence> getAllByClassroom(String classroom) {
        return occurrenceRepository.findAllByClassroom(classroom);
    }

    ;

    public List<StudiesOccurrence> getAllByType(StudiesOccurrence.StudyType type) {
        return occurrenceRepository.findAllByType(type);
    }

    public List<StudiesOccurrence> getAllActiveOccurrences() {
        return occurrenceRepository.findAllByActiveTrue();
    }


    public StudiesOccurrence create(StudiesOccurrence studiesOccurrence, Long studyId) {
        Optional<Studies> studiesOptional = this.studiesRepository.findById(studyId);
        if (studiesOptional.isPresent()) {
            Studies studies = studiesOptional.get();
            studies.getOccurrences().add(studiesOccurrence); // add the new occurrence to the list
            this.studiesRepository.save(studies); // save the updated Studies object
            studiesOccurrence =  this.occurrenceRepository.save(studiesOccurrence); // save the StudiesOccurrence object
            return studiesOccurrence;
        }
        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
    }


}
