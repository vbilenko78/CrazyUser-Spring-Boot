package com.vbilenko.springmvc.controller.rest;

import com.vbilenko.springmvc.model.User;
import com.vbilenko.springmvc.model.UserProfile;
import com.vbilenko.springmvc.service.UserProfileService;
import com.vbilenko.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
//    @DeleteMapping(value = "/user/{id}")
//    public ResponseEntity<?> deleteUser(@PathVariable int id) {
//        userService.deleteById(id);
//        return ResponseEntity.ok().build();
//    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
