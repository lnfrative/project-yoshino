/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class MainController {
    @FXML
    private ListView albumes;
    
    public void createAlbum() {
        albumes.getItems().add("hellow world");
    }
    
    public void removeAlbum() {
        int selectedIndex = albumes.getSelectionModel().getSelectedIndex();
        
        if (selectedIndex != -1) {
            albumes.getItems().remove(selectedIndex);
        }
    }
}
