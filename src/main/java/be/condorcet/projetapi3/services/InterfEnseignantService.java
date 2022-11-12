package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Enseignant;

import java.util.Date;
import java.util.List;

public interface InterfEnseignantService extends InterfService<Enseignant> {
    public List<Enseignant> read(Date dateengag);
    public Enseignant read(String nom,String prenom,String tel);

}
