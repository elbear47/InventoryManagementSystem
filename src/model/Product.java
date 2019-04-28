/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import javafx.collections.ObservableList;

/**
 *
 * @author elber
 */
public class Product {
    private ObservableList<Product> associatedProducts;
    private int productID;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;
    
    //Still need to add methods for associatedProduct
    // This class will be used to populate table with sample data 
    public Product(int productID, String name, int inStock,double price) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
    }
    // this class will be used to populate table with new data from AddProductMenu
    public Product() {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
    }


    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
    
    
    
    
}
