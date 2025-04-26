package project.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Team", schema = "public")
public class Team implements Serializable {

    @Serial
    public static final long serialVersionUID = -139812389168912389L;

    @Id
    @SequenceGenerator(
            name = "SEQ-TEAM",
            sequenceName = "public.seq_team",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ-TEAM")
    private Long id;

    @Column(name = "team", nullable = false, length = 50)
    private String team;
}
