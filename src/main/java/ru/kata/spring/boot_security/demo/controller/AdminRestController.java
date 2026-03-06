package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;

@RestController                         // @Controller + @ResponseBody на каждом методе
@RequestMapping("/api/admin")
public class AdminRestController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    // GET /api/admin/users — список всех пользователей
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);                        // 200 OK + тело
    }

    // GET /api/admin/users/{id} — один пользователь
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();           // 404
        }
        return ResponseEntity.ok(user);                        // 200 OK
    }

    // POST /api/admin/users — создать пользователя
    // Тело запроса в POSTMAN: Body → raw → JSON
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user); // 201 Created
    }

    // PUT /api/admin/users — обновить пользователя (передаём id внутри тела)
    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok(user);                        // 200 OK
    }

    // DELETE /api/admin/users/{id} — удалить пользователя
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();           // 404
        }
        userService.delete(user);
        return ResponseEntity.noContent().build();             // 204 No Content
    }
}
