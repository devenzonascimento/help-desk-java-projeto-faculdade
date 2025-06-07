package project.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.DTOS.Attendance.*;
import project.Entities.Attendance;
import project.Services.AttendanceService;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    AttendanceService attendanceService;

    @PostMapping("/start")
    public @ResponseBody ResponseEntity<Attendance> startAttendance(@RequestBody @Valid StartAttendanceRequest request) {
        Attendance attendance = attendanceService.startAttendance(request);

        return ResponseEntity.ok(attendance);
    }

    @PostMapping("/transfer-to-another-team")
    public @ResponseBody ResponseEntity<Attendance> transferToAnotherTeam(@RequestBody @Valid TransferToAnotherTeamRequest request) {
        attendanceService.transferToAnotherTeam(request);

        return ResponseEntity.ok().build();
    }
}
