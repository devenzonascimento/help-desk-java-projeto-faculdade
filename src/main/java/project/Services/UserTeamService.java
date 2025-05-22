package project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.Entities.Team;
import project.Entities.User;
import project.Entities.UserTeam;
import project.Repositories.TeamRepository;
import project.Repositories.UserRepository;
import project.Repositories.UserTeamRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserTeamService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserTeamRepository userTeamRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByUserId(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Transactional
    public User saveTeams(Long userId, List<Long> teamIds) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        List<UserTeam> currentUserTeamList = userTeamRepository.findByUserId(userId);

        // Times que já estão vinculados
        Set<Long> currentTeamIds = currentUserTeamList.stream()
                .map(userTeam -> userTeam.getTeam().getId())
                .collect(Collectors.toSet());

        // Novos vínculos
        for (Long teamId : teamIds) {
            if (!currentTeamIds.contains(teamId)) {
                Team team = teamRepository.findById(teamId)
                        .orElseThrow(() -> new RuntimeException("Team not found"));

                UserTeam newUserTeam = new UserTeam();
                newUserTeam.setUser(user);
                newUserTeam.setTeam(team);
                userTeamRepository.save(newUserTeam);
            }
        }

        // Remover vínculos que não estão mais presentes
        for (UserTeam userTeam : currentUserTeamList) {
            if (!teamIds.contains(userTeam.getTeam().getId())) {
                userTeamRepository.delete(userTeam);
            }
        }

        return userRepository.findById(userId).orElse(null);
    }

}
