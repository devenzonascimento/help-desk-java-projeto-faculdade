package project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.Entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
