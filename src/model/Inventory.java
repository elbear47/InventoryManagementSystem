/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 *
 * @author elber
 */
//Dont really know if this uses inheritance yet just guessin
public class Inventory{
    // create observable list for parts and products
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> filteredProducts = FXCollections.observableArrayList();
    private static ObservableList<Part> filteredParts = FXCollections.observableArrayList();
    private static int partIDCount = 0;
    private static int productIDCount = 0;
    public static boolean alreadyExecuted = false;
    
    
    public static void addProducts(Product product){
        allProducts.add(product);
    } 
   public static ObservableList<Product> getallProducts(){
       return allProducts;
   }
   public static void addParts(Part part){
       allParts.add(part);
   }
   public static ObservableList<Part> getAllParts(){
       return allParts;
   }
   
   // These methods will help auto generate a new part id
   public static int getPartIDCount() {
        partIDCount++;
        return partIDCount;
    }
    
    public static int cancelPartIDCount() {
        partIDCount--;
        return partIDCount;
    }
    // These methods will help auto generate a new product id
    public static int getProductIDCount() {
        productIDCount++;
        return productIDCount;
    }
    
   
    public static int cancelProductIDCount() {
        productIDCount--;
        return productIDCount;
    }

  
   }

  //Listen for changes in allProducts
  // Whenever allProducts changes we must also update the filteredproducts
   

