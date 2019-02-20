/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.model;

import edu.eci.arsw.cinema.persistence.CinemaException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author cristian
 */
@Service
public class CinemaFunction {

    private Movie movie;
    private List<List<Boolean>> seats = new ArrayList<>();
    private String date;

    public CinemaFunction() {
    }

    public CinemaFunction(Movie movie, String date) {
        this.movie = movie;
        this.date = date;
        for (int i = 0; i < 7; i++) {
            List<Boolean> row = new ArrayList<>(Arrays.asList(new Boolean[12]));
            Collections.fill(row, Boolean.TRUE);
            this.seats.add(row);
        }
    }

    public int getTicketAvality() {
        int resp = 0;
        for (int i = 0; i < 7; i++) {
            for (int u = 0; u < 12; u++) {
                if (seats.get(i).get(u).equals(true)) {
                    resp++;
                }
            }
        }
        return resp;
    }

    public void buyTicket(int row, int col) throws CinemaException {
        if (seats.get(row).get(col).equals(true)) {
            seats.get(row).set(col, Boolean.FALSE);
        } else {
            throw new CinemaException("Seat booked");
        }
    }

    public List<List<Boolean>> getSeats() {
        return this.seats;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
