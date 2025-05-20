package project.DTOS.UserTeam;

import project.DTOS.ProfileDTO;
import project.DTOS.TeamDTO;
import project.Entities.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public record UserTeamDTO(
        Long id,
        String name,
        String email,
        ProfileDTO profile,
        List<TeamDTO> teams
) implements Serializable {

    @Serial
    private static final long serialVersionUID = -4297989842874219L;

    public static UserTeamDTO fromUser(User user) {
        if (user == null) {
            return null;
        }

        return new UserTeamDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                ProfileDTO.valueOf(user.getProfile()),
                TeamDTO.fromTeams(user.getTeams().stream().toList())
        );
    }

    public static User toUser(UserTeamDTO userTeamDTO) {
        if (userTeamDTO == null) {
            return null;
        }

        User user = new User();

        user.setId(userTeamDTO.id);
        user.setName(userTeamDTO.name);
        user.setEmail(userTeamDTO.email);
        user.setProfile(ProfileDTO.toProfile(userTeamDTO.profile()));
        user.setTeams(TeamDTO.toTeams(userTeamDTO.teams));

        return user;
    }

    public static List<UserTeamDTO> fromUsers(List<User> users) {
        List<UserTeamDTO> usersTeamDTO = new ArrayList<>();

        if (users != null && !users.isEmpty()) {
            usersTeamDTO.addAll(users.stream().map(UserTeamDTO::fromUser).toList());
        }

        return usersTeamDTO;
    }

    public static List<User> toUsers(List<UserTeamDTO> usersTeamDTO) {
        List<User> users = new ArrayList<>();

        if (usersTeamDTO != null && !usersTeamDTO.isEmpty()) {
            users.addAll(usersTeamDTO.stream().map(UserTeamDTO::toUser).toList());
        }

        return users;
    }
}
