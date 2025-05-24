package project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.DTOS.Team.TeamDTO;
import project.Entities.Team;
import project.Services.TeamService;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    TeamService teamService;

    @PostMapping("/{team}")
    public @ResponseBody ResponseEntity<TeamDTO> create(@PathVariable String team) {
        Team createdTeam = teamService.create(team);

        return ResponseEntity.ok(TeamDTO.fromTeam(createdTeam));
    }

    @GetMapping("/{teamId}")
    public @ResponseBody ResponseEntity<TeamDTO> findById(@PathVariable Long teamId) {
        Team team = teamService.findById(teamId);

        if (team == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(TeamDTO.fromTeam(team));
    }

    @GetMapping("")
    public @ResponseBody ResponseEntity<List<TeamDTO>> findAll() {
        List<Team> teams = teamService.findAll();

        return ResponseEntity.ok(TeamDTO.fromTeams(teams));
    }
}
