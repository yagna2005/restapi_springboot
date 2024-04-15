package com.java.velvetvista.controller;

import com.java.velvetvista.model.User;
import com.java.velvetvista.model.Furniture;
import com.java.velvetvista.service.UserService;
import com.java.velvetvista.repository.FurnitureRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final FurnitureRepository furnitureRepo;

    public UserController(UserService userService, FurnitureRepository furnitureRepo) {
        this.userService = userService;
        this.furnitureRepo = furnitureRepo;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Pagination endpoint
    @GetMapping("/paged")
    public ResponseEntity<Page<User>> getAllUsersPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<User> users = userService.getAllUsers(
                PageRequest.of(page, size));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Sorting endpoint
    @GetMapping("/sorted")
    public ResponseEntity<List<User>> getAllUsersSorted(
            @RequestParam(defaultValue = "id") String sortBy) {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/users/{userId}/furniture")
    public ResponseEntity<Furniture> addFurnitureToUser(@PathVariable Integer userId, @RequestBody Furniture furniture) {
        User user = userService.getUserById(userId);
        
        if (user != null) {
            furniture.setUser(user);
            Furniture addedFurniture = furnitureRepo.save(furniture); // Save furniture directly
            
            if (addedFurniture != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(addedFurniture);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
