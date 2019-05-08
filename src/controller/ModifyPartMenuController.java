/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import static com.sun.deploy.util.ReflectionUtil.instanceOf;
import static com.sun.deploy.util.ReflectionUtil.instanceOf;
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
import model.Inventory;
import model.Outsourced;
import model.Part;

/**
 * FXML Controller class
 *
 * @author elber
 */
public class ModifyPartMenuController implements Initializable {

    Stage stage;
    Parent scene;
    // Textfields
    @FXML private TextField partPriceTxt;
    @FXML private TextField partMaxTxt;
    @FXML private TextField partInvLevelTxt;
    @FXML private TextField partCompanyNameTxt;
    @FXML private TextField partMinTxt;
    @FXML private TextField partIdTxt;
    @FXML private TextField partNameTxt;
    //Labels
    @FXML private Label companyNameLabel;
    //ToggleGroup
    @FXML private ToggleGroup ToggleGroup;
    //Radio Buttons
    @FXML private RadioButton inhouseRBtn;
    @FXML private RadioButton outsourcedRBtn;
    // Variables
    private Part part;
    private boolean isInHouse;
    private InHouse inhousePartSelectedToModify;
    private Outsourced outsourcedPartSelectedToModify;
    

    @FXML
    void onActionSaveModifiedChanges(ActionEvent event) throws IOException {
        
        this.partIdTxt = partIdTxt;
        this.partNameTxt = partNameTxt;
        this.partInvLevelTxt = partInvLevelTxt;
        this.partMaxTxt = partMaxTxt;
        this.partMinTxt = partMinTxt;
        this.partCompanyNameTxt = partCompanyNameTxt;
        this.partPriceTxt = partPriceTxt;
// When the toggleGroup is for inhouse then set text fields to override selected row of type Part
         if(isInHouse ==true ){
             
             // set txt fields equal to part fields(inhouse)
             inhousePartSelectedToModify.setPartID(Integer.parseInt(partIdTxt.getText()));
             inhousePartSelectedToModify.setName(partNameTxt.getText());
             inhousePartSelectedToModify.setInStock(Integer.parseInt(partInvLevelTxt.getText()));
             inhousePartSelectedToModify.setPrice(Double.parseDouble(partPriceTxt.getText()));
             
         // Back to main menu after save
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        
         }
         else
             // set txt fields equal to part fields (outsourced)
            
            outsourcedPartSelectedToModify.setPartID(Integer.parseInt(partIdTxt.getText()));
            outsourcedPartSelectedToModify.setName(partNameTxt.getText());
            outsourcedPartSelectedToModify.setInStock(Integer.parseInt(partInvLevelTxt.getText()));
            outsourcedPartSelectedToModify.setPrice(Double.parseDouble(partPriceTxt.getText()));
            
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
    // When you select a part and hit Modify it will load text fields with info from main table
    public void setPart(Part part) {
        this.part = part;
        if(part instanceof InHouse){
            inhousePartSelectedToModify = (InHouse) part;
            this.ToggleGroup.getSelectedToggle().equals(this.isInHouse);
            isInHouse = true;
            this.inhouseRBtn.setSelected(true);
        partIdTxt.setText(new Integer(part.getPartID()).toString());
        partNameTxt.setText(part.getName());
        partInvLevelTxt.setText(new Integer(part.getInStock()).toString());
        partCompanyNameTxt.setText(new Integer(part.getMachineID()).toString());
        partMinTxt.setText(new Integer(part.getMin()).toString());
        partMaxTxt.setText(new Integer(part.getMax()).toString());
        partPriceTxt.setText(new Double(part.getPrice()).toString());
        }
        else{
            isInHouse = false;
            outsourcedPartSelectedToModify = (Outsourced) part;
            this.outsourcedRBtn.setSelected(true);
            companyNameLabel.setText("Company Name:");
        partIdTxt.setText(new Integer(part.getPartID()).toString());
        partNameTxt.setText(part.getName());
        partInvLevelTxt.setText(new Integer(part.getInStock()).toString());
        partCompanyNameTxt.setText(part.getCompanyName());
        partMinTxt.setText(new Integer(part.getMin()).toString());
        partMaxTxt.setText(new Integer(part.getMax()).toString());
        partPriceTxt.setText(new Double(part.getPrice()).toString());
        }
     }
     @FXML void radioButtonChanged(ActionEvent event) throws IOException{
       if(this.ToggleGroup.getSelectedToggle().equals(this.inhouseRBtn)){
           isInHouse = true;
           companyNameLabel.setText("Machine ID:");
           //companyNameLabel.setPromptText("Machine ID");
       }
       if(this.ToggleGroup.getSelectedToggle().equals(this.outsourcedRBtn)){
           isInHouse = false;
           companyNameLabel.setText("Company Name:");
           //companyNameLabel.setPromptText("Company Name");
       }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Automatically load inhouse choice
        this.inhouseRBtn.setSelected(true);
       
        //handle it
         if(this.ToggleGroup.getSelectedToggle().equals(this.inhouseRBtn)){
           companyNameLabel.setText("Machine ID:");
           //partCompanyNameTxt.setPromptText("Machine ID");
       }
       if(this.ToggleGroup.getSelectedToggle().equals(this.outsourcedRBtn)){
           
           companyNameLabel.setText("Company Name:");
         //  partCompanyNameTxt.setPromptText("Company Name");
    }    
                
    }

    
}
