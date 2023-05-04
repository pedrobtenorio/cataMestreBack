package com.p3p.catamestre.Controller;

import com.p3p.catamestre.Domain.Studies;
import com.p3p.catamestre.Domain.User;
import com.p3p.catamestre.Service.StudiesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
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


//    @PostMapping("/create")
//    @ApiOperation(value = "Create a user", notes = "Create a new user.")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "User created successfully")
//    })
//    public ResponseEntity<User> createUser(@RequestBody User user) throws Exception {
//        User createdUser = userService.createUser(user);
//        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}")
//    @ApiOperation(value = "Update a user", notes = "Update an existing user.")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "User updated successfully"),
//            @ApiResponse(code = 404, message = "User not found by ID")
//    })
//    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
//        User updatedUser = userService.updateUser(id, user);
//        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    @ApiOperation(value = "Delete a user", notes = "Delete a user by its ID.")
//    @ApiResponses(value = {
//            @ApiResponse(code = 204, message = "User deleted successfully"),
//            @ApiResponse(code = 404, message = "User not found by ID")
//    })
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }



}
