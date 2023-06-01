package com.p3p.catamestre.Controller;

import com.p3p.catamestre.Domain.Studies;
import com.p3p.catamestre.Domain.StudiesOccurrence;
import com.p3p.catamestre.Domain.User;
import com.p3p.catamestre.Repository.StudiesOccurrenceRepository;
import com.p3p.catamestre.Service.StudiesOccurrenceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studies_occurrence")
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
    @ApiOperation(value = "Get all studies occurrences", notes = "Get a list of all studies occurrences")
    public ResponseEntity<Page<StudiesOccurrence>> getAllActiveOccurrences(Pageable pageable) {
        Page<StudiesOccurrence> studiesOccurrences = occurrenceService.getAll(pageable);
        return new ResponseEntity<>(studiesOccurrences, HttpStatus.OK);
    }


    @PostMapping("/create/{studyId}")
    @ApiOperation(value="create new study occurrence")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Study Occurrence created created successfully"),
            @ApiResponse(code = 406, message = "Not possible to create due to missing id"),
    })
    public ResponseEntity<StudiesOccurrence> create(@RequestBody StudiesOccurrence studiesOccurrence, @PathVariable Long studyId  ) {
        StudiesOccurrence createdStudiesOccurrence =  this.occurrenceService.create(studiesOccurrence, studyId);
        return new ResponseEntity<>(createdStudiesOccurrence, HttpStatus.OK);
    }

    @PutMapping("/{id}/{studyId}")
    @ApiOperation(value = "Update a studies occurrence", notes = "Update an existing studies occurence.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Occurence updated successfully"),
            @ApiResponse(code = 404, message = "Occurence not found by ID")
    })
    public ResponseEntity<StudiesOccurrence> update(@PathVariable Long id, @PathVariable Long studyId, @RequestBody StudiesOccurrence occurrence) {
        StudiesOccurrence updatedOccurrence = this.occurrenceService.update(id, studyId,  occurrence);
        return new ResponseEntity<>(updatedOccurrence, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a study occurrence", notes = "Delete a study occurrence by its ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "User deleted successfully"),
            @ApiResponse(code = 404, message = "User not found by ID")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.occurrenceService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get occurrence by ID", notes = "Get a user by its occurrence.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Occurrence returned successfully"),
            @ApiResponse(code = 404, message = "Occurrence not found by ID")
    })
    public ResponseEntity<StudiesOccurrence> getOccurrenceById(@PathVariable Long id) {
        StudiesOccurrence occurrence = occurrenceService.getOccurrenceById(id);
        return new ResponseEntity<>(occurrence, HttpStatus.OK);
    }

}
