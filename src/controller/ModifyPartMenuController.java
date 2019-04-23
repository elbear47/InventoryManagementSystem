/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author elber
 */
public class ModifyPartMenuController implements Initializable {

    Stage stage;
    Parent scene;

     @FXML
    private TextField partPriceCol;

    @FXML
    private RadioButton inhouseRBtn;

    @FXML
    private RadioButton outsourcedRBtn;

    @FXML
    private TextField partMaxCol;

    @FXML
    private TextField partInvLevelCol;

    @FXML
    private ToggleGroup ToggleGroup;

    @FXML
    private TextField partCompanyNameCol;

    @FXML
    private TextField partMinCol;

    @FXML
    private TextField partIdCol;

    @FXML
    private TextField partNameCol;

    @FXML
    void onActionSaveModifiedChanges(ActionEvent event) {

    }

     @FXML
    void onActionDisplayMainMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    
}
