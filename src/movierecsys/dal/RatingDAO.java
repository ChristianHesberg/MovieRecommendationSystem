/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import movierecsys.be.Movie;
import movierecsys.be.Rating;
import movierecsys.be.User;

/**
 *
 * @author pgn
 */
public class RatingDAO
{

    private static final String RATING_SOURCE = "data/ratings.txt";
    private static final MovieDAO movieData = new MovieDAO();
    private static final UserDAO userData = new UserDAO();
    
    /**
     * Persists the given rating.
     * @param rating the rating to persist.
     */
    public void createRating(Rating rating)
    {
        try (FileReader reader = new FileReader(RATING_SOURCE);
             BufferedReader br = new BufferedReader(reader))
        {

        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileWriter writer = new FileWriter(RATING_SOURCE, true);
          BufferedWriter bw = new BufferedWriter(writer))
    {

    }
    catch (IOException e) {
        e.printStackTrace();
    }
    }
    
    /**
     * Updates the rating to reflect the given object.
     * @param rating The updated rating to persist.
     */
    public void updateRating(Rating rating)
    {
        String newFileText = "";
        try (FileReader reader = new FileReader(RATING_SOURCE);
             BufferedReader br = new BufferedReader(reader)) {
            Scanner scanner = new Scanner(br);
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine().trim();
                String[] lineSplit = currentLine.split(",");
                int movieID = Integer.parseInt(lineSplit[0].trim());
                int userID = Integer.parseInt(lineSplit[1].trim());
                if (movieID != rating.getMovie().getId() && userID != rating.getUser().getId()) {
                    newFileText += currentLine + "\r\n";
                }
                if (movieID == rating.getMovie().getId() && userID == rating.getUser().getId())
                {
                    newFileText += rating.getMovie().getId() + "," + rating.getUser().getId() + "," + rating.getRating();
                }
            }
            try (FileWriter writer = new FileWriter(RATING_SOURCE);
                 BufferedWriter bw = new BufferedWriter(writer))
            {
                bw.write(newFileText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }    }
    
    /**
     * Removes the given rating.
     * @param rating 
     */
    public void deleteRating(Rating rating)
    {
        //TODO Delete rating
    }
    
    /**
     * Gets all ratings from all users.
     * @return List of all ratings.
     */
    public List<Rating> getAllRatings()
    {
        List<Rating> allRatings = new ArrayList<>();
        try {
            List<String> ratingList = Files.readAllLines(Path.of(RATING_SOURCE));
            for (String line : ratingList)
            {
                String[] values = line.split(",");

                int movieID = Integer.parseInt(values[0]);
                int userID = Integer.parseInt(values[1]);
                int rating = Integer.parseInt(values[2]);

                Rating newRating = new Rating(movieData.getMovie(movieID), userData.getUser(userID), rating);
                allRatings.add(newRating);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allRatings;
        /*List<Rating> allRatings = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(RATING_SOURCE)))
        {
            Scanner scanner = new Scanner(br);
            while(scanner.hasNextLine())
            {
                String currentLine = scanner.nextLine().trim();
                String[] arrRating = currentLine.split(",");

                int movieID = Integer.parseInt(arrRating[0]);
                int userID = Integer.parseInt(arrRating[1]);
                int rating = Integer.parseInt(arrRating[2]);

                Rating newRating = new Rating(movieData.getMovie(movieID), userData.getUser(userID), rating);
                allRatings.add(newRating);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return allRatings;

         */
    }
    
    /**
     * Get all ratings from a specific user.
     * @param user The user 
     * @return The list of ratings.
     */
    public List<Rating> getRatings(User user)
    {
        //TODO Get user ratings.
        return null;
    }
    
}
