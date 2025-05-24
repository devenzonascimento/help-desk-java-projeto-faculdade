package project.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import project.DTOS.Ticket.OpenTicketRequest;
import project.DTOS.Ticket.TicketDTO;
import project.Entities.Ticket;
import project.Services.TicketService;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/open")
    public @ResponseBody ResponseEntity<TicketDTO> openTicket(@RequestBody @Valid OpenTicketRequest request) {
        Ticket openedTicket = ticketService.openTicket(request);

        return ResponseEntity.ok(TicketDTO.fromTicket(openedTicket));
    }

    @GetMapping("/open-not-completed")
    public @ResponseBody ResponseEntity<List<TicketDTO>> findAllOpenAndNotCompletedTickets() {
        List<Ticket> openTickets = ticketService.findAllOpenAndNotCompletedTickets();

        return ResponseEntity.ok(TicketDTO.fromTickets(openTickets));
    }

    @GetMapping("/open-by-team/{teamId}")
    public @ResponseBody ResponseEntity<List<TicketDTO>> findAllOpenTicketsByTeam(@PathVariable Long teamId) {
        List<Ticket> openTickets = ticketService.findAllOpenTicketsByTeam(teamId);

        return ResponseEntity.ok(TicketDTO.fromTickets(openTickets));
    }
}
