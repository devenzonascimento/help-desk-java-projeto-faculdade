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
            return null; // TODO: Usuario não encontrado
        }

        if (!Objects.equals(user.getPassword(), password)) {
            return null; // TODO: Senha diferente
        }

        return user;
    }
}
