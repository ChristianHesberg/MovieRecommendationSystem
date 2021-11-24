/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import movierecsys.be.Movie;
import movierecsys.gui.model.ListOfMoviesModel;

/**
 *
 * @author pgn
 */
public class MovieRecController implements Initializable
{

    @FXML
    private TextField updateTxtField;
    @FXML
    private TextField deleteTxtField;
    @FXML
    private TextField addTxtField;
    @FXML
    private Button addBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button searchBtn;
    /**
     * The TextField containing the URL of the targeted website.
     */
    @FXML
    private TextField txtMovieSearcjh;

    /**
     * The TextField containing the query word.
     */
    @FXML
    private ListView<Movie> lstMovies;
    private ListOfMoviesModel moviesModel;

    public MovieRecController()
    {
        moviesModel = new ListOfMoviesModel();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        lstMovies.setItems(moviesModel.getAllMovies());
    }


    public void handleSearchClick(ActionEvent actionEvent) {
        lstMovies.setItems(moviesModel.searchResults(txtMovieSearcjh.getText()));
    }

    public void handleAddClick(ActionEvent actionEvent) {
        moviesModel.addMovie(addTxtField.getText());
        lstMovies.setItems(moviesModel.getAllMovies());
    }

    public void handleUpdateClick(ActionEvent actionEvent) {
        moviesModel.updateMovie(updateTxtField.getText());
        lstMovies.setItems(moviesModel.getAllMovies());
    }

    public void handleDeleteClick(ActionEvent actionEvent) {
        moviesModel.deleteMovie(deleteTxtField.getText());
        lstMovies.setItems(moviesModel.getAllMovies());
    }
}