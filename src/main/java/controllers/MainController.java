/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import models.*;

public class MainController {
    private ArrayList<Album> albumes = new ArrayList();
    private ArrayList<Image> images = new ArrayList();
    
    @FXML
    private ListView albumesListView;
    
    public void initialize() {
        System.out.println("initialized.");
    }
    
    public void openWindowToCreateAlbum() {
        
    }
    
    public void createAlbum() {
        albumesListView.getItems().add("hellow world");
    }
    
    public void removeAlbum() {
        int selectedIndex = albumesListView.getSelectionModel().getSelectedIndex();
        
        if (selectedIndex != -1) {
            albumesListView.getItems().remove(selectedIndex);
        }
    }
}
