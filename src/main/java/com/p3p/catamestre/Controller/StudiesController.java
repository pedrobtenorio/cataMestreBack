package com.p3p.catamestre.Controller;

import com.p3p.catamestre.Domain.Studies;
import com.p3p.catamestre.Domain.StudiesOccurrence;
import com.p3p.catamestre.Domain.User;
import com.p3p.catamestre.Service.StudiesService;
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
@RequestMapping("/studies")
public class StudiesController {

    @Autowired
    private StudiesService studiesService;

    @GetMapping("/term/{term}")
    @ApiOperation(value = "Get all studies from a term", notes = "Get a list of all studies from a specific term.")
    public ResponseEntity<List<Studies>> getAllByTerm(@PathVariable String term) {
        List<Studies> studies = studiesService.getAllByTerm(term);
        return new ResponseEntity<>(studies, HttpStatus.OK);
    }

    @GetMapping("/modality/{modality}")
    @ApiOperation(value="Get all studies from a modality", notes="Get a list of all studies from a specific modality.")
    public ResponseEntity<List<Studies>> getAllByModality(@PathVariable Studies.Modality modality) {
        List<Studies> studies = studiesService.getAllByModality(modality);
        return new ResponseEntity<>(studies, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    @ApiOperation(value="Get studies by name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Study returned successfully"),
            @ApiResponse(code = 404, message = "Study not found")
    })
    public ResponseEntity<Studies> getByName(@PathVariable String name) {
        Studies studies = studiesService.getByName(name);
        return new ResponseEntity<>(studies, HttpStatus.OK);
    }

    @GetMapping("/code/{code}")
    @ApiOperation(value="Get studies by code")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Study returned successfully"),
            @ApiResponse(code = 404, message = "Study not found")
    })
    public ResponseEntity<Studies> getByCode(@PathVariable String code) {
        Studies studies = studiesService.getByCode(code);
        return new ResponseEntity<>(studies, HttpStatus.OK);
    }


    @GetMapping("/all")
    public Page<Studies> getStudies(Pageable pageable) {
        return this.studiesService.findAll(pageable);
    }


    @PostMapping("/create")
    @ApiOperation(value="create new study")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Study created successfully"),
            @ApiResponse(code = 401, message = "Study code already exist"),
    })
    public ResponseEntity<Studies> create(@RequestBody Studies studies) {
        Studies createdStudies = this.studiesService.create(studies);
        return new ResponseEntity<>(createdStudies, HttpStatus.OK);
    }

    @PutMapping("/update")
    @ApiOperation(value="Update a study")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Study updated successfully"),
            @ApiResponse(code = 401, message = "Study code doesn't exist"),
    })
    public ResponseEntity<Studies> update(@RequestBody Studies studies) {
        Studies updatedStudies = this.studiesService.update(studies);
        return new ResponseEntity<>(updatedStudies, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="Delete a study")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Study deleted successfully"),
            @ApiResponse(code = 401, message = "Study code doesn't exist"),
    })
    public ResponseEntity<Studies> delete(@PathVariable Long id) {
        Studies deletedStudy = this.studiesService.delete(id);
        return new ResponseEntity<>(deletedStudy, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get study by ID", notes = "Get a study by its Id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Study returned successfully"),
            @ApiResponse(code = 404, message = "Study not found by ID")
    })
    public ResponseEntity<Studies> getById(@PathVariable Long id) {
        Studies studies = studiesService.getById(id);
        return new ResponseEntity<>(studies, HttpStatus.OK);
    }




}
