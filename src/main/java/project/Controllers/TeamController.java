package project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.DTOS.TeamDTO;
import project.Entities.Team;
import project.Services.TeamService;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    TeamService teamService;

    @PostMapping("/{team}")
    public @ResponseBody ResponseEntity<TeamDTO> create(@PathVariable String team) {
        Team createdTeam = teamService.create(team);

        return ResponseEntity.ok().body(TeamDTO.valueOf(createdTeam));
    }
}
