package be.condorcet.projetapi3.entities;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APICOURS", schema = "ORA2", catalog = "ORCL")
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cours_generator")
    @SequenceGenerator(name = "cours_generator", sequenceName = "APICOURS_SEQ", allocationSize = 1)
    private Integer idcours;
    @NonNull
    private String code;
    private String intitule;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "IDSALLE")
    private Salle salle;
}
