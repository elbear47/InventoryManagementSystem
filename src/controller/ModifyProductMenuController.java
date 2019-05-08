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
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
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
import model.Inventory;
import static model.Inventory.getAllParts;
import model.Part;
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
    @FXML private TableView<Part> allPartsInInventory;
    @FXML private TableView<Part> updatedPartsTableView;
    //Observable List
    private ObservableList<Part> currentParts = FXCollections.observableArrayList();
   
    // Columns for Top table (current Products)
    @FXML private TableColumn<Part, Integer> partIdCol;
    @FXML private TableColumn<Part, String> partNameCol;
    @FXML private TableColumn<Part, Integer> partInvLevelCol;
    @FXML private TableColumn<Part, Double> partPriceCol;
    
    // Columns for bottom table (updated products)
    @FXML private TableColumn<Part, Integer> partIdUpdatedCol;
    @FXML private TableColumn<Part, String> partNameUpdatedCol;
    @FXML private TableColumn<Part, Integer> partInvLevelUpdatedCol;
    @FXML private TableColumn<Part, Double> partPriceUpdatedCol;
    
    // search bar
    @FXML private TextField searchPartTxt;
    // TextFields
    @FXML private TextField productIdTxt;
    @FXML private TextField productNameTxt;
    @FXML private TextField productInvLevelTxt;
    @FXML private TextField productPriceTxt;
    @FXML private TextField productMaxTxt;
    @FXML private TextField productMinTxt;
    
    //variables
    private Product productSelectedToModify;
    FilteredList<Part> filteredPartData = new FilteredList<>(Inventory.getAllParts(), e -> true);


    @FXML
    void onActionSearchParts(ActionEvent event) {
        this.searchPartTxt = searchPartTxt;
        String x = searchPartTxt.getText();
        filteredPartData.setPredicate((Predicate<? super Part>) (Part part)->{
            if(x.equals("")|| x == null){
                return true;
            }
            else if(part.getName().contains(x)|| new Integer(part.getPartID()).toString().equals(x)){
            return true;
        }
        return false;
        });
        SortedList sortedProducts = new SortedList(filteredPartData);
        sortedProducts.comparatorProperty().bind(allPartsInInventory.comparatorProperty());
        allPartsInInventory.setItems(sortedProducts);
    }

    @FXML
    void onActionAddPart(ActionEvent event) {
        //allPartsInInventory.getSelectionModel().getSelectedItem();
         Part part = allPartsInInventory.getSelectionModel().getSelectedItem();
        currentParts.add(part);
        updateUpdatedPartsTableView();
        updateAllPartsInInventory();
        
    }

    @FXML
    void onActionDeletePart(ActionEvent event) {
         Part partToDelete = updatedPartsTableView.getSelectionModel().getSelectedItem();

      if (partToDelete != null) {
            Alert newAlert = new Alert(Alert.AlertType.CONFIRMATION);
            newAlert.setTitle("Confirm Delete");
            newAlert.setHeaderText("Are You Sure You want to Delete Part?");
            newAlert.setContentText("Click Ok if you want to delete");
            Optional<ButtonType> result = newAlert.showAndWait();
            if (result.get() == ButtonType.OK) 
            {
            updatedPartsTableView.getItems().removeAll(updatedPartsTableView.getSelectionModel().getSelectedItem());
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
    void onActionSaveProductChanges(ActionEvent event) throws IOException {
      
        this.productIdTxt = productIdTxt;
        this.productNameTxt = productNameTxt;
        this.productInvLevelTxt = productInvLevelTxt;
        this.productMinTxt = productMinTxt;
        this.productMaxTxt = productMaxTxt;
        this.productPriceTxt = productPriceTxt;
        
        productSelectedToModify.setProductID(Integer.parseInt(productIdTxt.getText()));
        productSelectedToModify.setName(productNameTxt.getText());
        productSelectedToModify.setInStock(Integer.parseInt(productInvLevelTxt.getText()));
        productSelectedToModify.setMax(Integer.parseInt(productMaxTxt.getText()));
        productSelectedToModify.setMin(Integer.parseInt(productMinTxt.getText()));
        productSelectedToModify.setPrice(Double.parseDouble(productPriceTxt.getText()));
        // Add it to current tableview
        
        productSelectedToModify.setAssociatedParts(updatedPartsTableView.getItems());
         // Back to main menu after save
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        
        
        
        
    }

      @FXML
    void onActionDisplayMainMenu(ActionEvent event) throws IOException {
        Alert newAlert = new Alert(Alert.AlertType.CONFIRMATION);
        newAlert.setTitle("Confirm Cancellation");
        newAlert.setHeaderText("Are You Sure You want to Cancel Modification?");
        newAlert.setContentText("Click Ok if you are sure");
        Optional<ButtonType> result = newAlert.showAndWait();
            if (result.get() == ButtonType.OK) 
            {
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
            }
    
    }
 
    
public void setProduct(Product product) {
        this.productSelectedToModify = product;
        updatedPartsTableView.setItems(product.getAssociatedParts()); 
         productSelectedToModify = product;
        productIdTxt.setText(Integer.toString(product.getProductID()));
        productNameTxt.setText(product.getName());
        productInvLevelTxt.setText(Integer.toString(product.getInStock()));
        productMinTxt.setText(Integer.toString(product.getMin()));
        productMaxTxt.setText(Integer.toString(product.getMax()));
        productPriceTxt.setText(Double.toString(product.getPrice()));
       
        
     }
    public void updateAllPartsInInventory() 
    {
        allPartsInInventory.setItems(getAllParts());
    
    }
    public void updateUpdatedPartsTableView() 
    {
        updatedPartsTableView.setItems(currentParts);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Bottom Table Set
         updatedPartsTableView.setItems(currentParts);
        partIdUpdatedCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameUpdatedCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvLevelUpdatedCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partPriceUpdatedCol.setCellValueFactory(new PropertyValueFactory<>("price"));
       //Top Table set
        allPartsInInventory.setItems(Inventory.getAllParts());
        
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        
        allPartsInInventory.getItems().addAll();
        
    }    

 
    
}
