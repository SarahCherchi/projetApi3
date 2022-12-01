package be.condorcet.projetapi3.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.List;


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

    @JsonIgnore
    @OneToMany(mappedBy = "classe" , fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Infos> infos;

}


