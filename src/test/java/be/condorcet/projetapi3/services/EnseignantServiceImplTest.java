package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Enseignant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EnseignantServiceImplTest {
    @Autowired
    private EnseignantServiceImpl enseignantServiceImpl;

    Enseignant ens;

    @BeforeEach
    void setUp() {
        try{
            ens = new Enseignant(null,"MatriculeTest","NomTest","PrenomTest","0476",200,2000, Date.valueOf(LocalDate.now()));
            enseignantServiceImpl.create(ens);
            System.out.println("création de l'enseignant : "+ens);
        }
        catch (Exception e){
            System.out.println("erreur de création de l'enseignant : "+ens +" erreur : "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
            enseignantServiceImpl.delete(ens);
            System.out.println("effacement de l'enseignant : "+ens);
        }
        catch (Exception e){
            System.out.println("erreur d'effacement de l'enseignant :"+ens+" erreur : "+e);
        }
    }

    @Test
    void read() {
        try{
            int numens= ens.getIdenseignant();
            Enseignant ens2= enseignantServiceImpl.read(numens);
            assertEquals("MatriculeTest",ens2.getMatricule(),"matricules différents "+"MatriculeTest"+"-"+ens2.getMatricule());
            assertEquals("NomTest",ens2.getNom(),"noms différents "+"NomTest"+"-"+ens2.getNom());
            assertEquals("PrenomTest",ens2.getPrenom(),"prénoms différents"+"PrenomTest"+"-"+ens2.getPrenom());
            assertEquals("0476",ens2.getTel(),"téléphones différents"+"0476"+"-"+ens2.getTel());
        }
        catch (Exception e){
            fail("recherche infructueuse "+e);
        }
    }

    @Test
    void create() {
        assertNotEquals(0,ens.getIdenseignant(),"id enseignant non incrémenté");
        assertEquals("MatriculeTest",ens.getMatricule(),"matricule de l'enseignant non enregistré : "+ens.getMatricule()+ " au lieu de MatriculeTest");
        assertEquals("NomTest",ens.getNom(),"nom enseignant non enregistré : "+ens.getNom()+ " au lieu de NomTest");
        assertEquals("PrenomTest",ens.getPrenom(),"prénom enseignant non enregistré :"+ens.getPrenom()+" au lieu de PrenomTest");
        assertEquals("0476",ens.getTel(),"téléphone non enregistré : "+ens.getTel()+ " au lieu de 0476");
    }

    @Test
    void creationDoublon(){
        Enseignant ens2 = new Enseignant(null,"MatriculeTest","NomTest","PrenomTest","0476",200,2000, Date.valueOf(LocalDate.now()));
        Assertions.assertThrows(Exception.class, () -> {enseignantServiceImpl.create(ens2);},"création d'un doublon");
    }

    @Test
    void update() {
        try{
            ens.setMatricule("MatriculeTest2");
            ens.setNom("NomTest2");
            ens.setPrenom("PrenomTest2");
            ens.setTel("0478");
            ens = enseignantServiceImpl.update(ens);
            assertEquals("MatriculeTest2",ens.getMatricule(),"matricules différents "+"MatriculeTest2-"+ens.getMatricule());
            assertEquals("NomTest2",ens.getNom(),"noms différents "+"NomTest2-"+ens.getNom());
            assertEquals("PrenomTest2",ens.getPrenom(),"prénoms différents"+"PrenomTest2-"+ens.getPrenom());
            assertEquals("0478",ens.getTel(),"teléphones différents "+"0478-"+ens.getTel());

        }
        catch(Exception e){
            fail("erreur de mise à jour "+e);
        }
    }

    @Test
    void delete() {
        try{
            enseignantServiceImpl.delete(ens);
            Assertions.assertThrows(Exception.class, () -> {enseignantServiceImpl.read(ens.getIdenseignant());},"record non effacé");
        }
        catch(Exception e){
            fail("erreur d'effacement "+e);
        }
    }

    @Test
    void all() {
        try {
            List<Enseignant> lens = enseignantServiceImpl.all();
            assertNotEquals(0,lens.size(),"la liste ne contient aucun élément");
        }catch (Exception e){
            fail("erreur de recherche de tous les enseignants "+e);
        }
    }
}