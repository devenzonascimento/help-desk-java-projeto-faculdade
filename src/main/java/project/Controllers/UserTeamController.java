package project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.DTOS.User.SaveTeamsRequest;
import project.DTOS.UserTeam.UserTeamDTO;
import project.Entities.User;
import project.Services.UserTeamService;

@RestController
@RequestMapping("/user-team")
public class UserTeamController {

    @Autowired
    UserTeamService userTeamService;

    @PostMapping("/save-teams")
    public ResponseEntity<UserTeamDTO> saveTeams(@RequestBody SaveTeamsRequest request) {
        User user = userTeamService.saveTeams(request.userId(), request.teamsIds());

        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(UserTeamDTO.fromUser(user));
    }
}
