/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author elber
 */
public class InHouse extends Part{
    private SimpleIntegerProperty machineID;
    public InHouse(){
        super();
        
    }

    public int getMachineID() {
        return this.machineID.get();
    }

    public void setMachineID(int machineID) {
        this.machineID.set(machineID); 
    }

    
    
}
