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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import static model.Inventory.getAllParts;
import static model.Inventory.getallProducts;
import model.Outsourced;
import model.Part;
import model.Product;


/**
 *
 * @author elber
 */
public class MainMenuController implements Initializable {
    
    
    // TableViews 
   @FXML private TableView<Product> productsTableView;
   @FXML private TableView<Part> partsTableView;

   // Table columns for Parts
   @FXML private TableColumn<Part, Integer> partIdCol;
   @FXML private TableColumn<Part, String> partNameCol;
   @FXML private TableColumn<Part, Integer> partInvLevelCol;
   @FXML private TableColumn<Part, Double> partPriceCol;
   //Table Columns for Products 
    @FXML private TableColumn<Product, Integer> productIdCol;
    @FXML private TableColumn<Product, String> productNameCol;
    @FXML private TableColumn<Product, Integer> productInvLevelCol;
    @FXML private TableColumn<Product, Double> productPriceCol;
 
    //Search fields
    @FXML private TextField searchPartTxt;
    @FXML private TextField searchProductTxt;
    //Buttons
    @FXML private Button ModifyProductButton;
    @FXML private Button ModifyPartButton;
    
    static boolean entered; 
    FilteredList<Product> filteredProductData = new FilteredList<>(Inventory.getallProducts(), e -> true);
    FilteredList<Part> filteredPartData = new FilteredList<>(Inventory.getAllParts(), e -> true);

    
 
    @FXML
    void onActionSearchPart(ActionEvent event) {
      this.searchPartTxt = searchPartTxt;
        String x = searchPartTxt.getText();
        filteredPartData.setPredicate((Predicate<? super Part>) (Part part)->{
            if(x.equals("")|| x == null){
                return true;
            }
            else if(part.getName().equalsIgnoreCase(x)|| new Integer(part.getPartID()).toString().equals(x)){
            return true;
        }
            
       
        return false;
        });
        SortedList sortedProducts = new SortedList(filteredPartData);
        sortedProducts.comparatorProperty().bind(partsTableView.comparatorProperty());
        partsTableView.setItems(sortedProducts);

    }

