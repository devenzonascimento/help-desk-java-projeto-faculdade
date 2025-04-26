package project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.DTOS.UserDTO;
import project.Entities.User;
import project.Services.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        User userToCreate = UserDTO.toUser(userDTO);

        User createdUser = userService.create(userToCreate);

        return ResponseEntity.ok(UserDTO.fromUser(createdUser));
    }

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getAll() {
        List<User> users = userService.getAll();

        return ResponseEntity.ok(UserDTO.fromUsers(users));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<UserDTO>> delete(@PathVariable Long id) {
        userService.delete(id);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/change-password")
    public ResponseEntity<UserDTO> create(@RequestBody ChangePasswordRequest request) {
        // TODO: Implementar
    }
}
