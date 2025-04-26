package project.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "User", schema = "public")
public class User implements Serializable {

    @Serial
    public static final long serialVersionUID = -139812389168912389L;

    @Id
    @SequenceGenerator(
            name = "SEQ-USER",
            sequenceName = "public.seq_user",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ-USER")
    private Long id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "position", nullable = true, length = 120)
    private String position;

    @Column(name = "telephone", nullable = true, length = 30)
    private String telephone;

    @ManyToOne
    @JoinColumn(name = "profileId", referencedColumnName = "id", nullable = false, unique = false, insertable = true, updatable = true)
    private Profile profile;

    @OneToMany
    @JoinTable(name = "userTeam", schema = "public", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "teamId"))
    @JsonManagedReference
    private Collection<Team> teams;
}
