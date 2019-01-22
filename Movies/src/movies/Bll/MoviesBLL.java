/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movies.Bll;

import java.util.List;
import movies.Be.Category;
import movies.Be.Movie;
import movies.Dal.MoviesDao;

/**
 *
 * @author Lukas
 */
public class MoviesBLL {

    MoviesDao dal = new MoviesDao();
    public int checkForName;

    public List<Category> loadCategories() {
        return dal.loadCategories();
    }

    public List<Movie> LoadMovie() {
        return dal.loadMovie();
    }

    public void addCategory(Category category) {
        dal.addCategory(category);
    }

    public void removeCategory(int selectedCategory) {
        dal.removeCategory(selectedCategory);
    }

    public List<Movie> loadMovies(int selectedCategory) {
        return dal.loadMovies(selectedCategory);
    }

    public List<Movie> filterMovies(String filteredMovies) {
        return dal.filterMovies(filteredMovies);
    }

    public void removeMovie(int selectedMovie) {
        dal.removeMovie(selectedMovie);
    }

    public void addAllMovies(Movie movie, List<Category> category) {
        dal.addAllMovies(movie, category);
    }

    public void setLastView(Movie movie) {
        dal.setLastView(movie);
    }

    public int checkForName(Movie movie) {
        return dal.checkForName(movie);
    }

}
