/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.persistence.impl;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author cristian
 */
@Service
public class InMemoryCinemaPersistence implements CinemaPersitence{
    
    private final Map<String,Cinema> cinemas=new HashMap<>();

    public InMemoryCinemaPersistence() {
        //load stub data
        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie","Action"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night","Horror"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c=new Cinema("cinemaX",functions);
        cinemas.put("cinemaX", c);
    }    

    @Override
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException {
        try{
            Cinema temp=getCinemaString(cinema);            
            List<CinemaFunction> tro=temp.getFunctions();           
            for(CinemaFunction tr: tro){
                if(tr.getMovie().equals(movieName)){
                    tr.buyTicket(row, col);
                }
            }
        }catch(Exception ex){
            new CinemaException("Ya se vendio este asiento");     
        }
    }    
    public Cinema getCinemaString(String name){
        Cinema resp=null;
        Iterator it;
        it = cinemas.keySet().iterator();
        while(it.hasNext()){
            String key=(String) it.next();
            if(cinemas.get(key).getName().equals(name)){
                resp=cinemas.get(key);
            }
        }
        return resp;
    }
    @Override
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {
        List<CinemaFunction> resp=new ArrayList<CinemaFunction>();
        try{
            Cinema temp=getCinemaString(cinema);            
            List<CinemaFunction> tro=temp.getFunctions();  
            for(CinemaFunction tr: tro){
                if(tr.getDate().equals(date)){
                    resp=temp.getFunctions();
                }
            }
        }catch(Exception ex){
            new CinemaException("No se pudo, lo siento");     
        }        
        return resp;
    }

    @Override
    public void saveCinema(Cinema c) throws CinemaPersistenceException {
        if (cinemas.containsKey(c.getName())){
            throw new CinemaPersistenceException("The given cinema already exists: "+c.getName());
        }
        else{
            cinemas.put(c.getName(),c);
        }   
    }

    @Override
    public Cinema getCinema(String name) throws CinemaPersistenceException {
        return cinemas.get(name);
    }
    
    @Override
    public Collection<Cinema> getCinemaValues(){
        return cinemas.values();
    }

}
