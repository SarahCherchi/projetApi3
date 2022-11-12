package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Classe;


public interface InterfClasseService extends InterfService<Classe> {
    public Classe read(String sigle);

}
