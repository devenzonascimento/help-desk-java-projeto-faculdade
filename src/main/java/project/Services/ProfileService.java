package project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.Entities.Profile;
import project.Repositories.ProfileRepository;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    public Profile create(String profileName) {
        Profile newProfile = new Profile();

        newProfile.setName(profileName);

        return profileRepository.save(newProfile);
    }
}
