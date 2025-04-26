package project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.Entities.Profile;
import project.Repositories.ProfileRepository;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    public Profile create(String profile) {
        Profile newProfile = new Profile();
        newProfile.setProfile(profile);

        return profileRepository.save(newProfile);
    }
}
