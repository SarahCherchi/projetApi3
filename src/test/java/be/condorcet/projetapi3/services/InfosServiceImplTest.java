package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Classe;
import be.condorcet.projetapi3.entities.Infos;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class InfosServiceImplTest {
    private Classe cl;
    private Infos inf;

    @Autowired
    private ClasseServiceImpl classeServiceImpl;

    @Autowired
    private InfosServiceImpl infosServiceImpl;

    @BeforeEach
    void setUp() {
        try{
            cl = new Classe(null,"SigleTest",1,"SpecialiteTest",23,new ArrayList<>());
            classeServiceImpl.create(cl);
            System.out.println("création d'une classe : "+cl);
            inf = new Infos(4,null,null,null,null);
            infosServiceImpl.create(inf);
            System.out.println("création de l'information : "+inf);
        }
        catch (Exception e){
            System.out.println("erreur de création de l'information "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try{
            infosServiceImpl.delete(inf);
        }
        catch(Exception e){
            System.out.println("erreur d'effacement de l'info "+e);
        }
        try{
            classeServiceImpl.delete(cl);
        }
        catch(Exception e){
            System.out.println("erreur d'effacement de la classe "+e);
        }
    }

    @Test
    void create() {
        assertNotEquals(0,inf.getClasse(),"numéro de la classe non incrémenté");
        // à voir comment faire avec une classe d'association
        // problème ede comparaison d'un int avec un objet de type Classe
        // Pas de numéro qui correspond à une infos
        // Clé primaire de infos Classe + Cours ! peut être creer cours avant à voir
    }

    @Test
    void read() {
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

    @Test
    void getInfos() {
    }
}