package project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.Entities.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long>  {
    List<Ticket> findByTeamIdAndEndDateIsNull(Long teamId);

    List<Ticket> findByEndDateIsNull();
}
