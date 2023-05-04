package com.p3p.catamestre.Controller;

import com.p3p.catamestre.Domain.Studies;
import com.p3p.catamestre.Domain.StudiesOccurrence;
import com.p3p.catamestre.Domain.User;
import com.p3p.catamestre.Repository.StudiesOccurrenceRepository;
import com.p3p.catamestre.Service.StudiesOccurrenceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/studies_occurence")
public class StudiesOccurenceController {

    @Autowired
    StudiesOccurrenceService occurrenceService;

    @GetMapping("/weekday/{weekday}")
    @ApiOperation(value = "Get all studies occurrences in a weekday", notes = "Get a list of all studies occurrences in a weekday")
    public ResponseEntity<List<StudiesOccurrence>> getAllByWeekDay(@PathVariable String weekday) {
        List<StudiesOccurrence> studiesOccurrences = occurrenceService.getOccurrencesByWeekDay(weekday);
        return new ResponseEntity<>(studiesOccurrences, HttpStatus.OK);
    }

    @GetMapping("/professor/{professor}")
    @ApiOperation(value = "Get all studies occurrences from a professor", notes = "Get a list of all studies occurrences from a professor")
    public ResponseEntity<List<StudiesOccurrence>> getAllByProfessor(@PathVariable Long professor_id) {
        List<StudiesOccurrence> studiesOccurrences = occurrenceService.getAllByProfessor(professor_id);
        return new ResponseEntity<>(studiesOccurrences, HttpStatus.OK);
    }

    @GetMapping("/time/{time}")
    @ApiOperation(value = "Get all studies occurrences on a certain time", notes = "Get a list of all studies occurrences on a certain time")
    public ResponseEntity<List<StudiesOccurrence>> getAllByTime(@PathVariable String time) {
        List<StudiesOccurrence> studiesOccurrences = occurrenceService.getAllByTime(time);
        return new ResponseEntity<>(studiesOccurrences, HttpStatus.OK);
    }

    @GetMapping("/classroom/{classroom}")
    @ApiOperation(value = "Get all studies occurrences on a certain classroom", notes = "Get a list of all studies occurrences on a certain classroom")
    public ResponseEntity<List<StudiesOccurrence>> getAllByClassroom(@PathVariable String classroom) {
        List<StudiesOccurrence> studiesOccurrences = occurrenceService.getAllByClassroom(classroom);
        return new ResponseEntity<>(studiesOccurrences, HttpStatus.OK);
    }

    @GetMapping("/modality/{type}")
    @ApiOperation(value = "Get all studies occurrences from a certain modality", notes = "Get a list of all studies occurrences from a certain modality")
    public ResponseEntity<List<StudiesOccurrence>> getAllByType(@PathVariable StudiesOccurrence.StudyType type) {
        List<StudiesOccurrence> studiesOccurrences = occurrenceService.getAllByType(type);
        return new ResponseEntity<>(studiesOccurrences, HttpStatus.OK);
    }

    @GetMapping("/study/{study_id}")
    @ApiOperation(value = "Get all studies occurrences from a certain study", notes = "Get a list of all studies occurrences from a certain study")
    public ResponseEntity<List<StudiesOccurrence>> getAllByStudies(@PathVariable Long study_id) {
        List<StudiesOccurrence> studiesOccurrences = occurrenceService.getAllByStudies(study_id);
        return new ResponseEntity<>(studiesOccurrences, HttpStatus.OK);
    }

    @GetMapping("/all")
    @ApiOperation(value = "Get all studies occurrences that are active", notes = "Get a list of all studies occurrences that are active")
    public ResponseEntity<List<StudiesOccurrence>> getAllActiveOccurrences() {
        List<StudiesOccurrence> studiesOccurrences = occurrenceService.getAllActiveOccurrences();
        return new ResponseEntity<>(studiesOccurrences, HttpStatus.OK);
    }


}
