package project.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.DTOS.Generic.CommandResponse;
import project.DTOS.User.ChangePasswordRequest;
import project.DTOS.User.CreateUserRequest;
import project.DTOS.User.LoginRequest;
import project.DTOS.User.UserDTO;
import project.Entities.User;
import project.Services.AuthenticationService;
import project.Services.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("")
    public ResponseEntity<UserDTO> create(@RequestBody @Valid CreateUserRequest request) {
        User createdUser = userService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(UserDTO.fromUser(createdUser));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long userId) {
        User user = userService.findById(userId);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(UserDTO.fromUser(user));
    }

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = userService.findAll();

        return ResponseEntity.ok(UserDTO.fromUsers(users));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<CommandResponse> delete(@PathVariable Long userId) {
        userService.delete(userId);

        return ResponseEntity.ok(new CommandResponse(true, userId));
    }

    @PutMapping("/change-password")
    public ResponseEntity<CommandResponse> changePassword(@RequestBody ChangePasswordRequest request) {
        User user = userService.changePassword(request);

        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(new CommandResponse(true, user.getId()));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody @Valid LoginRequest request) {
        User user = authenticationService.login(request.email(), request.password());

        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(UserDTO.fromUser(user));
    }
}
