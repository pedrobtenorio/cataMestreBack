package com.p3p.catamestre.Service;

import com.p3p.catamestre.Domain.Studies;
import com.p3p.catamestre.Domain.StudiesOccurrence;
import com.p3p.catamestre.Domain.User;
import com.p3p.catamestre.Repository.StudiesOccurrenceRepository;
import com.p3p.catamestre.Repository.StudiesRepository;
import com.p3p.catamestre.Repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudiesService {

    final private StudiesRepository studiesRepository;
    final private StudiesOccurrenceRepository studiesOccurrenceRepository;
    final private UserRepository userRepository;

    @Autowired
    public StudiesService(StudiesRepository studiesRepository, StudiesOccurrenceRepository studiesOccurrenceRepository, UserRepository userRepository) {
        this.studiesRepository = studiesRepository;
        this.studiesOccurrenceRepository = studiesOccurrenceRepository;
        this.userRepository = userRepository;
    }

    @PersistenceContext
    EntityManager entityManager;

    public Studies getByCode(String code) {
        Optional<Studies> studiesOptional = studiesRepository.findByCode(code);

        if (studiesOptional.isPresent()) {
            return studiesOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Studies not found");
        }
    }

    public Studies getByName(String name) {
        Optional<Studies> studiesOptional = studiesRepository.findByName(name);

        if (studiesOptional.isPresent()) {
            return studiesOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Studies not found");
        }
    }

    public Page<Studies> findAll(Pageable pageable) {
        return studiesRepository.findAll(pageable);
    }

    public List<Studies> getAllByTerm(String term) {
        return studiesRepository.findAllByTerm(term);
    }

    public List<Studies> getAllByModality(Studies.Modality modality) {
        return studiesRepository.findAllByModality(modality);
    }

    public Studies create(Studies studies) {
        if (studiesRepository.findByCode(studies.getCode()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        studies = this.studiesRepository.save(studies);
        List<StudiesOccurrence> studiesOccurrences = studies.getOccurrences();
        for (StudiesOccurrence studiesOccurrence : studiesOccurrences) {
            studiesOccurrence.setStudies(studies);
            this.studiesOccurrenceRepository.save(studiesOccurrence);
        }
        return this.studiesRepository.save(studies);
    }

    public Studies delete(Long id) {
        Optional<Studies> studiesOptional = studiesRepository.findById(id);
        if (studiesOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        } else {
            Studies study = studiesOptional.get();
            List<StudiesOccurrence> occurrences = study.getOccurrences();

            for (StudiesOccurrence occurrence : occurrences) {
                occurrence.setStudies(null);
                studiesOccurrenceRepository.delete(occurrence);
            }

            this.studiesRepository.deleteById(id);

            return study;
        }

    }

    public Studies update(Studies studies) {
        Optional<Studies> studiesOptional = studiesRepository.findByCode(studies.getCode());
        if (studiesOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        } else {
            Studies study = studiesOptional.get();
            study.setName(studies.getName());
            study.setColor(studies.getColor());
            study.setModality(studies.getModality());
            study.setTerm(studies.getTerm());

            List<StudiesOccurrence> existingOccurrences = study.getOccurrences();
            List<StudiesOccurrence> updatedOccurrences = studies.getOccurrences();

            // Delete occurrences that no longer exist in the updated study
            // TODO: não está deletando as ocorrências de fato, apenas tirando seu studies_id
            for (StudiesOccurrence existingOccurrence : existingOccurrences) {
                if (!updatedOccurrences.contains(existingOccurrence)) {
                    existingOccurrence.setStudies(null);
                    studiesOccurrenceRepository.delete(existingOccurrence);
                }
            }

            // Update or add new occurrences to the study
            for (StudiesOccurrence updatedOccurrence : updatedOccurrences) {
                updatedOccurrence.setStudies(study);
                studiesOccurrenceRepository.save(updatedOccurrence);
            }

            return studiesRepository.save(study);
        }


    }

//    public StudiesOccurrence update(Long id, StudiesOccurrence occurrence) {
//        StudiesOccurrence existingOccurrence = occurrenceRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Study occurrence not found"));
//        existingOccurrence.setStudies(occurrence.getStudies());
//        existingOccurrence.setClassroom(occurrence.getClassroom());
//        existingOccurrence.setProfessor(occurrence.getProfessor());
//        existingOccurrence.setTime(occurrence.getTime());
//        existingOccurrence.setType(occurrence.getType());
//        existingOccurrence.setWeekDay(occurrence.getWeekDay());
//
//        return occurrenceRepository.save(existingOccurrence);
//    }


    public Studies getById(Long id) {
        return studiesRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public List<Studies> getStudiesByProfessorId(Long professorId) {
        User professor = userRepository.findById(professorId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        List<Studies> studiesList = entityManager.createQuery("SELECT so.studies FROM StudiesOccurrence so WHERE so.professor = :professor", Studies.class)
                .setParameter("professor", professor)
                .getResultList();

        for (Studies studies : studiesList) {
            List<StudiesOccurrence> occurrences = studies.getOccurrences();
            occurrences.removeIf(occurrence -> !occurrence.getProfessor().equals(professor));
        }

        return studiesList;
    }


}
