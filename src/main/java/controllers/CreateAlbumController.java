/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import models.Album;

public class CreateAlbumController {
    private MainController mainController = null;
    
    @FXML
    private Pane container;
    
    @FXML
    private TextField nameField;
    
    @FXML
    private TextField descriptionField;
    
    public void onCreate() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        
        if (!name.isEmpty() && !description.isBlank()) {
            Album album = new Album(name, description, mainController.getAlbumes());
            
            mainController.addAlbum(album);

            destroy();
        }
    }
    
    public void onCancel() {
        destroy();
    }
    
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    
    private void destroy() {
        container.getScene().getWindow().hide();
    }
}
