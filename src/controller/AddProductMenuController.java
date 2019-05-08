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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
public class AddProductMenuController implements Initializable {
    Stage stage;
    Parent scene;
    
    //RADIOBUTTONS and togglegroup
    @FXML private ToggleGroup ToggleGroup;
    @FXML private RadioButton inHouseRBtn;
    @FXML private RadioButton outsourcedRBtn;
    //Textfields
    @FXML private TextField searchPartTxt;
    @FXML private TextField productInvLevelTxt;
    @FXML private TextField productPriceTxt;
    @FXML private TextField productMaxTxt;
    @FXML private TextField productCompanyNameTxt;
    @FXML private TextField productMinTxt;
    @FXML private TextField productIdTxt;
    @FXML private TextField productNameTxt;
    //tablecolumns for all parts in inventory
    @FXML private TableColumn<Product, Integer> partIdCol;
    @FXML private TableColumn<Product, String> partNameCol;
    @FXML private TableColumn<Product, Integer> partInvLevelCol;
    @FXML private TableColumn<Product, Double> partPriceCol;
    //tablecolumns for all parts associated w that product
    @FXML private TableColumn<Product, Integer> partAssociatedIdCol;
    @FXML private TableColumn<Product, String> partAssociatedNameCol;
    @FXML private TableColumn<Product, Integer> partAssociatedInvLevelCol;
    @FXML private TableColumn<Product, Double> partAssociatedPriceCol;
   //Tableviews
    //Top Table (will only be populated with products we have created)
    @FXML private TableView<Part>  allPartsInInventory;
    //Bottom table (This will store current products plus products added from above tables)
    @FXML private TableView<Part> partsProductUses;
    // this label will update when diff radio button is selected
    @FXML private Label companyNameLabel;
    //variables
    private boolean isInHouse;
    private int productID;
    FilteredList<Part> filteredPartData = new FilteredList<>(Inventory.getAllParts(), e -> true);
    private ObservableList<Part> currentParts = FXCollections.observableArrayList();
    
    
    
    @FXML
    void onActionSearchAllParts(ActionEvent event) {
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
    // this method will add product to the bottom table within the AddProductsView
    @FXML
    void onActionAddAssociatedPart(ActionEvent event) {
         Part part = allPartsInInventory.getSelectionModel().getSelectedItem();
        currentParts.add(part);
        updatepartsProductUsesTableView();
        updateAllPartsInInventory();
    }

    @FXML
    void onActionDeleteAssociatedPart(ActionEvent event) {
         Part partToDelete = partsProductUses.getSelectionModel().getSelectedItem();

      if (partToDelete != null) {
            Alert newAlert = new Alert(Alert.AlertType.CONFIRMATION);
            newAlert.setTitle("Confirm Delete");
            newAlert.setHeaderText("Are You Sure You want to Delete Part?");
            newAlert.setContentText("Click Ok if you want to delete");
            Optional<ButtonType> result = newAlert.showAndWait();
            if (result.get() == ButtonType.OK) 
            {
            partsProductUses.getItems().removeAll(partsProductUses.getSelectionModel().getSelectedItem());
            }
            
        } else {
            Alert newAlert = new Alert(Alert.AlertType.ERROR);
            newAlert.setTitle("Error When Trying To Delete");
            newAlert.setHeaderText("Application Could not Delete Anything");
            newAlert.setContentText("Please Select a Part to Delete");
            newAlert.showAndWait();
        }
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
        newProduct.setAssociatedParts(partsProductUses.getItems());
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
       Alert newAlert = new Alert(Alert.AlertType.CONFIRMATION);
        newAlert.setTitle("Confirm Cancellation");
        newAlert.setHeaderText("Are You Sure You want to Cancel Add Product?");
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
   public void updateAllPartsInInventory() 
    {
        allPartsInInventory.setItems(getAllParts());
    
    }
    public void updatepartsProductUsesTableView() 
    {
        partsProductUses.setItems(currentParts);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // For Bottom Table
        partsProductUses.setItems(currentParts);
        partAssociatedIdCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partAssociatedNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partAssociatedInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partAssociatedPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
       // For Top Table
        allPartsInInventory.setItems(Inventory.getAllParts());
       
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        
        allPartsInInventory.getItems().addAll();
        
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
