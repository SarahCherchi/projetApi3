package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Salle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.interfaces.DSAKey;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SalleServiceImplTest {
    @Autowired
    private SalleServiceImpl salleServiceImpl;

    Salle sl;

    @BeforeEach
    void setUp() {
        try{
            sl= new Salle(null,"SigleTest",17,new ArrayList<>());
            salleServiceImpl.create(sl);
            System.out.printf("création de la salle : "+sl);
        }
        catch (Exception e){
            System.out.println("erreur de création de la salle : "+sl +" erreur : "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
            salleServiceImpl.delete(sl);
            System.out.println("effacement de la salle : "+sl);
        }
        catch (Exception e){
            System.out.println("erreur d'effacement de la salle :"+sl+" erreur : "+e);
        }
    }

    @Test
    void read() {
        try {
            int numSl = sl.getIdsalle();
            Salle sl2 = salleServiceImpl.read(numSl);
            assertEquals("SigleTest",sl2.getSigle(),"sigle différents "+"NomTest"+"-"+sl2.getSigle());
        }
        catch (Exception e){
            fail("recherche infructueuse "+e);
        }
    }

    @Test
    void create() {
        assertNotEquals(0,sl.getIdsalle(),"id salle non incrémenté");
        assertEquals("SigleTest",sl.getSigle(),"sigle de la salle non enregistré : "+sl.getSigle()+ " au lieu de SigleTest");
    }

    @Test
    void creationDoublon() {
        Salle sl2 = new Salle(null,"SigleTest",20,new ArrayList<>());
        Assertions.assertThrows(Exception.class, () -> {salleServiceImpl.create(sl2);},"création d'un doublon");
    }

    @Test
    void update() {
        try{
            sl.setSigle("SigleTest2");
            sl.setCapacite(35);
            sl = salleServiceImpl.update(sl);
            assertEquals("SigleTest2",sl.getSigle(),"sigles différents "+"SigleTest2-"+sl.getSigle());
            assertEquals(35,sl.getCapacite(),"capacité différentes "+"35-"+sl.getCapacite());
        }
        catch (Exception e){
            fail("erreur de mise à jour "+e);
        }
    }

    @Test
    void delete() {
        try{
            salleServiceImpl.delete(sl);
            Assertions.assertThrows(Exception.class, () -> {salleServiceImpl.read(sl.getIdsalle());},"record non effacé");
        }
        catch(Exception e){
            fail("erreur d'effacement "+e);
        }
    }

    @Test
    void all() {
        try {
            List<Salle> lsl = salleServiceImpl.all();
            assertNotEquals(0,lsl.size(),"la liste ne contient aucun élément");
        }catch (Exception e){
            fail("erreur de recherche de toutes les salles "+e);
        }
    }
}