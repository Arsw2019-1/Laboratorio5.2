/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.persistence;

import edu.eci.arsw.cinema.model.Movie;
import java.util.List;

/**
 *
 * @author 2098325
 */
public interface CinemaFilter {
    
    
    public List<Movie> getListMovies(String cinema, String date, Object factor);
    
}
