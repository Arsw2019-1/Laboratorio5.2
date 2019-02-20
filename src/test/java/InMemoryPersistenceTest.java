
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.impl.FilterByGender;
import edu.eci.arsw.cinema.persistence.impl.InMemoryCinemaPersistence;
import edu.eci.arsw.cinema.services.CinemaServices;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cristian
 */
public class InMemoryPersistenceTest {

    @Test
    public void saveNewAndLoadTest() throws CinemaPersistenceException {
        InMemoryCinemaPersistence ipct = new InMemoryCinemaPersistence();

        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions = new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2", "Action"), functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2", "Horror"), functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c = new Cinema("Movies Bogotá", functions);
        ipct.saveCinema(c);

        assertNotNull("Loading a previously stored cinema returned null.", ipct.getCinema(c.getName()));
        assertEquals("Loading a previously stored cinema returned a different cinema.", ipct.getCinema(c.getName()), c);
    }

    @Test
    public void saveExistingCinemaTest() {
        InMemoryCinemaPersistence ipct = new InMemoryCinemaPersistence();

        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions = new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2", "Action"), functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2", "Horror"), functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c = new Cinema("Movies Bogotá", functions);

        try {
            ipct.saveCinema(c);
        } catch (CinemaPersistenceException ex) {
            fail("Cinema persistence failed inserting the first cinema.");
        }

        List<CinemaFunction> functions2 = new ArrayList<>();
        CinemaFunction funct12 = new CinemaFunction(new Movie("SuperHeroes Movie 3", "Action"), functionDate);
        CinemaFunction funct22 = new CinemaFunction(new Movie("The Night 3", "Horror"), functionDate);
        functions.add(funct12);
        functions.add(funct22);
        Cinema c2 = new Cinema("Movies Bogotá", functions2);
        try {
            ipct.saveCinema(c2);
            fail("An exception was expected after saving a second cinema with the same name");
        } catch (CinemaPersistenceException ex) {

        }
    }

    @Test
    public void buyTicketSave() throws CinemaException {
        InMemoryCinemaPersistence ipct = new InMemoryCinemaPersistence();
        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions = new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2", "Action"), functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2", "Horror"), functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c = new Cinema("Movies Bogotá", functions);

        try {
            ipct.saveCinema(c);
            ipct.buyTicket(0, 0, "Movies Bogotá", "2018-12-18 15:30", "The Nigth 2");
            ipct.buyTicket(0, 1, "Movies Bogotá", "2018-12-18 15:30", "The Nigth 2");
            ipct.buyTicket(0, 0, "SuperHeroes Movie 2", "2018-12-18 15:30", "The Nigth 2");
            ipct.buyTicket(1, 0, "Movies Bogotá", "2018-12-18 15:30", "The Nigth 2");
            ipct.buyTicket(0, 5, "Movies Bogotá", "2018-12-18 15:30", "The Nigth 2");

        } catch (CinemaPersistenceException ex) {
            fail("Cinema persistence failed inserting the first cinema.");
        }

    }

    @Test
    public void getFunctionCinemaSave() {

        InMemoryCinemaPersistence ipct = new InMemoryCinemaPersistence();

        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions = new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2", "Action"), functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2", "Horror"), functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c = new Cinema("Movies Bogotá", functions);

        try {
            ipct.saveCinema(c);
        } catch (CinemaPersistenceException ex) {
            fail("Cinema persistence failed inserting the first cinema.");
        }
        List<CinemaFunction> temp;
        List<CinemaFunction> functions2 = new ArrayList<>();
        CinemaFunction funct12 = new CinemaFunction(new Movie("SuperHeroes Movie 3", "Action"), functionDate);
        CinemaFunction funct22 = new CinemaFunction(new Movie("The Night 3", "Horror"), functionDate);
        functions.add(funct12);
        functions.add(funct22);
        Cinema c2 = new Cinema("Movies Bogotá", functions2);
        try {
            ipct.saveCinema(c2);
            fail("An exception was expected after saving a second cinema with the same name");
            temp = ipct.getFunctionsbyCinemaAndDate("Movies Bogota", "2018-12-18 15:30");
            System.out.println("" + temp);
            assertEquals(temp, c2.getFunctions());
        } catch (CinemaPersistenceException ex) {

        }
    }

    @Test
    public void testFilter() {
        List<Movie>fi=new ArrayList<Movie>();
        FilterByGender ge = new FilterByGender();
        CinemaServices se=new CinemaServices();
        //se = null;
        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions = new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2", "Action"), functionDate);
        fi.add(new Movie("SuperHeroes Movie 2", "Action"));
        CinemaFunction funct3 = new CinemaFunction(new Movie("SuperHeroes Movie 3", "Action"), functionDate);
        fi.add(new Movie("SuperHeroes Movie 3", "Action"));
        CinemaFunction funct4 = new CinemaFunction(new Movie("SuperHeroes Movie 4", "Action"), functionDate);
        fi.add(new Movie("SuperHeroes Movie 4", "Action"));
        CinemaFunction funct5 = new CinemaFunction(new Movie("SuperHeroes Movie 5", "Action"), functionDate);
        fi.add(new Movie("SuperHeroes Movie 5", "Action"));
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2", "Horror"), functionDate);
        functions.add(funct1);
        functions.add(funct2);
        functions.add(funct3);
                functions.add(funct4);
        functions.add(funct5);                
        Cinema c = new Cinema("Movies Bogotá", functions);
        List<Movie> re = null;
        try {
            se.addNewCinema(c);
            re = se.getMovieByGender("Movies Bogotá", "2018-12-18 15:30", "Action");

            assertEquals(fi, re);

        } catch (CinemaPersistenceException ex) {
            fail("Cinema persistence failed inserting the first cinema.");
        }

    }
}
