package project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.Entities.User;
import project.Repositories.UserRepository;

import java.util.Objects;

@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            throw new RuntimeException("Invalid credentials.");
        }

        if (!Objects.equals(user.getPassword(), password)) {
            throw new RuntimeException("Invalid credentials.");
        }

        return user;
    }
}
