package project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.Entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
//    @Query(value = "SELECT User FROM User WHERE User.name = :name", nativeQuery = false)
//    Optional<User> findByName(String name);
//
//    @Query(value = "SELECT User FROM User WHERE User.email = :email", nativeQuery = false)
//    Optional<User> findByEmail(String email);
}
