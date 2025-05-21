package project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.Entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>  {

}
