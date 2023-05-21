package com.p3p.catamestre.Service;

import com.p3p.catamestre.Domain.Studies;
import com.p3p.catamestre.Domain.StudiesOccurrence;
import com.p3p.catamestre.Repository.StudiesOccurrenceRepository;
import com.p3p.catamestre.Repository.StudiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class StudiesService {

    final private StudiesRepository studiesRepository;
    final private StudiesOccurrenceRepository studiesOccurrenceRepository;

    @Autowired
    public StudiesService(StudiesRepository studiesRepository, StudiesOccurrenceRepository studiesOccurrenceRepository) {
        this.studiesRepository = studiesRepository;
        this.studiesOccurrenceRepository = studiesOccurrenceRepository;
    }

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
        for (StudiesOccurrence studiesOccurrence: studiesOccurrences) {
            studiesOccurrence.setStudies(studies);
            this.studiesOccurrenceRepository.save(studiesOccurrence);
        }
        return this.studiesRepository.save(studies);
    }

    public Studies getById(Long id) {
        return studiesRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }



}
