package be.condorcet.projetapi3.entities;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APIINFOS", schema = "ORA2", catalog = "ORCL")
public class Infos {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="infos_generator")
    @SequenceGenerator(name = "infos_generator", sequenceName = "APIINFOS_SEQ", allocationSize = 1)
    private Integer nbrHeures;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "IDCLASSE")
    private Classe classe;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "IDCOURS")
    private Cours cours;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "IDSALLE")
    private Salle salle;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "IDENSEIGNANT")
    private Enseignant enseignant;

}
