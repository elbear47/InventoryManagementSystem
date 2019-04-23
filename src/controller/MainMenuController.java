/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
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
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;


/**
 *
 * @author elber
 */
public class MainMenuController implements Initializable {
    //declare variables to switch scenes
    Stage stage;
    Parent scene;
    // Variables used for Scene
   @FXML private TableView<Product> productsTableView;
   @FXML private TableView<Part> partsTableView;
    
   
     @FXML
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    private TextField searchPartTxt;

    @FXML
    private TableColumn<Part, Integer> partInvLevelCol;

    @FXML
    private TextField searchProductTxt;

    @FXML
    private TableColumn<Part, Integer> partIdCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Product, Integer> productIdCol;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, Integer> productInvLevelCol;

    @FXML
    private TableColumn<Product, Double> productPriceCol;
    
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> filteredProducts = FXCollections.observableArrayList();
    private static ObservableList<Part> filteredParts = FXCollections.observableArrayList();

 
      @FXML
    void onActionSearchPart(ActionEvent event) {
        
    String searchItem= searchPartTxt.getText();
    boolean found=false;
    try{
        int itemNumber=Integer.parseInt(searchItem);
        for(Part p: allParts){
            if(p.getPartID()==itemNumber){
                System.out.println("This is part "+ itemNumber);
                found=true;

                   filteredParts = partsTableView.getItems();
                   filteredParts.add(p);

                    
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
                partsTableView.setItems(filteredParts);
            
            }
            
        }
            if (found==false){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Part not found");

            alert.showAndWait();
        }
    }
    catch(NumberFormatException e){
        for(Part p: allParts){
            if(p.getName().equals(searchItem)){
                System.out.println("This is part "+p.getPartID());
                found=true;

               filteredParts = partsTableView.getItems();
                filteredParts.add(p);
  
                partIdCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
                partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                partInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
                partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
                    }
            
        }
            if (found==false){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error");
            alert.setContentText("Part not found");

            alert.showAndWait();
        }
    }

    }

    @FXML
    void onActionDisplayAddPartsView(ActionEvent event) throws IOException {
          stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPartMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionDisplayModifyPartsView(ActionEvent event) throws IOException {
           stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ModifyPartMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionDeletePart(ActionEvent event) {
        partsTableView.getItems().removeAll(partsTableView.getSelectionModel().getSelectedItem());
    }

    @FXML
    void onActionSearchProduct(ActionEvent event) {

    }

    @FXML
    void onActionDisplayAddProductsView(ActionEvent event) throws IOException {
           stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProductMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionDisplayModifyProductsView(ActionEvent event) throws IOException {
           stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ModifyProductMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        productsTableView.getItems().removeAll(productsTableView.getSelectionModel().getSelectedItem());
    }

    @FXML
    void onActionExit(ActionEvent event) {
         System.exit(0);

    }


    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // get 
        productsTableView.setItems(Inventory.getallProducts());
        partsTableView.setItems(Inventory.getAllParts());
        
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        /*
        // Populate Table with Sample Data
        Product p1 = new Product(1,"bike", 5, 150.0);
        Product p2 = new Product(2,"car", 20, 850.0);
        Product p3 = new Product(3,"computer", 100, 200.0);
        
        Inventory.addProducts(p1);
        Inventory.addProducts(p2);
        Inventory.addProducts(p3);
        
        Part part1 = new Part(1,"Wheel", 500, 30.0) {};
        Part part2 = new Part(2,"battery", 50, 120.0) {};
        Part part3 = new Part(3,"Hard Drive", 36, 85.0) {};
        
        Inventory.addParts(part1);
        Inventory.addParts(part2);
        Inventory.addParts(part3);
        */
    }    
    
}
