/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import movierecsys.be.Movie;

/**
 * @author pgn
 */
public class MovieDAO {

    private static final String MOVIE_SOURCE = "data/movie_titles.txt";

    /**
     * Gets a list of all movies in the persistence storage.
     *
     * @return List of movies.
     * @throws java.io.FileNotFoundException
     */
    public List<Movie> getAllMovies() throws FileNotFoundException, IOException {
        List<Movie> allMovies = new ArrayList<>();
        File file = new File(MOVIE_SOURCE);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Movie mov = stringArrayToMovie(line);
                    allMovies.add(mov);
                } catch (Exception ex) {
                    //Do nothing we simply do not accept malformed lines of data.
                    //In a perfect world you should at least log the incident.
                }
            }
        }
        return allMovies;
    }

    /**
     * Reads a movie from a , s
     *
     * @param t
     * @return
     * @throws NumberFormatException
     */
    private Movie stringArrayToMovie(String t) {
        String[] arrMovie = t.split(",");

        int id = Integer.parseInt(arrMovie[0]);
        int year = Integer.parseInt(arrMovie[1]);
        String title = arrMovie[2];
        if (arrMovie.length > 3) {
            for (int i = 3; i < arrMovie.length; i++) {
                title += "," + arrMovie[i];
            }
        }
        Movie mov = new Movie(id, year, title);
        return mov;
    }

    /**
     * Creates a movie in the persistence storage.
     *
     * @param releaseYear The release year of the movie
     * @param title       The title of the movie
     * @return The object representation of the movie added to the persistence
     * storage.
     */
    public Movie createMovie(int releaseYear, String title) {
        try (FileWriter writer = new FileWriter(MOVIE_SOURCE, true);
            BufferedWriter bw = new BufferedWriter(writer))
        {
            int biggest = 0;
            int newID = 0;
            for(Movie movie : getAllMovies())
            {
                if (movie.getId() > biggest)
                {
                    biggest = movie.getId();
                    newID = biggest + 1;
                }
            }
            Movie movieToAdd = new Movie(newID, releaseYear, title);
            bw.write("\r\n" + newID + "," + releaseYear + "," + title);
            return movieToAdd;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Deletes a movie from the persistence storage.
     *
     * @param movie The movie to delete.
     */
    public void deleteMovie(Movie movie) {

        String newFileText = "";
        try (FileReader reader = new FileReader(MOVIE_SOURCE);
             BufferedReader br = new BufferedReader(reader)) {
            Scanner scanner = new Scanner(br);
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine().trim();
                String[] lineSplit = currentLine.split(",");
                int ID = Integer.parseInt(lineSplit[0]);
                if (ID != movie.getId() && ID == 1) {
                    newFileText += currentLine;
                }
                if (ID != movie.getId())
                {
                    newFileText += "\r\n" + currentLine;
                }
            }
            try (FileWriter writer = new FileWriter(MOVIE_SOURCE);
                 BufferedWriter bw = new BufferedWriter(writer))
            {
                bw.write(newFileText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Updates the movie in the persistence storage to reflect the values in the
     * given Movie object.
     *
     * @param movie The updated movie.
     */
    public void updateMovie(Movie movie) {

        String newFileText = "";
        try (FileReader reader = new FileReader(MOVIE_SOURCE);
             BufferedReader br = new BufferedReader(reader)) {
            Scanner scanner = new Scanner(br);
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine().trim();
                String[] lineSplit = currentLine.split(",");
                int ID = Integer.parseInt(lineSplit[0].trim());
                if (ID != movie.getId()) {
                    newFileText += currentLine + "\r\n";
                }
                if (ID == movie.getId())
                {
                    newFileText += movie.getId() + "," + movie.getYear() + "," + movie.getTitle();
                }
            }
            try (FileWriter writer = new FileWriter(MOVIE_SOURCE);
                 BufferedWriter bw = new BufferedWriter(writer))
            {
                bw.write(newFileText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        }

        /**
     * Gets a the movie with the given ID.
     *
     * @param id ID of the movie.
     * @return A Movie object.
     */
    public Movie getMovie(int id) {
        String newFileText = "";
        try (FileReader reader = new FileReader(MOVIE_SOURCE);
             BufferedReader br = new BufferedReader(reader)) {
            Scanner scanner = new Scanner(br);
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine().trim();
                String[] lineSplit = currentLine.split(",");
                int ID = Integer.parseInt(lineSplit[0]);
                if(ID == id)
                {
                    int year = Integer.parseInt(lineSplit[1]);
                    Movie movie = new Movie(ID, year, lineSplit[2]);
                    return movie;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

}
