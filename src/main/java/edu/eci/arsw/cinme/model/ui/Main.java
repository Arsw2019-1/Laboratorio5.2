/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinme.model.ui;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.services.CinemaServices;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import static sun.net.www.http.HttpClient.New;

/**
 *
 * @author 2098325
 */
public class Main {
    CinemaServices cine;
     public static void main(String a[]) throws CinemaPersistenceException, CinemaException {
         //AnnotationConfigApplicationContext cont=new AnnotationConfigApplicationContext();
         //cont.scan("edu.eci.arsw.cinme.model.ui");
         //cont.refresh();
         ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
         CinemaServices ce=ac.getBean(CinemaServices.class);
         
         //Probando filtro gender
         Cinema r= new Cinema();
         r.setName("Movies Bogotá");
         List<Movie>er=ce.getMovieByGender(r, "2018-12-18 15:30", "Action");
         //List<Movie>er=ce.getMovieByGender(r, "2018-12-18 15:30", 10);
         for(Movie rr: er){
             System.out.println("La peli es "+rr.getName());

         }
         
         //Probando adicionar cinema
          List<Movie> fi = new ArrayList<Movie>();
                 String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions = new ArrayList<>();
        fi.add(new Movie("SuperHeroes Movie 2", "Action"));
        fi.add(new Movie("Sup M3", "Action"));
        fi.add(new Movie("SuperHe 4", "Action"));
        fi.add(new Movie("Supvie 5", "Action"));
         Cinema nuevo=new Cinema("El dolor del gobierno",functions);
         ce.addNewCinema(nuevo);
         //Probar darme todos los cinemas
         
         Collection<Cinema> re=ce.getAllCinemas();
         System.out.println("Miremos los cinemas : "+re.toString());
         
         
         //Probar CINEMA POR NOMBRE
         
         Cinema trt=ce.getCinemaByName("El dolor del gobierno");
         System.out.println("El Cinema encontrado :"+trt+"y el nombre : "+trt.getName());
         //Probar que deme las funciones de un cinema
         List<CinemaFunction> pop=ce.getFunctionsbyCinemaAndDate("El dolor del gobierno", "2018-12-18 15:30");
        
         for(CinemaFunction cf : pop){
             System.out.println("La peloicula es : "+cf.getMovie());
         
         
         }
         
     
     }
    
    
}
