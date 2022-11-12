package be.condorcet.projetapi3.repositories;

import be.condorcet.projetapi3.entities.Classe;
import be.condorcet.projetapi3.entities.Cours;
import be.condorcet.projetapi3.entities.Enseignant;
import be.condorcet.projetapi3.entities.Infos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfosRepository extends JpaRepository<Infos,Integer> {
    public Infos findInfosByClasseanAndAndCours(Classe cl, Cours cr);
    public List <Infos> findInfosByEnseignant(Enseignant ens);

}
