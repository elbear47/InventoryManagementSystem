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
        /*
        //Populate Products table with sample data
        System.out.println(ListOfProducts.product.add(new Product(1,"bike", 5, 150.0)));
        //Product p1 = new Product(1,"bike", 5, 150.0);
        Product p2 = new Product(2,"car", 20, 850.0);
        Product p3 = new Product(3,"computer", 100, 200.0);
        
       // Inventory.addProducts(p1);
        Inventory.addProducts(p2);
        Inventory.addProducts(p3);
   
    */
        launch(args);
    }
    
}
