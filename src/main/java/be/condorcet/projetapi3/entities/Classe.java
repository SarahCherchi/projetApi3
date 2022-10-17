package be.condorcet.projetapi3.entities;
import lombok.*;
import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APICLASSE", schema = "ORA2", catalog = "ORCL")
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "classe_generator")
    @SequenceGenerator(name="classe_generator", sequenceName = "APICLASSE_SEQ", allocationSize=1)
    private Integer idclasse;
    @NonNull
    private String sigle;
    @NonNull
    private Integer annee;
    @NonNull
    private String specialite;
    private Integer nbreleves;

}


