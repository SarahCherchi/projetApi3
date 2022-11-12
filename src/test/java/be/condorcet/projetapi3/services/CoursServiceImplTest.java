package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Cours;

import be.condorcet.projetapi3.entities.Salle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;

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

        }
        catch (Exception e){
            fail("recherche infructueuse "+e);
        }
    }

    @Test
    void create() {
        assertNotEquals(0,cr.getIdcours(),"numéro du cours non incrémenté");
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void all() {
    }
}