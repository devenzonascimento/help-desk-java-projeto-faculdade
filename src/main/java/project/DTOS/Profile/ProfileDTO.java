package project.DTOS.Profile;

import project.Entities.Profile;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public record ProfileDTO(Long id, String name) implements Serializable {

    @Serial
    public static final long serialVersionUID = -4297989842874219L;

    public static ProfileDTO fromProfile(Profile profile) {
        if (profile == null) {
            return null;
        }

        return new ProfileDTO(profile.getId(), profile.getName());
    }

    public static Profile toProfile(ProfileDTO profileDTO) {
        if (profileDTO == null) {
            return null;
        }

        return new Profile(profileDTO.id, profileDTO.name);
    }

    public static List<ProfileDTO> fromProfiles(List<Profile> profiles) {
        List<ProfileDTO> profileDTO = new ArrayList<>();

        if (profiles != null && !profiles.isEmpty()) {
            profileDTO.addAll(profiles.stream().map(ProfileDTO::fromProfile).toList());
        }

        return profileDTO;
    }

    public static List<Profile> toProfiles(List<ProfileDTO> profilesDTO) {
        List<Profile> profiles = new ArrayList<>();

        if (profilesDTO != null && !profilesDTO.isEmpty()) {
            profiles.addAll(profilesDTO.stream().map(ProfileDTO::toProfile).toList());
        }

        return profiles;
    }
}