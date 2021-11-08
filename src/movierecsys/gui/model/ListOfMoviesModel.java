package movierecsys.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import movierecsys.be.Movie;
import movierecsys.bll.OwsLogicFacade;

public class ListOfMoviesModel
{

    private ObservableList<Movie> allMovies;
    private OwsLogicFacade logicFacade;

    public ListOfMoviesModel()
    {
        allMovies = FXCollections.observableArrayList();
    }

    public ObservableList<Movie> getAllMovies() {
        return allMovies;
    }

}
