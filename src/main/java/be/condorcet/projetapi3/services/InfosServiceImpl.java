package be.condorcet.projetapi3.services;

import java.util.List;

import be.condorcet.projetapi3.entities.*;
import be.condorcet.projetapi3.repositories.ClasseRepository;
import be.condorcet.projetapi3.repositories.InfosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
@Transactional(rollbackOn = Exception.class)
public class InfosServiceImpl implements InterfInfosService{
    @Autowired
    private InfosRepository infosRepository;
    @Autowired
    private ClasseRepository classeRepository;
    @Override
    public Infos create(Infos infos) throws Exception {
        infosRepository.save(infos);
        return infos;
    }
    @Override
    public Infos read(Integer id) throws Exception {
        return infosRepository.findById(id).get();
    }
    @Override
    public Infos update(Infos infos) throws Exception {
        read(infos.getClasse().getIdclasse());
        infosRepository.save(infos);
        return infos;
    }
    @Override
    public void delete(Infos infos) throws Exception {
        infosRepository.deleteById(infos.getClasse().getIdclasse());
    }
    @Override
    public List<Infos> all() throws Exception {
        return infosRepository.findAll();
    }
    @Override
    public Infos read(Classe cl, Cours cr) {
        return infosRepository.findInfosByClasseAndCours(cl,cr);
    }

    @Override
    public List<Infos> getInfos(Enseignant ens) {
        List<Infos> linf = infosRepository.findInfosByEnseignant(ens);
        return linf;
    }

}
