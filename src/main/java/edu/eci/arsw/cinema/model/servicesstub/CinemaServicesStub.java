/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.model.servicesstub;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.services.CinemaServices;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 2098325
 */
public class CinemaServicesStub {

    CinemaServices cinema;

    public void CinemaServicesStub() throws CinemaPersistenceException {
        //Registrar cines
        String functionDate = "2028-9-20 19:30";
        List<CinemaFunction> functions = new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("Advengers End Game", "Action"), functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("Mis huellas a casa", "Drama"), functionDate);
        functions.add(funct1);
        functions.add(funct2);
        cinema.addNewCinema( new Cinema("Circo Soledad", functions));
        functions.clear();
        functionDate = "2020-12-29 6:45";
        funct1 = new CinemaFunction(new Movie("BATTLE ANGEL: LA ÚLTIMA GUERRERA", "Action"), functionDate);
        funct2 = new CinemaFunction(new Movie("FELIZ DÍA DE TU MUERTE 2", "Horror"), functionDate);
        functions.add(funct1);
        functions.add(funct2);    
        cinema.addNewCinema( new Cinema("El principio del fin", functions));

    }
    public void consCIne() throws CinemaPersistenceException{
    
        System.out.println("Los cines son: "+cinema.getAllCinemas());
    
    }
    
    public void funcByCinema(String name, String date){
        
        System.out.println("Las funciones del cine : "+name+" son :"+cinema.getFunctionsbyCinemaAndDate(name, date));
    
    }
    
    public void buyTickets() throws CinemaException{
        cinema.buyTicket(1, 2, "El principio del fin", "2020-12-29 6:45", "BATTLE ANGEL: LA ÚLTIMA GUERRERA");
        cinema.buyTicket(5, 5, "Circo Soledad", "2028-9-20 19:30", "Advengers End Game");
        cinema.buyTicket(5, 5, "El principio del fin", "2020-12-29 6:45", "Advengers End Game");    
    }
    
    
    
    
    
}
