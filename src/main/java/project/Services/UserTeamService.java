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

@Service
public class UserTeamService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserTeamRepository userTeamRepository;

    public List<User> findAll() {
        List<User> users = userRepository.findAll();

        List<User> userWithActiveTeams = users.stream().peek(user -> {
            List<Team> teams = userTeamRepository.findActiveTeamsByUserId(user.getId());

            user.setTeams(teams);
        }).toList();

        return userWithActiveTeams;
    }

    public User findByUserId(Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return null;
        }

        List<Team> teams = userTeamRepository.findActiveTeamsByUserId(user.getId());

        user.setTeams(teams);

        return user;
    }

    @Transactional
    public User saveTeams(Long userId, List<Long> teamIds) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        List<UserTeam> currentUserTeamList = userTeamRepository.findByUserId(userId);

        List<UserTeam> userTeamsToDeactivate =  currentUserTeamList.stream()
            .filter(userTeam ->
                teamIds.stream().noneMatch(teamId -> userTeam.getId().equals(teamId) && userTeam.getActive())
            )
            .peek(userTeam -> userTeam.setActive(false)).toList();

        List<UserTeam> userTeamsToReactivate =  currentUserTeamList.stream()
            .filter(userTeam ->
                teamIds.stream().anyMatch(teamId -> userTeam.getId().equals(teamId) && !userTeam.getActive())
            )
            .peek(userTeam -> userTeam.setActive(true)).toList();

        List<UserTeam> userTeamsToCreate = teamIds.stream()
            .filter(teamId ->
                currentUserTeamList.stream().noneMatch(userTeam -> userTeam.getId().equals(teamId))
            )
            .map(teamId -> {
                Team team = teamRepository.findById(teamId)
                        .orElseThrow(() -> new RuntimeException("Team not found"));

                UserTeam newUserTeam = new UserTeam();
                newUserTeam.setUser(user);
                newUserTeam.setTeam(team);

                return newUserTeam;
            }).toList();

        userTeamsToDeactivate.forEach(userTeam -> {
            userTeamRepository.save(userTeam);
        });

        userTeamsToReactivate.forEach(userTeam -> {
            userTeamRepository.save(userTeam);
        });

        userTeamsToCreate.forEach(userTeam -> {
            userTeamRepository.save(userTeam);
        });

        return this.findByUserId(userId);
    }

}
