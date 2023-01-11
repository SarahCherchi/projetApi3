package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Cours;
import be.condorcet.projetapi3.entities.Salle;

import java.util.List;

public interface InterfSalleService extends InterfService<Salle> {
   //public Salle read(String sigle);
    public List<Salle> read(String sigle);

    List<Salle> readCap(Integer capacite);

    List<Salle> readIntuti(String intitule);


}

