package project.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.DTOS.Generic.CommandResponse;
import project.DTOS.User.ChangePasswordRequest;
import project.DTOS.User.CreateUserRequest;
import project.DTOS.User.LoginRequest;
import project.DTOS.UserDTO;
import project.Entities.Profile;
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
    public ResponseEntity<CommandResponse> create(@RequestBody @Valid CreateUserRequest request) {
        User userToCreate = new User();

        userToCreate.setName(request.name());
        userToCreate.setEmail(request.email());
        userToCreate.setPassword(request.password());
        userToCreate.setPosition(request.position());
        userToCreate.setTelephone(request.telephone());
        userToCreate.setProfile(new Profile(request.profileId(), ""));

        User createdUser = userService.create(userToCreate);

        return ResponseEntity.ok(new CommandResponse(true, createdUser.getId()));
    }

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getAll() {
        List<User> users = userService.getAll();

        return ResponseEntity.ok(UserDTO.fromUsers(users));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommandResponse> delete(@PathVariable Long id) {
        userService.delete(id);

        return ResponseEntity.ok(new CommandResponse(true, id));
    }

    @PutMapping("/change-password")
    public ResponseEntity<CommandResponse> changePassword(@RequestBody ChangePasswordRequest request) {
        User user = userService.changePassword(request);

        if (user != null) {
            return ResponseEntity.ok(new CommandResponse(true, user.getId()));
        }

        return ResponseEntity.ok(new CommandResponse(false, 0L));
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
