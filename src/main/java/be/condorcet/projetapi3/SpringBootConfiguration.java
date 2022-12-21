package be.condorcet.projetapi3;

import be.condorcet.projetapi3.services.CoursServiceImpl;
import be.condorcet.projetapi3.services.InterfCoursService;
import be.condorcet.projetapi3.services.InterfSalleService;
import be.condorcet.projetapi3.services.SalleServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootConfiguration {

    @Value("${server.mode}")
    private String mode;
    @Bean
    InterfSalleService salleServiceImpl() {
        System.out.println("création du service salle en mode : "+mode);
        switch (mode){
            case "PROD" : return new SalleServiceImpl();
            //case "STUB" : return new SalleServiceStub();
            //case "MOCK" : return new SalleServiceMock();
            default: return new SalleServiceImpl();
        }
    }
    @Bean
    InterfCoursService coursServiceImpl() {
        System.out.println("création du service cours en mode : "+mode);
        switch (mode){
            case "PROD" : return new CoursServiceImpl();
            //case "STUB" : return new CoursServiceStub();
            //case "MOCK" : return new CoursServiceMock();
            default: return new CoursServiceImpl();
        }
    }
}

