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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.Part;

/**
 * FXML Controller class
 *
 * @author elber
 */
public class AddPartMenuController implements Initializable{
    //declare variables
      @FXML
    private RadioButton inHouseRBtn;

    @FXML
    private RadioButton outsourcedRBtn;

    @FXML
    private TextField companyNameTxt;
    
    @FXML
    private TextField maxTxt;

    @FXML
    private TextField minTxt;

    @FXML
    private ToggleGroup ToggleGroup;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField partIdTxt;

    @FXML
    private TextField invTxt;

    @FXML
    private TextField priceTxt;
    // Create Reference Variables
    Stage stage;
    Parent scene;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
    }    

    

    @FXML
     void onActionSavePartDetails(ActionEvent event){
        
    }   

    @FXML
    void onActionDisplayMainMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    

    
}
