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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Product;

/**
 * FXML Controller class
 *
 * @author elber
 */
public class AddProductMenuController implements Initializable {
    Stage stage;
    Parent scene;
    
    //RADIOBUTTONS and togglegroup
    @FXML private ToggleGroup ToggleGroup;
    @FXML private RadioButton inHouseRBtn;
    @FXML private RadioButton outsourcedRBtn;
    //Textfields
    @FXML private TextField searchProductTxt;
    @FXML private TextField productInvLevelTxt;
    @FXML private TextField productPriceTxt;
    @FXML private TextField productMaxTxt;
    @FXML private TextField productCompanyNameTxt;
    @FXML private TextField productMinTxt;
    @FXML private TextField productIdTxt;
    @FXML private TextField productNameTxt;
    //tablecolumns
    @FXML private TableColumn<Product, Integer> productIdCol;
    @FXML private TableColumn<Product, String> productNameCol;
    @FXML private TableColumn<Product, Integer> productInvLevelCol;
    @FXML private TableColumn<Product, Double> productPriceCol;
   //Tableviews
    @FXML private TableView<Product> productsTableView;
    @FXML private TableView<Product> ProductsTableViewUpdated;
    // this label will update when diff radio button is selected
    @FXML private Label companyNameLabel;
    //variables
    private boolean isInHouse;
    private int productID;

    
    
    
    @FXML
    void onActionSearchProduct(ActionEvent event) {

    }
    // this method will add product to the bottom table within the AddProductsView
    @FXML
    void onActionAddProduct(ActionEvent event) {
        
    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {

    }
// this method will add product to main menu 
    @FXML
    void onActionSaveProductChanges(ActionEvent event) throws IOException {
        productIdTxt =  this.productIdTxt;
        productNameTxt = this.productNameTxt;
        productInvLevelTxt = this.productInvLevelTxt;
        productPriceTxt = this.productPriceTxt;
        // Create a new Product row which will store the data from the text fields
        Product newProduct = new Product();
        // Set each txt field to the Product and cast correct data type
        newProduct.setProductID(Integer.parseInt(productIdTxt.getText()));
        newProduct.setName(productNameTxt.getText());
        newProduct.setInStock(Integer.parseInt(productInvLevelTxt.getText()));
        newProduct.setPrice(Double.parseDouble(productIdTxt.getText()));
        
        // Add it to current tableview
        Inventory.addProducts(newProduct);
        
        // Clear txt field
        productIdTxt.clear();
        productNameTxt.clear();
        productInvLevelTxt.clear();
        productPriceTxt.clear();
        productCompanyNameTxt.clear();
        productMinTxt.clear();
        productMaxTxt.clear();
        // Back to main menu after save
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        ;
        
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
        
        productsTableView.setItems(Inventory.getallProducts());
       
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        productsTableView.getItems().addAll();
        
        productID = Inventory.getProductIDCount();
        productIdTxt.setEditable(false);
        productIdTxt.setText(String.valueOf(productID));
        // Automatically load inhouse choice
        this.inHouseRBtn.setSelected(true);
        //handle it
         if(this.ToggleGroup.getSelectedToggle().equals(this.inHouseRBtn)){
           companyNameLabel.setText("Machine ID:");
           productCompanyNameTxt.setPromptText("Machine ID");
       }
       if(this.ToggleGroup.getSelectedToggle().equals(this.outsourcedRBtn)){
           
           companyNameLabel.setText("Company Name:");
           productCompanyNameTxt.setPromptText("Company Name");
       }
    }    
    // This method will change to machine ID or company name depending on inhouse or outsourced radio selection
    @FXML void radioButtonChanged(ActionEvent event) throws IOException{
       if(this.ToggleGroup.getSelectedToggle().equals(this.inHouseRBtn)){
           companyNameLabel.setText("Machine ID:");
           productCompanyNameTxt.setPromptText("Machine ID");
       }
       if(this.ToggleGroup.getSelectedToggle().equals(this.outsourcedRBtn)){
           
           companyNameLabel.setText("Company Name:");
           productCompanyNameTxt.setPromptText("Company Name");
       }
    }

    
    
}
