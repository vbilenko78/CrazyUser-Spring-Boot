package com.vbilenko.springmvc.controller.rest;

import com.vbilenko.springmvc.model.User;
import com.vbilenko.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {this.userService = userService;}


    /**
     * This method will list all existing users.
     */
    @GetMapping
    public List<User> listUsers() {
        return userService.findAllUsers();
    }

    /**
     * This method will retrieve specific user by Id.
     */
    @GetMapping("/{id}")
    public User retrieveUser(@PathVariable int id) {
        Optional<User> user = Optional.ofNullable(userService.findById(id));
        if (!user.isPresent())
            System.out.println("Not Found");
        return user.get();
    }

    /**
     * This method will delete an user by it's SSOID value.
     */
    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody User user, @PathVariable int id) {
        Optional<User> studentOptional = Optional.ofNullable(userService.findById(id));
        if (!studentOptional.isPresent())
            return ResponseEntity.notFound().build();
        user.setId(id);
        userService.updateUser(user);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public void createStudent(@RequestBody User user) {
        User savedStudent = userService.saveUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedStudent.getId()).toUri();


    }
}
