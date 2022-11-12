package be.condorcet.projetapi3.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APIENSEIGNANT", schema = "ORA2", catalog = "ORCL")
public class Enseignant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enseignant_generator")
    @SequenceGenerator(name = "enseignant_generator", sequenceName = "APIENSEIGNANT_SEQ", allocationSize = 1)
    private Integer idenseignant;
    @NonNull
    private String matricule;
    @NonNull
    private String nom;
    @NonNull
    private String prenom;
    @NonNull
    private String tel;
    private Integer chargesem;
    private Integer salairemensu;
    private Date dateengag;
}