package project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.Entities.UserTeam;

import java.util.List;

public interface UserTeamRepository extends JpaRepository<UserTeam, Long> {
    List<UserTeam> findByUserId(Long userId);
}

