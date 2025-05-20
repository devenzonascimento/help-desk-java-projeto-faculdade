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
@Table(name = "UserTeam", schema = "public")
public class UserTeam implements Serializable {

    @Serial
    private static final long serialVersionUID = -139812389168912389L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "teamId", referencedColumnName = "id")
    private Team team;
}

