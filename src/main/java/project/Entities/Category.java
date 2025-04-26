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
@Table(name = "Category", schema = "public")
public class Category implements Serializable {

    @Serial
    public static final long serialVersionUID = -139812389168912389L;

    @Id
    @SequenceGenerator(
            name = "SEQ-CATEGORY",
            sequenceName = "public.seq_category",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ-CATEGORY")
    private Long id;

    @Column(name = "category", nullable = false, length = 50)
    private String category;
}
