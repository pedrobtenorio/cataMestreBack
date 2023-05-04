package com.p3p.catamestre.Controller;
import com.p3p.catamestre.Domain.AuthResponse;
import com.p3p.catamestre.Domain.User;
import com.p3p.catamestre.Security.Credential;
import com.p3p.catamestre.Service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ApiOperation(value = "Get all users", notes = "Get a list of all users.")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/me")
    @ApiOperation(value = "Get current logged user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User returned successfully"),
            @ApiResponse(code = 404, message = "User not found ")
    })
    public ResponseEntity<User>  getUserById(Principal principal) {
        User user = this.userService.getLoggedUser(principal);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get user by ID", notes = "Get a user by its ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User returned successfully"),
            @ApiResponse(code = 404, message = "User not found by ID")
    })
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    @ApiOperation(value = "Get user by email", notes = "Get a user by its email.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User returned successfully"),
            @ApiResponse(code = 404, message = "User not found by email")
    })
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create a user", notes = "Create a new user.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User created successfully")
    })
    public ResponseEntity<User> createUser(@RequestBody User user) throws Exception {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a user", notes = "Update an existing user.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User updated successfully"),
            @ApiResponse(code = 404, message = "User not found by ID")
    })
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a user", notes = "Delete a user by its ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "User deleted successfully"),
            @ApiResponse(code = 404, message = "User not found by ID")
    })
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Authenticate user token by comparing insert password with the database password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User authenticated successfully."),
            @ApiResponse(code = 401, message = "password dont match."),
    })
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody Credential credential) {
        return this.userService.authenticate(credential);
    }

}
