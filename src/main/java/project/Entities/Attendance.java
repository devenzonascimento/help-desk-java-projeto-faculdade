package project.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import project.Enums.AttendanceStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "attendance", schema = "public")
public class Attendance implements Serializable {

    @Serial
    private static final long serialVersionUID = 139812389168912389L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 15)
    private AttendanceStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_team_id", nullable = false)
    private UserTeam userTeam;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT", length = 1000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "ticket_id", referencedColumnName = "id", nullable = false)
    private Ticket ticket;

    // @Column(name = "file_id")
    // private Long fileId;
}
