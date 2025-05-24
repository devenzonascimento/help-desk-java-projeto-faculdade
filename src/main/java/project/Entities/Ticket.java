package project.Entities;

import jakarta.persistence.*;
import lombok.*;
import project.Enums.AttendanceStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ticket", schema = "public")
public class Ticket implements Serializable {

    @Serial
    private static final long serialVersionUID = 139812389168912389L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id", referencedColumnName = "id", nullable = false)
    private User requester;

    @Enumerated(EnumType.STRING)
    @Column(name = "last_status", nullable = false, length = 15)
    private AttendanceStatus lastStatus;

    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endDate;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT", length = 1000)
    private String description;

    @Column(name = "internal_report", columnDefinition = "TEXT", length = 1000)
    private String internalReport;

    @OneToMany(cascade =  CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ticket_id")
    private List<Attendance> attendances;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = false)
    private Team team;

    // @Column(name = "file_id")
    // private Long fileId;
}
