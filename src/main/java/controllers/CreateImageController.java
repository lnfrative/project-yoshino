/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import models.Image;

public class CreateImageController {
    private MainController mainController;
    private int albumId;
    
    @FXML
    private Pane container;
    
    @FXML
    private TextField descriptionField;
    
    @FXML
    private TextField lugarField;
    
    @FXML
    private TextField fechaField;
    
    @FXML
    private TextField imageField;
    
    public void onCreate() {
        String description = descriptionField.getText();
        String lugar = lugarField.getText();
        String fecha = fechaField.getText();
        String path = imageField.getText();
        
        if (!description.isEmpty() && !lugar.isEmpty() && !fecha.isEmpty() && !path.isEmpty()) {
            Image image = new Image(path, description, lugar, fecha, new ArrayList(), albumId);
            
            mainController.addImage(image);
            
            destroy();
        }
    }
    
    public void onCancel() {
        destroy();
    }
    
    public void onBuscarImagen() {
        
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }
   
    
    
    private void destroy() {
        container.getScene().getWindow().hide();
    }
}
