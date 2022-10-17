package be.condorcet.projetapi3;

import be.condorcet.projetapi3.entities.Classe;
import be.condorcet.projetapi3.services.ClasseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Map;

@Controller
@RequestMapping("/classes")
public class GestClasse {
    @Autowired
    private ClasseServiceImpl classeServiceImpl;
    @RequestMapping("/tous")
    public String affTous(Map<String, Object> model){
        System.out.println("recherche classes");
        try {
            Collection<Classe> lcl= classeServiceImpl.all();
            model.put("mesClasses", lcl);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche-------- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "affichagetoutesClasses";
    }
    @RequestMapping("/create")
    public String create(@RequestParam String sigle, @RequestParam Integer annee,@RequestParam String specialite, Map<String, Object> model){
        System.out.println("création de la classe");
        Classe cl = new Classe(sigle,annee,specialite);
        try {
            classeServiceImpl.create(cl);
            cl = classeServiceImpl.read(cl.getIdclasse());
            cl.setNbreleves(30);
            classeServiceImpl.update(cl);
            model.put("nouvcla",cl);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la création-------- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "nouvelleClasse";
    }
    @RequestMapping("/read")
    public String read(@RequestParam int idclasse, Map<String, Object> model){
        System.out.println("recherche de la classe n° "+idclasse);
        try {
            Classe cl = classeServiceImpl.read(idclasse);
            model.put("macla",cl);
        }catch (Exception e) {
            System.out.println("----------erreur lors de la recherche ----- --- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "affclasse";
    }
    @RequestMapping("/update")
    public String update(@RequestParam int idclasse,@RequestParam Integer nbreleves, Map<String, Object> model){
        System.out.println("modification du nombre d'élèves de la classe n° "+idclasse);
        try {
            Classe cl = classeServiceImpl.read(idclasse);
            cl.setNbreleves(nbreleves);
            classeServiceImpl.update(cl);
            model.put("modifcla",cl);
        }catch (Exception e) {
            System.out.println("----------erreur lors de la recherche ----- --- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "modifclasse";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam int idclasse, Map<String, Object> model){
        System.out.println("Suppression de la classe n° "+idclasse);
        try {
            Classe cl = classeServiceImpl.read(idclasse);
            classeServiceImpl.delete(cl);
            model.put("macla",cl);
        }catch (Exception e) {
            System.out.println("----------erreur lors de la recherche ----- --- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "affclasse";
    }
}
