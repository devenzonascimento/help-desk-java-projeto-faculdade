package project.DTOS.User;

import project.DTOS.Profile.ProfileDTO;
import project.Entities.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public record UserDTO(
        Long id,
        String name,
        String email,
        String position,
        String telephone,
        ProfileDTO profile
) implements Serializable {

    @Serial
    private static final long serialVersionUID = -4297989842874219L;

    public static UserDTO fromUser(User user) {
        if (user == null) {
            return null;
        }

        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPosition(),
                user.getTelephone(),
                ProfileDTO.fromProfile(user.getProfile())
        );
    }

    public static User toUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        User user = new User();

        user.setId(userDTO.id);
        user.setName(userDTO.name);
        user.setEmail(userDTO.email);
        user.setPosition(userDTO.position);
        user.setTelephone(userDTO.telephone);
        user.setProfile(ProfileDTO.toProfile(userDTO.profile()));

        return user;
    }

    public static List<UserDTO> fromUsers(List<User> users) {
        List<UserDTO> userDTO = new ArrayList<>();

        if (users != null && !users.isEmpty()) {
            userDTO.addAll(users.stream().map(UserDTO::fromUser).toList());
        }

        return userDTO;
    }

    public static List<User> toUsers(List<UserDTO> usersDTO) {
        List<User> users = new ArrayList<>();

        if (usersDTO != null && !usersDTO.isEmpty()) {
            users.addAll(usersDTO.stream().map(UserDTO::toUser).toList());
        }

        return users;
    }
}
