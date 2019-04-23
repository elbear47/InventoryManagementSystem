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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.Product;

/**
 * FXML Controller class
 *
 * @author elber
 */
public class AddProductMenuController implements Initializable {
  Stage stage;
    Parent scene;
    
    @FXML
    private TextField searchProductTxt;

    @FXML
    private RadioButton inHouseRBtn;

    @FXML
    private TableColumn<Product, Integer> productIdCol;

    @FXML
    private TextField productPriceTxt;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, Integer> productInvLevelCol;

    @FXML
    private TextField productMaxTxt;

    @FXML
    private RadioButton outsourcedRBtn;

    @FXML
    private TextField productCompanyNameTxt;

    @FXML
    private ToggleGroup ToggleGroup;

    @FXML
    private TextField productMinTxt;

    @FXML
    private TextField productIdTxt;

    @FXML
    private TextField productNameTxt;

    @FXML
    private TableColumn<Product, Double> productPriceCol;

    @FXML
    private TextField productInvLevelTxt;

    @FXML
    void onActionSearchProduct(ActionEvent event) {

    }

    @FXML
    void onActionAddProduct(ActionEvent event) {

    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {

    }

    @FXML
    void onActionSaveProductChanges(ActionEvent event) {

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