    @FXML
    void onActionDisplayAddPartsView(ActionEvent event) throws IOException {
        Stage stage;
        Parent scene;
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPartMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionDisplayModifyPartsView(ActionEvent event) throws IOException {

        //declare variables to switch scenes
        Part partToModify = partsTableView.getSelectionModel().getSelectedItem();
        if (partToModify != null) {
            Stage stage;
            Parent root;

            stage = (Stage) ModifyPartButton.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ModifyPartMenu.fxml"));
            root = loader.load();
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            stage.show();
            ModifyPartMenuController controller = loader.getController();
            controller.setPart(partToModify);
        } else {
            Alert newAlert = new Alert(Alert.AlertType.ERROR);
            newAlert.setTitle("Error In Application");
            newAlert.setHeaderText("No Part Selected To Modify");
            newAlert.setContentText("Please Select a Part To modify");
            newAlert.showAndWait();
        }
    }

    @FXML
    void onActionDeletePart(ActionEvent event) {
       Part partToDelete = partsTableView.getSelectionModel().getSelectedItem();
        if (partToDelete != null) {
            Alert newAlert = new Alert(Alert.AlertType.CONFIRMATION);
            newAlert.setTitle("Confirm Delete");
            newAlert.setHeaderText("Are You Sure You want to Delete Part?");
            newAlert.setContentText("Click Ok if you want to delete");
            Optional<ButtonType> result = newAlert.showAndWait();
            if (result.get() == ButtonType.OK) 
            {
            partsTableView.getItems().removeAll(partsTableView.getSelectionModel().getSelectedItem());
                //updatePartsTableView();
            }
            
        } else {
            Alert newAlert = new Alert(Alert.AlertType.ERROR);
            newAlert.setTitle("Error When Trying To Delete");
            newAlert.setHeaderText("Application Could not Delete Anything");
            newAlert.setContentText("Please Select a Part to Delete");
            newAlert.showAndWait();
        }
    }
    @FXML
    void onActionClearSearchProduct(ActionEvent event) {
        searchProductTxt.setText("");
        productsTableView.setItems(Inventory.getallProducts());

    }
    @FXML
    void onActionClearSearchPart(ActionEvent event) {
        searchPartTxt.setText("");
        partsTableView.setItems(Inventory.getAllParts());
    }
    @FXML
    void onActionSearchProduct(ActionEvent event) {
  
           this.searchProductTxt = searchProductTxt;
        String x = searchProductTxt.getText();
        filteredProductData.setPredicate((Predicate<? super Product>) (Product product)->{
            if(x.equals("")|| x == null){
                return true;
            }
            else if(product.getName().contains(x)|| new Integer(product.getProductID()).toString().equals(x)){
            return true;
        }
        return false;
        });
        SortedList sortedProducts = new SortedList(filteredProductData);
        sortedProducts.comparatorProperty().bind(productsTableView.comparatorProperty());
        productsTableView.setItems(sortedProducts);
    }

    @FXML
    void onActionDisplayAddProductsView(ActionEvent event) throws IOException {

        Stage stage;
        Parent scene;
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProductMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionDisplayModifyProductsView(ActionEvent event) throws IOException {
        //declare variables to switch scenes
        Stage stage;
        Parent root;
        stage = (Stage) ModifyProductButton.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ModifyProductMenu.fxml"));
        root = loader.load();
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
        stage.show();
        ModifyProductMenuController controller = loader.getController();
        Product productToModify = productsTableView.getSelectionModel().getSelectedItem();
        controller.setProduct(productToModify);

    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        
        Product productToDelete = productsTableView.getSelectionModel().getSelectedItem();
        if(productToDelete != null){
            Alert newAlert = new Alert(Alert.AlertType.CONFIRMATION);
        newAlert.setTitle("Confirm Delete");
        newAlert.setHeaderText("Are You Sure You want to Delete this Product?");
        newAlert.setContentText("Click Ok if you want to delete");
        Optional<ButtonType> result = newAlert.showAndWait();
            if (result.get() == ButtonType.OK) 
            {
            productsTableView.getItems().removeAll(productsTableView.getSelectionModel().getSelectedItem());

            }
        }
        else{
            Alert newAlert = new Alert(Alert.AlertType.ERROR);
            newAlert.setTitle("Error When Trying To Delete");
            newAlert.setHeaderText("Application Could not Delete Anything");
            newAlert.setContentText("Please Select a Product to Delete");
            newAlert.showAndWait();
        }
    }

    @FXML
    void onActionExit(ActionEvent event) {
        Alert newAlert = new Alert(Alert.AlertType.CONFIRMATION);
        newAlert.setTitle("Confirm Exit");
        newAlert.setHeaderText("Are You Sure You want to Exit?");
        newAlert.setContentText("Click Ok if you want to exit");
        Optional<ButtonType> result = newAlert.showAndWait();
            if (result.get() == ButtonType.OK) 
            {
                   System.exit(0);

            }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productIdCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        productsTableView.setItems(productList);
        partsTableView.setItems(Inventory.getAllParts());
        InHouse newinhousePart1 = new InHouse();
        
        newinhousePart1.setPartID(1);
        newinhousePart1.setName("Gear");
        newinhousePart1.setInStock(10);
        newinhousePart1.setPrice(100.00);
        newinhousePart1.setMax(10);
        newinhousePart1.setMin(1);
        newinhousePart1.setMachineID(1001);
        
        Inventory.addParts(newinhousePart1);
        
        InHouse newinhousePart2 = new InHouse();
        
        newinhousePart2.setPartID(2);
        newinhousePart2.setName("bolt");
        newinhousePart2.setInStock(1000);
        newinhousePart2.setPrice(0.50);
        newinhousePart2.setMax(2000);
        newinhousePart2.setMin(500);
        newinhousePart2.setMachineID(1002);
        
        Inventory.addParts(newinhousePart2);
        
         
         Outsourced newoutsourcedpart1 = new Outsourced();
         
        newoutsourcedpart1.setPartID(3);
        newoutsourcedpart1.setName("Wheel");
        newoutsourcedpart1.setInStock(35);
        newoutsourcedpart1.setPrice(60.00);
        newoutsourcedpart1.setMax(50);
        newoutsourcedpart1.setMin(10);
        newoutsourcedpart1.setCompanyName("Huffy");
        
        Inventory.addParts(newoutsourcedpart1);
         */
        
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
        
        // Need the boolean so that they do not regenerate deleted Products or Parts
        if(!entered){
            entered=true;
             Product p1 = new Product(1,"bike", 5, 150.0);
             Product p2 = new Product(2,"car", 20, 850.0);
             Product p3 = new Product(3,"computer", 100, 200.0);
        
            Inventory.addProducts(p1);
            Inventory.addProducts(p2);
            Inventory.addProducts(p3);
         //Populate Parts table with inhouse and outsourced parts
        InHouse newinhousePart1 = new InHouse();
        
        newinhousePart1.setPartID(1);
        newinhousePart1.setName("Gear");
        newinhousePart1.setInStock(10);
        newinhousePart1.setPrice(100.00);
        newinhousePart1.setMax(10);
        newinhousePart1.setMin(1);
        newinhousePart1.setMachineID(1001);
        
        Inventory.addParts(newinhousePart1);
        
        InHouse newinhousePart2 = new InHouse();
        
        newinhousePart2.setPartID(2);
        newinhousePart2.setName("bolt");
        newinhousePart2.setInStock(1000);
        newinhousePart2.setPrice(0.50);
        newinhousePart2.setMax(2000);
        newinhousePart2.setMin(500);
        newinhousePart2.setMachineID(1002);
        
        Inventory.addParts(newinhousePart2);
        
         
         Outsourced newoutsourcedpart1 = new Outsourced();
         
        newoutsourcedpart1.setPartID(3);
        newoutsourcedpart1.setName("Wheel");
        newoutsourcedpart1.setInStock(35);
        newoutsourcedpart1.setPrice(60.00);
        newoutsourcedpart1.setMax(50);
        newoutsourcedpart1.setMin(10);
        newoutsourcedpart1.setCompanyName("Huffy");
        
        Inventory.addParts(newoutsourcedpart1);
         
        }
        
        
    }

}
