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
@Table(name = "APISALLE", schema = "ORA2", catalog = "ORCL")
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salle_generator")
    @SequenceGenerator(name = "salle_generator", sequenceName = "APISALLE_SEQ", allocationSize = 1)
    private Integer idsalle;
    @NonNull
    private String sigle;
    private Integer capacite;
    @JsonIgnore
    @OneToMany(mappedBy = "salle" , fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Cours> cours;
}
