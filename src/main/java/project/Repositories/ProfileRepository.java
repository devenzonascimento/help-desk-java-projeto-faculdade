package project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.Entities.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
