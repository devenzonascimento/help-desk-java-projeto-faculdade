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
@Table(name = "Profile", schema = "public")
public class Profile implements Serializable {

    @Serial
    public static final long serialVersionUID = -139812389168912389L;

    @Id
    @SequenceGenerator(
            name = "SEQ-PROFILE",
            sequenceName = "public.seq_profile",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ-PROFILE")
    private Long id;

    @Column(name = "profile", nullable = false, length = 50)
    private String profile;

    public void setProfile(String profile) {
        this.profile = profile.toUpperCase();
    }
}
