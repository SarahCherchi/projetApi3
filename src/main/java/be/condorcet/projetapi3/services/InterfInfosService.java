package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Classe;
import be.condorcet.projetapi3.entities.Infos;

import java.util.List;

public interface InterfInfosService extends InterfService<Infos> {
    public List<Infos> getInfos(Classe cl);
}
