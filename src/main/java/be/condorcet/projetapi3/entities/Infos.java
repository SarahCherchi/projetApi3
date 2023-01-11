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
    //il faut modifier car nbrheures n'est pas l'id
    private Integer nbrheures;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "IDCLASSE")
    private Classe classe;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "IDCOURSs")
    private Cours cours;

    @ManyToOne
    @JoinColumn(name = "IDSALLEs")
    private Salle salle;

    @ManyToOne
    @JoinColumn(name = "IDENSEIGNANT")
    private Enseignant enseignant;

}
