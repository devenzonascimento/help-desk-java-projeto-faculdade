package project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.Entities.Team;
import project.Repositories.TeamRepository;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    public Team create(String teamName) {
        Team newTeam = new Team();

        newTeam.setName(teamName);

        return teamRepository.save(newTeam);
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }
}
