package be.condorcet.projetapi3.repositories;

import be.condorcet.projetapi3.entities.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface EnseignantRepository extends JpaRepository<Enseignant,Integer> {
    public List<Enseignant> findEnseignantByDateengag(Date dateengag);
    public Enseignant findEnseignantByNomAndPrenomAndTel(String nom,String prenom,String tel);
}
