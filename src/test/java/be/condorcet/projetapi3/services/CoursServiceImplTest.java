package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Cours;

import be.condorcet.projetapi3.entities.Salle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoursServiceImplTest {
    @Autowired
    private CoursServiceImpl coursServiceImpl;
    @Autowired
    private SalleServiceImpl salleServiceImpl;
    private Cours cr;
    private Salle sl;

    @BeforeEach
    void setUp() {
        try{
            sl = new Salle(null,"SalleTest",30,new ArrayList<>());
            salleServiceImpl.create(sl);
            System.out.println("création de la salle : "+sl);
            cr = new Cours(null,"CodeTest","IntituleTest",sl);
            coursServiceImpl.create(cr);
            System.out.println("création du cours : "+cr);
        }
        catch (Exception e){
            System.out.println("erreur de création du cours "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try{
            coursServiceImpl.delete(cr);
        }
        catch(Exception e){
            System.out.println("erreur d'effacement du cours "+e);
        }
        try{
            salleServiceImpl.delete(sl);
        }
        catch(Exception e){
            System.out.println("erreur d'effacement de la salle "+e);
        }
    }

    @Test
    void read() {
        try{
            int numcr= cr.getIdcours();
            Cours cr2=coursServiceImpl.read(numcr);
            assertEquals("CodeTest",cr2.getCode(),"codes différents "+"CodeTest"+"-"+cr2.getCode());
        }
        catch (Exception e){
            fail("recherche infructueuse "+e);
        }
    }

    @Test
    void create() {
        assertNotEquals(0,cr.getIdcours(),"numéro du cours non incrémenté");
        assertEquals("CodeTest",cr.getCode(),"code du cours non enregistré : "+cr.getCode()+ " au lieu de CodeTest");
    }

    @Test
    void update() {
        try{
            cr.setCode("CodeTest2");
            cr.setIntitule("IntituleTest2");

            cr = coursServiceImpl.update(cr);
            assertEquals("CodeTest2",cr.getCode(),"codes différents "+"CodeTest2-"+cr.getCode());
            assertEquals("IntituleTest2",cr.getIntitule(),"intitulés différents"+"IntituleTest2-"+cr.getIntitule());
        }
        catch(Exception e){
            fail("erreur de mise à jour "+e);
        }

    }

    @Test
    void delete() {
        try{
            coursServiceImpl.delete(cr);
            Assertions.assertThrows(Exception.class, () -> {coursServiceImpl.read(cr.getIdcours());},"record non effacé");
        }
        catch(Exception e){
            fail("erreur d'effacement "+e);
        }
    }

    @Test
    void all() {
        try {
            List<Cours> lcr = coursServiceImpl.all();
            assertNotEquals(0,lcr.size(),"la liste ne contient aucun élément");
        }catch (Exception e){
            fail("erreur de recherche de tous les cours "+e);
        }

    }
}