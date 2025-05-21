package project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.Entities.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

}
