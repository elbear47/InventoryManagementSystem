/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Inventory;
import static model.Inventory.getallProducts;
import model.Product;

/**
 * FXML Controller class
 *
 * @author elber
 */
public class ModifyProductMenuController implements Initializable {
    Stage stage;
    Parent scene;
    //Tables
    @FXML private TableView<Product> currentProductsTableView;
    @FXML private TableView<Product> updatedProductsTableView;
   
    // Columns for Top table (current Products)
    @FXML private TableColumn<Product, Integer> productIdCol;
    @FXML private TableColumn<Product, String> productNameCol;
    @FXML private TableColumn<Product, Integer> productInvLevelCol;
    @FXML private TableColumn<Product, Double> productPriceCol;
    
    // Columns for bottom table (updated products)
    @FXML private TableColumn<Product, Integer> productIdUpdatedCol;
    @FXML private TableColumn<Product, String> productNameUpdatedCol;
    @FXML private TableColumn<Product, Integer> productInvLevelUpdatedCol;
    @FXML private TableColumn<Product, Double> productPriceUpdatedCol;
    
    // search bar
    @FXML private TextField searchProductTxt;
    // TextFields
    @FXML private TextField productIdTxt;
    @FXML private TextField productNameTxt;
    @FXML private TextField productInvLevelTxt;
    @FXML private TextField productPriceTxt;
    @FXML private TextField productMaxTxt;
    @FXML private TextField productMinTxt;
    
    //variables
    private Product product;
    
    

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
 
    
public void setProduct(Product product) {
        this.product = product;
        
        productIdTxt.setText(new Integer(product.getProductID()).toString());
        productNameTxt.setText(product.getName());
        productInvLevelTxt.setText(new Integer(product.getInStock()).toString());
        productMinTxt.setText(new Integer(product.getMin()).toString());
        productMaxTxt.setText(new Integer(product.getMax()).toString());
        productPriceTxt.setText(new Double(product.getPrice()).toString());
       
        
     }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    

 
    
}
