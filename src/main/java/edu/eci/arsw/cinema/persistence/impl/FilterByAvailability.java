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

/**
 *
 * @author 2098325
 */
public class FilterByAvailability implements CinemaFilter {

    private final Map<String, Cinema> cinemas = new HashMap<>();

    public FilterByAvailability() {
        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions = new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie", "Action"), functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night", "Horror"), functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c = new Cinema("cinemaX", functions);
        cinemas.put("cinemaX", c);
    }

    @Override
    public List<Movie> getListMovies(String cinema, String date, Object factor) {
        List<Movie> resp = null;
        Cinema temp = getCinemaString(cinema);
        List<CinemaFunction> tempCi = temp.getFunctions();

        for (CinemaFunction fu : tempCi) {
            if (date.equals(fu.getDate())) {
                int tempp=fu.getTicketAvality();
                if ((int)factor>tempp) {
                    resp.add(fu.getMovie());
                }
            }
        }

        return resp;
    }

    public Cinema getCinemaString(String name) {
        Cinema resp = null;
        Iterator it;
        it = cinemas.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            if (cinemas.get(key).equals(name)) {
                resp = cinemas.get(key);
            }
        }
        return resp;
    }
}
