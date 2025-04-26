package project.DTOS;

import project.Entities.Profile;

import java.io.Serial;
import java.io.Serializable;

public record ProfileDTO(Long id, String profile) implements Serializable {

    @Serial
    public static final long serialVersionUID = -4297989842874219L;

    public static ProfileDTO valueOf(Profile profile) {
        if (profile != null) {
            return new ProfileDTO(
                    profile.getId(),
                    profile.getProfile()
            );
        }
        return null;
    }

    public static Profile toProfile(ProfileDTO dto) {
        if (dto != null) {
            return new Profile(dto.id, dto.profile);
        }
        return null;
    }
}
