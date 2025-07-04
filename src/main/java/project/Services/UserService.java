package project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.DTOS.User.ChangePasswordRequest;
import project.DTOS.User.CreateUserRequest;
import project.Entities.Profile;
import project.Entities.User;
import project.Repositories.ProfileRepository;
import project.Repositories.UserRepository;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfileRepository profileRepository;

    public User create(CreateUserRequest request) {
        User userToCreate = new User();

        userToCreate.setName(request.name());
        userToCreate.setEmail(request.email());
        userToCreate.setPassword(request.password());
        userToCreate.setPosition(request.position());
        userToCreate.setTelephone(request.telephone());

        Profile profile = profileRepository.findById(request.profileId()).orElse(null);

        if (profile != null) {
            userToCreate.setProfile(profile);
        }

        return userRepository.save(userToCreate);
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User changePassword(ChangePasswordRequest request) {
        User user = userRepository.findByEmail(request.email()).orElse(null);

        if (user == null) {
            throw new RuntimeException("Invalid credentials.");
        }

        if (!Objects.equals(user.getPassword(), request.currentPassword())) {
            throw new RuntimeException("Invalid credentials.");
        }

        user.setPassword(request.newPassword());

        User updatedUser = userRepository.save(user);

        return updatedUser;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
