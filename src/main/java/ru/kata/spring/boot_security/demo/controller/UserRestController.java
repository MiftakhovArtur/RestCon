package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.models.PersonDetails;
import ru.kata.spring.boot_security.demo.models.User;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    // GET /api/user/me — текущий авторизованный пользователь
    @GetMapping("/showAccount")
    public ResponseEntity<User> getCurrentUser(
            @AuthenticationPrincipal PersonDetails personDetails) {
        if (personDetails == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(personDetails.getPersson());
    }
}
