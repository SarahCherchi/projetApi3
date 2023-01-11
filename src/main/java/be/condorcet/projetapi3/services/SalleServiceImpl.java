package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Cours;
import be.condorcet.projetapi3.entities.Salle;
import be.condorcet.projetapi3.repositories.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//@Service
@Transactional(rollbackOn = Exception.class)
public class SalleServiceImpl implements InterfSalleService{
    @Autowired
    private SalleRepository salleRepository;

    @Override
    public List<Salle> read(String sigle) {
        return salleRepository.findSallesBySigleLike(sigle+"%");
    }

    @Override
    public List<Salle> readCap(Integer capacite) {
       return salleRepository.findSalleByCapacite(capacite);
    }

    @Override
    public List<Salle> readIntuti(String intitule) {
        return salleRepository.findSalleByCoursLike(intitule+"%");
    }

  /*  @Override
    public Salle read(String sigle) {
        return salleRepository.findSalleBySigle(sigle);
    }*/

    @Override
    public Salle create(Salle salle) throws Exception {
        salleRepository.save(salle);
        return salle;
    }

    @Override
    public Salle read(Integer id) throws Exception {
        Optional<Salle> osl = salleRepository.findById(id);
        return osl.get();
    }

    @Override
    public Salle update(Salle salle) throws Exception {
        read(salle.getIdsalle());
        salleRepository.save(salle);
        return salle;
    }

    @Override
    public void delete(Salle salle) throws Exception {
        salleRepository.deleteById(salle.getIdsalle());
    }

    @Override
    public List<Salle> all() throws Exception {
        return salleRepository.findAll();
    }
}
