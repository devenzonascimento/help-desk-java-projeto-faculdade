package project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.Entities.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
