package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Classe;
import be.condorcet.projetapi3.entities.Cours;
import be.condorcet.projetapi3.entities.Infos;
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
class ClasseServiceImplTest {
    @Autowired
    private  ClasseServiceImpl classeServiceImpl;
    @Autowired
    private InfosServiceImpl infosServiceImpl;
    @Autowired
    private SalleServiceImpl salleServiceImpl;
    @Autowired
    private CoursServiceImpl coursServiceImpl;

    Classe cl;

    @BeforeEach
    void setUp() {
        try{
            cl = new Classe(null,"SigleTest",1,"SpecialiteTest",23,new ArrayList<>());
            classeServiceImpl.create(cl);
            System.out.println("création d'une classe : "+cl);
        }
        catch (Exception e){
            System.out.println("erreur de création de la classe : "+cl +" erreur : "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
            classeServiceImpl.delete(cl);
            System.out.println("effacement de la classe : "+cl);
        }
        catch (Exception e){
            System.out.println("erreur d'effacement de la classe :"+cl+" erreur : "+e);
        }
    }

    @Test
    void create() {
        assertNotEquals(0,cl.getIdclasse(),"id classe non incrémenté");
        assertEquals("SigleTest",cl.getSigle(),"sigle de la classe non enregistré : "+cl.getSigle()+ " au lieu de SigleTest");
        assertEquals(1,cl.getAnnee(),"Année non enregistrée :"+cl.getAnnee()+" au lieu de AnnéeTest");
        assertEquals("SpecialiteTest",cl.getSpecialite(),"Spécialité non enregistrée :"+cl.getSpecialite()+" au lieu de SpecialiteTest");
    }
    @Test()
    void creationDoublon(){
        Classe cl2 = new Classe(null,"SigleTest",1,"SpecialiteTest",23,null);
        Assertions.assertThrows(Exception.class, () -> {classeServiceImpl.create(cl2);},"création d'un doublon");
    }

    @Test
    void read() {
        try{
            int numclass=cl.getIdclasse();
            Classe cl2=classeServiceImpl.read(numclass);
            assertEquals("SigleTest",cl2.getSigle(),"sigles différents "+"SigleTest"+"-"+cl2.getSigle());
            assertEquals(1,cl2.getAnnee(),"année différentes "+"AnneeTest"+"-"+cl2.getAnnee());
            assertEquals("SpecialiteTest",cl2.getSpecialite(),"spécialité différentes "+"SpecialiteTest"+"-"+cl2.getSpecialite());
        }
        catch (Exception e){
            fail("recherche infructueuse "+e);
        }
    }

    @Test
    void update() {
        try{
            cl.setSigle("SigleTest2");
            cl.setAnnee(2);
            cl.setSpecialite("SpecialiteTest2");
            cl = classeServiceImpl.update(cl);
            assertEquals("SigleTest2",cl.getSigle(),"sigles différents "+"SigleTest2-"+cl.getSigle());
            assertEquals(2,cl.getAnnee(),"année différentes"+"AnneeTest2-"+cl.getAnnee());
            assertEquals("SpecialiteTest2",cl.getSpecialite(),"spécialités différentes "+"SpecialiteTest2-"+cl.getSpecialite());
        }
        catch(Exception e){
            fail("erreur de mise à jour "+e);
        }
    }

    @Test
    void delete() {
        try{
            classeServiceImpl.delete(cl);
            Assertions.assertThrows(Exception.class, () -> {classeServiceImpl.read(cl.getIdclasse());},"record non effacé");
        }
        catch(Exception e){
            fail("erreur d'effacement "+e);
        }
    }
    //@Test
    // ne fonctionne pas correctement, les données ne se suppriment pas des tables à la fin du test
    void delAvecInfos(){
        try{
            Salle sl= new Salle(null,"SigleTest",17,new ArrayList<>());
            salleServiceImpl.create(sl);
            Cours cr = new Cours(null,"CodeTest","IntituleTest",sl);
            coursServiceImpl.create(cr);
            Infos inf = new Infos(2,cl,cr,null,null);
            infosServiceImpl.create(inf);

            Assertions.assertThrows(Exception.class, () -> {
                salleServiceImpl.delete(sl);
            },"effacement réalisé malgré cours liées");
            coursServiceImpl.delete(cr);

            Assertions.assertThrows(Exception.class, () -> {
                classeServiceImpl.delete(cl);
            },"effacement réalisé malgré infos liées");

            infosServiceImpl.delete(inf);
        }catch (Exception e){
            fail("erreur de création d'infos"+ e);
        }
    }
    @Test
    void rechSigle() {
        Classe cl = classeServiceImpl.read("SigleTest");
        boolean trouve=false;
        if(cl.getSigle().equals("SigleTest")) trouve=true;
        else fail("un record ne correspond pas , sigle = "+cl.getSigle());
        assertTrue(trouve,"record non trouvé dans la liste");
    }

    @Test
    void all() {
        try {
            List<Classe> lcl = classeServiceImpl.all();
            assertNotEquals(0,lcl.size(),"la liste ne contient aucun élément");
        }catch (Exception e){
            fail("erreur de recherche de toutes les classes "+e);
        }
    }
}