package movierecsys.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import movierecsys.be.Movie;
import movierecsys.bll.MovieManager;
import movierecsys.bll.OwsLogicFacade;
import movierecsys.bll.util.MovieSearcher;
import movierecsys.dal.MovieDAO;

import java.io.IOException;
import java.util.List;

public class ListOfMoviesModel
{

    private ObservableList<Movie> allMovies;
    private OwsLogicFacade logicFacade;
    private MovieManager movieManager;

    public ListOfMoviesModel()
    {

    }

    public ObservableList<Movie> getAllMovies() {
        return allMovies;
    }

    public ObservableList<Movie> searchResults(String input)
    {
        return FXCollections.observableArrayList(movieManager.searchMovies(input));
    }


}
