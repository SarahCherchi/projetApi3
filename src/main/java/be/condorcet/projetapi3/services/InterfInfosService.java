package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Classe;
import be.condorcet.projetapi3.entities.Cours;
import be.condorcet.projetapi3.entities.Enseignant;
import be.condorcet.projetapi3.entities.Infos;

import java.util.List;

public interface InterfInfosService extends InterfService<Infos> {

    public Infos read(Classe cl, Cours cr);
    public List<Infos> getInfos(Enseignant ens);
}
