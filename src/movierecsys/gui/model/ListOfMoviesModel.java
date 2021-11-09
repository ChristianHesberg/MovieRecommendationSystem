package movierecsys.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import movierecsys.be.Movie;
import movierecsys.bll.OwsLogicFacade;
import movierecsys.dal.MovieDAO;

import java.io.IOException;

public class ListOfMoviesModel
{

    private ObservableList<Movie> allMovies;
    private OwsLogicFacade logicFacade;
    private MovieDAO movieData;

    public ListOfMoviesModel()
    {
        /*
        allMovies = FXCollections.observableArrayList();
        movieData = new MovieDAO();
        try {
            allMovies.addAll(movieData.getAllMovies());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
         */

    }

    public ObservableList<Movie> getAllMovies() {
        return allMovies;
    }

}
