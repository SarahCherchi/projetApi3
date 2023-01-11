package be.condorcet.projetapi3.webservices;


import be.condorcet.projetapi3.entities.Cours;
import be.condorcet.projetapi3.entities.Salle;
import be.condorcet.projetapi3.services.InterfCoursService;
import be.condorcet.projetapi3.services.InterfSalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/cours")
public class RestCours {
    @Autowired
    private InterfCoursService coursServiceImpl;
    @Autowired
    private InterfSalleService salleServiceImpl;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cours> getCours(@PathVariable(value = "id") int id)  throws Exception{
        System.out.println("recherche du cours n° " + id);
        Cours cr = coursServiceImpl.read(id);
        return new ResponseEntity<>(cr, HttpStatus.OK);
    }

    @RequestMapping(value = "/intitule={intitule}", method = RequestMethod.GET)
    public ResponseEntity<List<Cours>> getIntitule(@PathVariable(value = "intitule") String intitule) throws Exception {
        System.out.println("recherche de " + intitule);
        List<Cours> cours = coursServiceImpl.readIntitu(intitule);
        return new ResponseEntity<>(cours, HttpStatus.OK);
    }

    @RequestMapping(value = "/idsalle={id}", method = RequestMethod.GET)
    public ResponseEntity<List<Cours>> getCoursSalle(@PathVariable(value = "id") int id)  throws Exception{
        System.out.println("recherche des cours dans la salle d'id " + id);
        Salle sl = salleServiceImpl.read(id);
        List<Cours> lcr = coursServiceImpl.getCours(sl);
        return new ResponseEntity<>(lcr, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Cours> createCours(@RequestBody Cours cr) throws Exception {
        System.out.println("Création de la cours de la salle" + cr.getSalle());
        coursServiceImpl.create(cr);
        return new ResponseEntity<>(cr, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Cours> majSalle(@PathVariable(value = "id") int id,@RequestBody Cours nouvcr) throws Exception{
        System.out.println("maj du cours n° " + id);
        nouvcr.setIdcours(id);
        Cours cract = coursServiceImpl.update(nouvcr);
        return new ResponseEntity<>(cract, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCours(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("effacement du cours n°" + id);
        Cours cr = coursServiceImpl.read(id);
        coursServiceImpl.delete(cr);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value =  "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Cours>> listSalle() throws Exception{
        System.out.println("recherche de tous les cours");
        return new ResponseEntity<>(coursServiceImpl.all(), HttpStatus.OK);
    }
/*
    @RequestMapping(value =  "/allp",method = RequestMethod.GET)
    public ResponseEntity<Page<Cours>> listCours(Pageable pageable) throws Exception{
        System.out.println("recherche de toutes les cours");
        return new ResponseEntity<>(coursServiceImpl.allp(pageable), HttpStatus.OK);
    }
*/
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }
}
