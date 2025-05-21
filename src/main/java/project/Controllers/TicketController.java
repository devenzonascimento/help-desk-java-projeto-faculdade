package project.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import project.DTOS.Ticket.OpenTicketRequest;
import project.DTOS.Ticket.TicketDTO;
import project.Entities.Ticket;
import project.Services.TicketService;

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
}
