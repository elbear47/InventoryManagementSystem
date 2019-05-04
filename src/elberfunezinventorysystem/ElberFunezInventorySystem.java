/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package elberfunezinventorysystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;
import model.Product;

/**
 *
 * @author elber
 */
public class ElberFunezInventorySystem extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
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
        
    
        launch(args);
    }
    
}
