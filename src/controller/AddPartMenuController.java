/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.InHouse;
import model.Outsourced;
import model.Inventory;
import model.Part;

/**
 * FXML Controller class
 *
 * @author elber
 */
public class AddPartMenuController implements Initializable{
    //Radio Buttons
    @FXML private RadioButton inHouseRBtn;
    @FXML private RadioButton outsourcedRBtn;
    //textfields
    @FXML private TextField companyNameTxt;
    @FXML private TextField maxTxt;
    @FXML private TextField minTxt;
    @FXML private TextField partIdTxt;
    @FXML private TextField invTxt;
    @FXML private TextField priceTxt;
    @FXML private TextField nameTxt;
    //ToggleGroup
    @FXML private ToggleGroup ToggleGroup;
    //Labels
    @FXML private Label companyNameLabel;
    
    // Create Reference Variables
    Stage stage;
    Parent scene;
    // create partID, this will help us autogenerate them for our part screen
    private int partID;
    // create new part 
    private Part newpart;
    private Part selectedPart;
    // This variable will tell us whether inhouse or outsourced is selected
    private boolean isInHouse;
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        partID = Inventory.getPartIDCount();
        partIdTxt.setEditable(false);
        partIdTxt.setText(String.valueOf(partID));
        // Automatically load inhouse choice
        this.inHouseRBtn.setSelected(true);
        //handle it
         if(this.ToggleGroup.getSelectedToggle().equals(this.inHouseRBtn)){
           companyNameLabel.setText("Machine ID:");
           companyNameTxt.setPromptText("Machine ID");
       }
       if(this.ToggleGroup.getSelectedToggle().equals(this.outsourcedRBtn)){
           
           companyNameLabel.setText("Company Name:");
           companyNameTxt.setPromptText("Company Name");
       }
      
    }  
    
    
    @FXML void radioButtonChanged(ActionEvent event) throws IOException{
       if(this.ToggleGroup.getSelectedToggle().equals(this.inHouseRBtn)){
           isInHouse = true;
           companyNameLabel.setText("Machine ID:");
           companyNameTxt.setPromptText("Machine ID");
       }
       if(this.ToggleGroup.getSelectedToggle().equals(this.outsourcedRBtn)){
           isInHouse = false;
           companyNameLabel.setText("Company Name:");
           companyNameTxt.setPromptText("Company Name");
       }
    }
    

    @FXML
     void onActionSavePartDetails(ActionEvent event) throws IOException{
        partIdTxt = this.partIdTxt;
        nameTxt = this.nameTxt;
        invTxt = this.invTxt;
        priceTxt = this.priceTxt;
        
         InHouse newinhousePart = new InHouse();
         Outsourced newoutsourcedpart = new Outsourced();
         if(isInHouse ==true){
             // set txt fields equal to part fields(inhouse)
             
             newinhousePart.setPartID(Integer.parseInt(partIdTxt.getText()));
             newinhousePart.setName(nameTxt.getText());
             newinhousePart.setInStock(Integer.parseInt(invTxt.getText()));
             newinhousePart.setPrice(Double.parseDouble(priceTxt.getText()));
             
            Inventory.addParts(newinhousePart);
         // Back to main menu after save
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
         }
         else
             // set txt fields equal to part fields (outsourced)
            newoutsourcedpart.setPartID(Integer.parseInt(partIdTxt.getText()));
            newoutsourcedpart.setName(nameTxt.getText());
            newoutsourcedpart.setInStock(Integer.parseInt(invTxt.getText()));
            newoutsourcedpart.setPrice(Double.parseDouble(priceTxt.getText()));
            
            Inventory.addParts(newoutsourcedpart);
         
         // Back to main menu after save
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
      
        
    }   

    @FXML
    void onActionDisplayMainMenu(ActionEvent event) throws IOException {
        Alert newAlert = new Alert(Alert.AlertType.CONFIRMATION);
        newAlert.setTitle("Confirm Exit");
        newAlert.setHeaderText("Are You Sure You want to Exit?");
        newAlert.setContentText("Click Ok if you want to exit");
        Optional<ButtonType> result = newAlert.showAndWait();
            if (result.get() == ButtonType.OK) 
            {
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
            }
        
    }

    
}
