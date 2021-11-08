package movierecsys.bll;

import movierecsys.be.Movie;
import movierecsys.be.Rating;
import movierecsys.be.User;
import movierecsys.bll.util.MovieSearcher;

import java.util.List;

public class MovieManager implements OwsLogicFacade
{

    private MovieSearcher movieSearcher;

    public MovieManager()
    {
        movieSearcher = new MovieSearcher();
    }

    @Override
    public List<Rating> getRecommendedMovies(User user) {
        return null;
    }

    @Override
    public List<Movie> getAllTimeTopRatedMovies() {
        return null;
    }

    @Override
    public List<Movie> getMovieReccomendations(User user) {
        return null;
    }

    @Override
    public List<Movie> searchMovies(String query) {
        return null;
    }

    @Override
    public Movie createMovie(int year, String title) {
        return null;
    }

    @Override
    public void updateMovie(Movie movie) {

    }

    @Override
    public void deleteMovie(Movie movie) {

    }

    @Override
    public void rateMovie(Movie movie, User user, int rating) {

    }

    @Override
    public User createNewUser(String name) {
        return null;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
