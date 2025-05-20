package project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.Entities.Team;
import project.Repositories.TeamRepository;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    public Team create(String name) {
        Team newTeam = new Team();
        newTeam.setName(name);

        return teamRepository.save(newTeam);
    }
}
