/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.bll.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import movierecsys.be.Movie;
import movierecsys.dal.MovieDAO;

/**
 *
 * @author pgn
 */
public class MovieSearcher
{

    private MovieDAO movieData;

    public MovieSearcher()
    {
        movieData = new MovieDAO();
    }

    public List<Movie> search(List<Movie> searchBase, String query)
    {
        List<Movie> searchResults = new ArrayList<>();
        for (Movie m : searchBase)
        {
            if (m.getTitle().toLowerCase().contains(query.toLowerCase().trim()))
            {
                searchResults.add(m);
            }
        }
        return searchResults;
    }
    
}
