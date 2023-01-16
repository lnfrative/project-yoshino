/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
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
    
    @FXML
    private TextField genteField;
    
    public void onCreate() {
        String description = descriptionField.getText();
        String lugar = lugarField.getText();
        String fecha = fechaField.getText();
        String path = imageField.getText();
        String gente = genteField.getText();
        
        if (!description.isEmpty() && !lugar.isEmpty() && !fecha.isEmpty() && !path.isEmpty()) {
            Image image = new Image(path, description, lugar, fecha, mainController.getImages(), albumId, gente);
            
            mainController.addImage(image);
            
            destroy();
        }
    }
    
    public void onCancel() {
        destroy();
    }
    
    public void onBuscarImagen() {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(container.getScene().getWindow());
        
        try {
            imageField.setText(selectedFile.toURI().toURL().toExternalForm());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
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
