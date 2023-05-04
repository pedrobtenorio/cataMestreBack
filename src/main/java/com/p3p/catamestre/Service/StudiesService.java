package com.p3p.catamestre.Service;

import com.p3p.catamestre.Domain.Studies;
import com.p3p.catamestre.Domain.User;
import com.p3p.catamestre.Repository.StudiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class StudiesService {

    final private StudiesRepository studiesRepository;

    @Autowired
    public StudiesService(StudiesRepository studiesRepository) {
        this.studiesRepository = studiesRepository;
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

    public List<Studies> getAllByTerm(String term) {
        return studiesRepository.findAllByTerm(term);
    }

    public List<Studies> getAllByModality(Studies.Modality modality) {
        return studiesRepository.findAllByModality(modality);
    }



}
