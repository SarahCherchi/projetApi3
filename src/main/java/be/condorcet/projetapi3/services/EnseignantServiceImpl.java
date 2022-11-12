package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Enseignant;
import be.condorcet.projetapi3.repositories.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class EnseignantServiceImpl implements InterfEnseignantService{
    @Autowired
    private EnseignantRepository enseignantRepository;

    @Override
    public List<Enseignant> read(Date dateengag) {
        return enseignantRepository.findEnseignantByDateengag(dateengag);
    }

    @Override
    public Enseignant read(String nom, String prenom, String tel) {
        return enseignantRepository.findEnseignantByNomAndPrenomAndTel(nom,prenom,tel);
    }

    @Override
    public Enseignant create(Enseignant enseignant) throws Exception {
        enseignantRepository.save(enseignant);
        return enseignant;
    }

    @Override
    public Enseignant read(Integer id) throws Exception {
        Optional<Enseignant> oens= enseignantRepository.findById(id);
        return oens.get();
    }

    @Override
    public Enseignant update(Enseignant enseignant) throws Exception {
        read(enseignant.getIdenseignant());
        enseignantRepository.save(enseignant);
        return enseignant;
    }

    @Override
    public void delete(Enseignant enseignant) throws Exception {
        enseignantRepository.deleteById(enseignant.getIdenseignant());
    }

    @Override
    public List<Enseignant> all() throws Exception {
        return enseignantRepository.findAll();
    }
}
