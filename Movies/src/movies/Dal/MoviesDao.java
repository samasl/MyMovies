/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movies.Dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import movies.Be.Category;
import movies.Be.Movie;
import movies.Bll.MoviesBLL;
import movies.ConnectionManager;

/**
 *
 * @author Lukas
 */
public class MoviesDao {

    private static ConnectionManager conMan = new ConnectionManager();

    public List<Category> loadCategories() {
        List<Category> allCategories = new ArrayList();
        try (Connection con = conMan.getConnection()) {
            String query = "SELECT * FROM Categories";
            PreparedStatement statm = con.prepareStatement(query);
            ResultSet rs = statm.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setCategory(rs.getString("name"));
                allCategories.add(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MoviesBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allCategories;
    }

    public void addCategory(Category category) {
        try (Connection con = conMan.getConnection()) {
            String sql = "INSERT INTO Categories (name) VALUES(?)";
            PreparedStatement statm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statm.setString(1, category.getCategory());
            int affected = statm.executeUpdate();
            if (affected < 1) {
                throw new SQLException("Movie could not be added");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MoviesDao.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    public void removeCategory(int selectedCategory) {
        try (Connection con = conMan.getConnection()) {
            String sql = "DELETE FROM Categories WHERE id = ?";
            PreparedStatement statm = con.prepareStatement(sql);
            statm.setInt(1, selectedCategory);
            statm.execute();
            String query = "SELECT * FROM catMovie WHERE categoryId = ? ";
            PreparedStatement statme = con.prepareStatement(query);
            statme.setInt(1, selectedCategory);
            ResultSet rs = statme.executeQuery();
            while (rs.next()) {
                String s1 = "DELETE FROM catMovie WHERE categoryId = ?";
                PreparedStatement pstmt1 = con.prepareStatement(s1);
                pstmt1.setInt(1, selectedCategory);
                pstmt1.execute();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MoviesDao.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    public List<Movie> loadMovies(int selectedCategory) {
        List<Movie> listMovies = new ArrayList();
        try (Connection con = conMan.getConnection()) {
            String query = "SELECT * FROM catMovie WHERE categoryID = ?";
            PreparedStatement statm = con.prepareStatement(query);
            statm.setInt(1, selectedCategory);
            ResultSet rs = statm.executeQuery();
            while (rs.next()) {
                int movieID = rs.getInt("movieID");
                String queryList = "SELECT * FROM Movie WHERE id = ?";
                PreparedStatement statmeList = con.prepareStatement(queryList);
                statmeList.setInt(1, movieID);
                ResultSet rsList = statmeList.executeQuery();
                Movie movie = new Movie();
                rsList.next();
                movie.setId(rsList.getInt("id"));
                movie.setName(rsList.getString("name"));
                movie.setFilelink(rsList.getString("filelink"));
                movie.setRating(rsList.getInt("rating"));
                listMovies.add(movie);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MoviesBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listMovies;
    }

    public List<Movie> filterMovies(String filteredMovies) {
        List<Movie> filteredAllMovies = new ArrayList();
        try (Connection con = conMan.getConnection()) {
            String query = "SELECT * FROM Movie WHERE name LIKE ? OR rating LIKE ?  ";
            PreparedStatement statm = con.prepareStatement(query);
            statm.setString(1, "%" + filteredMovies + "%");
            statm.setString(2, "%" + filteredMovies + "%");
            statm.setString(3, "%" + filteredMovies + "%");
            ResultSet rs = statm.executeQuery();
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setName(rs.getString("name"));
                movie.setRating(rs.getInt("rating"));
                movie.setFilelink(rs.getString("filelink"));
                movie.setLastview(rs.getDate("lastview"));

                filteredAllMovies.add(movie);
            }
            String sql = "SELECT * FROM Categories WHERE name LIKE ? ";
            PreparedStatement statmCategories = con.prepareStatement(sql);
            statmCategories.setString(1, "%" + filteredMovies + "%");
            ResultSet rsCategories = statmCategories.executeQuery();
            while (rsCategories.next()) {
                int categoryID = rsCategories.getInt("id");
                String sqlCat = "SELECT * FROM catMovie WHERE categoryID = ? ";
                PreparedStatement statmCat = con.prepareStatement(sqlCat);
                statmCat.setInt(1, categoryID);
                ResultSet rsCatMovie = statmCat.executeQuery();
                while (rsCatMovie.next()) {
                    int movieID = rsCatMovie.getInt("movieID");
                    String sqlMovieID = "SELECT * FROM Movie WHERE id = ? ";
                    PreparedStatement pstmtMovieID = con.prepareStatement(sqlMovieID);
                    pstmtMovieID.setInt(1, movieID);
                    ResultSet rsMoviesID = pstmtMovieID.executeQuery();
                    while (rsMoviesID.next()) {
                        Movie movie = new Movie();
                        movie.setId(rsMoviesID.getInt("id"));
                        movie.setName(rsMoviesID.getString("name"));
                        movie.setRating(rsMoviesID.getInt("rating"));
                        movie.setFilelink(rsMoviesID.getString("filelink"));
                        movie.setLastview(rsMoviesID.getDate("lastview"));
                        filteredAllMovies.add(movie);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MoviesBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filteredAllMovies;
    }

    public void removeMovie(int selectedMovie) {
        try (Connection con = conMan.getConnection()) {
            String sql = "DELETE FROM Movie WHERE id = ?";
            PreparedStatement statm = con.prepareStatement(sql);
            statm.setInt(1, selectedMovie);
            statm.execute();

            String query = "SELECT * FROM catMovie WHERE movieID = ? ";
            PreparedStatement statme = con.prepareStatement(query);
            statme.setInt(1, selectedMovie);

            ResultSet rs = statme.executeQuery();

            while (rs.next()) {
                String sql1 = "DELETE FROM catMovie WHERE movieID = ?";
                PreparedStatement pstmt1 = con.prepareStatement(sql1);
                pstmt1.setInt(1, selectedMovie);
                pstmt1.execute();
            }

        } catch (SQLException ex) {
            Logger.getLogger(MoviesDao.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    public void addAllMovies(Movie movie, List<Category> categories) {

        try (Connection con = conMan.getConnection()) {

            String sql = "INSERT INTO Movie (name, rating, filelink) VALUES(?, ?, ?,?)";
            PreparedStatement statme = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statme.setString(1, movie.getName());
            statme.setInt(2, movie.getRating());
            statme.setString(3, movie.getFilelink());
            statme.executeUpdate();
            ResultSet rs = statme.getGeneratedKeys();

            rs.next();
            movie.setId(rs.getInt(1));
            addMovietoCategory(movie, categories);

        } catch (SQLException ex) {
            Logger.getLogger(MoviesDao.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
    public void addMovietoCategory(Movie movie, List<Category> categories) {
        try (Connection con = conMan.getConnection()) {
            for (Category category : categories) {
                int categoryID = category.getId();
                int movieID = movie.getId();

                String statmeMovie = "INSERT INTO catMovie (categoryID, movieID) VALUES(?, ?)";
                PreparedStatement statme = con.prepareStatement(statmeMovie);
                statme.setInt(1, categoryID);
                statme.setInt(2, movieID);
                statme.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(MoviesDao.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
    public void setLastView(Movie movie) {
        try (Connection con = conMan.getConnection()) {

            String query = "UPDATE Movie SET lastview WHERE id=?";
            PreparedStatement statm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statm.setDate(1, movie.getLastview());
            statm.setInt(2, movie.getId());
            statm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(MoviesDao.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
    public int checkForName(Movie movie) {
        int counter = 0;
        try (Connection con = conMan.getConnection()) {

            String query = "SELECT * FROM Movie WHERE name = ?";

            PreparedStatement statm = con.prepareStatement(query);
            statm.setString(1, movie.getName());

            ResultSet rs = statm.executeQuery();
            while (rs.next()) {
                counter++;

            }
        } catch (SQLException ex) {
            Logger.getLogger(MoviesBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return counter;

    }

    public List<Movie> loadMovie() {

        List<Movie> allMovies = new ArrayList();

        try (Connection con = conMan.getConnection()) {

            String query = "SELECT * FROM Movie";
            PreparedStatement statm = con.prepareStatement(query);

            ResultSet rs = statm.executeQuery();
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setName(rs.getString("name"));
                movie.setRating(rs.getInt("rating"));
                movie.setFilelink(rs.getString("filelink"));
                movie.setLastview(rs.getDate("lastview"));

                allMovies.add(movie);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MoviesBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allMovies;
    }
}
