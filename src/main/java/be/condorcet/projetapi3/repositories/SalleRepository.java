package be.condorcet.projetapi3.repositories;

import be.condorcet.projetapi3.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalleRepository extends JpaRepository<Salle,Integer> {
    public Salle findSalleBySigle(String sigle);
    public List<Salle> findSallesBySigleLike(String sigle);
    public List<Salle> findSalleByCapacite(Integer capacite);
}
