package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Cours;
import be.condorcet.projetapi3.entities.Salle;

import java.util.List;

public interface InterfCoursService extends InterfService<Cours>{
    public Cours read(String code);
    public List <Cours> getCours(Salle sl);

    List<Cours> readIntitu(String intitule);
}
