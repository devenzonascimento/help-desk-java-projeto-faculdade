package project.DTOS;

import project.Entities.Team;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public record TeamDTO(Long id, String name) implements Serializable {

    @Serial
    public static final long serialVersionUID = -4297989842874219L;

    public static TeamDTO fromTeam(Team team) {
        if (team == null) {
            return null;
        }

        return new TeamDTO(team.getId(), team.getName());
    }

    public static Team toTeam(TeamDTO teamDTO) {
        if (teamDTO == null) {
            return null;
        }

        return new Team(teamDTO.id, teamDTO.name);
    }

    public static List<TeamDTO> fromTeams(List<Team> teams) {
        List<TeamDTO> userDTO = new ArrayList<>();

        if (teams != null && !teams.isEmpty()) {
            userDTO.addAll(teams.stream().map(TeamDTO::fromTeam).toList());
        }

        return userDTO;
    }

    public static List<Team> toTeams(List<TeamDTO> teamsDTO) {
        List<Team> users = new ArrayList<>();

        if (teamsDTO != null && !teamsDTO.isEmpty()) {
            users.addAll(teamsDTO.stream().map(TeamDTO::toTeam).toList());
        }

        return users;
    }
}
