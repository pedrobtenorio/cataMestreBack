package com.p3p.catamestre.Service;

import com.p3p.catamestre.Domain.Studies;
import com.p3p.catamestre.Domain.StudiesOccurrence;
import com.p3p.catamestre.Domain.User;
import com.p3p.catamestre.Repository.StudiesOccurrenceRepository;
import com.p3p.catamestre.Repository.StudiesRepository;
import com.p3p.catamestre.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<StudiesOccurrence> getOccurrencesByWeekDay(String weekDay) {
        return occurrenceRepository.findAllByWeekDay(weekDay);
    }

    public List<StudiesOccurrence> getAllByProfessor(Long professor_id) {
        return occurrenceRepository.findAllByProfessor(
                userRepository.getReferenceById(professor_id)
        );
    }

    public List<StudiesOccurrence> getAllByStudies(Long studies_id) {
        return occurrenceRepository.findAllByStudies(
                studiesRepository.getReferenceById(studies_id)
        );
    }

    public List<StudiesOccurrence> getAllByTime(String time) {
        return occurrenceRepository.findAllByTime(time);
    }

    public List<StudiesOccurrence> getAllByClassroom(String classroom) {
        return occurrenceRepository.findAllByClassroom(classroom);
    };

    public List<StudiesOccurrence> getAllByType(StudiesOccurrence.StudyType type) {
        return occurrenceRepository.findAllByType(type);
    }

    public List<StudiesOccurrence> getAllActiveOccurrences() {
        return occurrenceRepository.findAllByActiveTrue();
    }


}
