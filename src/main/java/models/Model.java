/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.ArrayList;
import java.util.Random;

import java.io.Serializable;

public class Model implements Serializable {
    private Integer id = null;
    
    public Model(ArrayList<? extends Model> models) {
        this.id = this.generateUniqueId(models);
    }
    
    private Integer generateUniqueId(ArrayList<? extends Model> models) {
        Random random = new Random();
        Integer uniqueId = null;
        
        while(uniqueId == null) {
            boolean idInUse = false;
            Integer id = random.nextInt();
            
            for (Model model : models) {
                if (model.id.equals(id)) {
                    idInUse = true;
                }
            }
            
            if (!idInUse) {
                uniqueId = id;
            }
        }
        
        return uniqueId;
    }
    
    public Integer getId() {
        return id;
    }
}
