package movierecsys.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import movierecsys.be.Movie;
import movierecsys.bll.OwsLogicFacade;
import movierecsys.bll.util.MovieSearcher;
import movierecsys.dal.MovieDAO;

import java.io.IOException;
import java.util.List;

public class ListOfMoviesModel
{

    private ObservableList<Movie> allMovies;
    private OwsLogicFacade logicFacade;
    private MovieDAO movieData;
    private MovieSearcher movieSearcher;

    public ListOfMoviesModel()
    {
        allMovies = FXCollections.observableArrayList();
        movieData = new MovieDAO();
        movieSearcher = new MovieSearcher();
        try {
            allMovies.addAll(movieData.getAllMovies());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Movie> getAllMovies() {
        return allMovies;
    }

    public ObservableList<Movie> searchResults(String input)
    {
        try {
            return movieSearcher.search(movieData.getAllMovies(), input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
