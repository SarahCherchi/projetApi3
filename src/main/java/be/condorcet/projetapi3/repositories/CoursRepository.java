package be.condorcet.projetapi3.repositories;

import be.condorcet.projetapi3.entities.Cours;
import be.condorcet.projetapi3.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursRepository extends JpaRepository<Cours,Integer> {
    public Cours findCoursByCode(String code);
    public List<Cours> findCoursBySalle(Salle salle);

}
