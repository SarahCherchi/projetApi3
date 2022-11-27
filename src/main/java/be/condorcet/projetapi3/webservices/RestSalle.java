package be.condorcet.projetapi3.webservices;

import be.condorcet.projetapi3.entities.Salle;
import be.condorcet.projetapi3.services.InterfSalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salles")
public class RestSalle {
    @Autowired
    private InterfSalleService salleServiceImpl;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Salle> getSalle(@PathVariable(value = "id") int id) throws Exception {
        System.out.println("recherche de salle d' id " + id);
        Salle salle = salleServiceImpl.read(id);
        return new ResponseEntity<>(salle, HttpStatus.OK);
    }

    @RequestMapping(value = "/sigle={sigle}", method = RequestMethod.GET)
    public ResponseEntity<Salle> getSigle(@PathVariable(value = "sigle") String sigle) throws Exception {
        System.out.println("recherche de " + sigle);
        Salle salle = salleServiceImpl.read(sigle);
        return new ResponseEntity<>(salle, HttpStatus.OK);
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Salle>> listSalle() throws Exception{
        System.out.println("recherche de toutes les salles");
        return new ResponseEntity<>(salleServiceImpl.all(), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Salle> createSalle(@RequestBody Salle salle) throws Exception {
        System.out.println("Création de la salle " + salle.getSigle());
        salleServiceImpl.create(salle);
        return new ResponseEntity<>(salle, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Salle> majSalle(@PathVariable(value = "id") int id, @RequestBody Salle nouvsal) throws Exception{
        System.out.println("maj de la salle id = " + id);
        nouvsal.setIdsalle(id);
        Salle slact = salleServiceImpl.update(nouvsal);
        return new ResponseEntity<>(slact, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE
    )
    public ResponseEntity<Void> deleteSalle(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("effacement de la salle d'id " + id);
        Salle salle = salleServiceImpl.read(id);
        salleServiceImpl.delete(salle);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void> handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }

    /*@RequestMapping(value = "/allp",method = RequestMethod.GET)
    public ResponseEntity<Page<Salle>> listClient(Pageable pageable) throws Exception{
        System.out.println("recherche de toutes les salles");
        return new ResponseEntity<>(salleServiceImpl.allp(pageable), HttpStatus.OK);
    }*/

}


