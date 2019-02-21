/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.persistence.impl;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author 2098325
 */
@Service
public class FilterByGender implements CinemaFilter {

    private final Map<String, Cinema> cinemas = new HashMap<>();

    public FilterByGender() {

        //load stub data
        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions = new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2", "Action"), functionDate);
        CinemaFunction funct3 = new CinemaFunction(new Movie("SuperHeroes Movie 3", "Action"), functionDate);
        CinemaFunction funct5 = new CinemaFunction(new Movie("SuperHeroes Movie 5", "Action"), functionDate);
        CinemaFunction funct4 = new CinemaFunction(new Movie("SuperHeroes Movie 4", "Action"), functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2", "Horror"), functionDate);
        functions.add(funct1);
        functions.add(funct2);
        functions.add(funct3);
        functions.add(funct4);
        functions.add(funct5);
        Cinema c = new Cinema("Movies Bogotá", functions);
        cinemas.put("Movies Bogotá", c);
    }

    @Override
    public List<Movie> getListMovies(Cinema cinema, String date, Object factor) {

        List<Movie> resp =  new ArrayList<Movie>();

        Cinema temp = getCinemaString(cinema);
        List<CinemaFunction> tempCi = temp.getFunctions();
        
        for (CinemaFunction fu : tempCi) {
            if (date.equals(fu.getDate())) {
                Movie tem = fu.getMovie();
                if (tem.getGenre().equals((String) factor)) {
                    resp.add(tem);
                }
            }
        }
        return resp;
    }

    public Cinema getCinemaString(Cinema name) {
        Cinema resp = null;
        Iterator it;
        it = cinemas.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            if (cinemas.get(key).getName().equals(name.getName())) {
                resp = cinemas.get(key);
            }
        }
        return resp;
    }

}
