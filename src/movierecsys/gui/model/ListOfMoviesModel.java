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
    private ObservableList<Movie> searchedMovies;
    private OwsLogicFacade logicFacade;
    private MovieManager movieManager;

    public ListOfMoviesModel()
    {
        this.movieManager = new MovieManager();
    }

    public ObservableList<Movie> getAllMovies() {
        try {
            allMovies = FXCollections.observableArrayList(movieManager.getMovieList());
            return allMovies;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Movie> searchResults(String input)
    {
        searchedMovies = FXCollections.observableArrayList(movieManager.searchMovies(input));
        return searchedMovies;
    }

    public void addMovie(String input)
    {
        String[] strArr = input.split(",");
        int year = Integer.parseInt(strArr[0]);
        String title = strArr[1].trim();

        movieManager.createMovie(year, title);
    }

    public void deleteMovie(String input)
    {
        String[] strArr = input.split(",");
        int id = Integer.parseInt(strArr[0].trim());
        int year = Integer.parseInt(strArr[1].trim());
        String title = strArr[2].trim();

        Movie deletedMovie = new Movie(id, year, title);
        movieManager.deleteMovie(deletedMovie);
    }

    public void updateMovie(String input)
    {
        String[] strArr = input.split(",");
        int id = Integer.parseInt(strArr[0].trim());
        int year = Integer.parseInt(strArr[1].trim());
        String title = strArr[2].trim();

        Movie updatedMovie = new Movie(id, year, title);
        movieManager.updateMovie(updatedMovie);

    }
}
