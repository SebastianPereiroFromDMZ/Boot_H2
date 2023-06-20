package ru.netology.springboot_h2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.springboot_h2.exception.InvalidCredentials;
import ru.netology.springboot_h2.exception.UnauthorizedUser;
import ru.netology.springboot_h2.model.Authorities;
import ru.netology.springboot_h2.service.AuthorizationService;

import java.util.List;

@RestController
public class AuthorizationController {
    AuthorizationService service;

    public AuthorizationController(AuthorizationService service){
        this.service = service;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestParam("user") String user, @RequestParam("password") String password) {
        return service.getAuthorities(user, password);
    }

    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<String> iaeHandler(InvalidCredentials e) {
        return new ResponseEntity<>("IllegalArgumentException in throwExceptionIne method", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity<String> reHandler(UnauthorizedUser e) {
        return new ResponseEntity<>("RuntimeException in throwExceptionIne method", HttpStatus.I_AM_A_TEAPOT);
    }
}