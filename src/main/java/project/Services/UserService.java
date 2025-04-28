package project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.DTOS.User.ChangePasswordRequest;
import project.Entities.User;
import project.Repositories.UserRepository;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User changePassword(ChangePasswordRequest request) {
        User user = userRepository.findByEmail(request.email()).orElse(null);

        if (user == null) {
            return null; // TODO: Usuario não encontrado
        }

        if (!Objects.equals(user.getPassword(), request.currentPassword())) {
            return null; // TODO: Senha diferente
        }

        user.setPassword(request.newPassword());

        User updatedUser = userRepository.save(user);

        return updatedUser;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
