/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movies.GUI.Controler;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author Lukas
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button btnRemove2Category;
    @FXML
    private Button btnAddCategory;
    @FXML
    private TextField srcBar;
    @FXML
    private TableView<?> listTableMovies;
    @FXML
    private TableColumn<?, ?> collumTitle;
    @FXML
    private TableColumn<?, ?> collumImdb;
    @FXML
    private TableColumn<?, ?> collumPersonalsco;
    @FXML
    private TableColumn<?, ?> collumCategories;
    @FXML
    private Button btnAddMovie;
    @FXML
    private Button btnRemoveMovie;
    @FXML
    private Button btnFilter;
    @FXML
    private MediaView mv;
    @FXML
    private Button buttonMoviePause;
    @FXML
    private TableView<?> categoryList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buttonRemoveCategory(ActionEvent event) {
    }

    @FXML
    private void buttonAddCategory(ActionEvent event) {
    }

    @FXML
    private void searchBar(ActionEvent event) {
    }

    @FXML
    private void buttonAddMovie(ActionEvent event) {
    }

    @FXML
    private void buttonRemoveMovie(ActionEvent event) {
    }

    @FXML
    private void buttonFilter(ActionEvent event) {
    }

    @FXML
    private void buttonMoviePlay(ActionEvent event) {
    }

    @FXML
    private void buttonStop(ActionEvent event) {
    }

    @FXML
    private void buttonMoviePause(ActionEvent event) {
    }

    @FXML
    private void buttonScore(ActionEvent event) {
    }

    @FXML
    private void onMouseOver(MouseEvent event) {
    }

    int getSelectedCategoryID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void clearCategoryList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void loadCategories() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
