package project.DTOS;

import project.Entities.Team;

import java.io.Serial;
import java.io.Serializable;

public record TeamDTO(Long id, String team) implements Serializable {

    @Serial
    public static final long serialVersionUID = -4297989842874219L;

    public static TeamDTO valueOf(Team team) {
        if (team != null) {
            return new TeamDTO(
                    team.getId(),
                    team.getTeam()
            );
        }
        return null;
    }

    public static Team toTeam(TeamDTO dto) {
        if (dto != null) {
            return new Team(dto.id, dto.team);
        }
        return null;
    }
}
