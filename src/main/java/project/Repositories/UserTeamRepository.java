package project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.Entities.Team;
import project.Entities.UserTeam;

import java.util.List;

public interface UserTeamRepository extends JpaRepository<UserTeam, Long> {
    List<UserTeam> findByUserId(Long userId);

    @Query("SELECT ut.team FROM UserTeam ut WHERE ut.user.id = :userId AND ut.active = true")
    List<Team> findActiveTeamsByUserId(@Param("userId") Long userId);
}

