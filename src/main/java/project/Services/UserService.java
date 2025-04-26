package project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.Entities.User;
import project.Repositories.UserRepository;

import java.util.List;

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

//    public User changePassword(String email, String currentPassword, String newPassword) {
//        User user = userRepository.findByEmail(email).orElse(null);
//
//        if (user == null) {
//            return null; // TODO: Usuario não encontrado
//        }
//
//        if (!Objects.equals(user.getPassword(), currentPassword)) {
//            return null; // TODO: Senha diferente
//        }
//
//        user.setPassword(newPassword);
//
//        User updatedUser = userRepository.save(user);
//
//        return updatedUser;
//    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
