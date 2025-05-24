package project.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.DTOS.User.SaveTeamsRequest;
import project.DTOS.UserTeam.UserTeamDTO;
import project.Entities.User;
import project.Services.UserTeamService;

import java.util.List;

@RestController
@RequestMapping("/user-team")
public class UserTeamController {

    @Autowired
    UserTeamService userTeamService;

    @PostMapping("/save-teams")
    public ResponseEntity<UserTeamDTO> saveTeams(@RequestBody @Valid SaveTeamsRequest request) {
        User user = userTeamService.saveTeams(request.userId(), request.teamsIds());

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(UserTeamDTO.fromUser(user));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserTeamDTO> findByUserId(@PathVariable Long userId) {
        User user = userTeamService.findByUserId(userId);

        return ResponseEntity.ok(UserTeamDTO.fromUser(user));
    }

    @GetMapping("")
    public ResponseEntity<List<UserTeamDTO>> findAll() {
        List<User> users = userTeamService.findAll();

        return ResponseEntity.ok(UserTeamDTO.fromUsers(users));
    }
}
