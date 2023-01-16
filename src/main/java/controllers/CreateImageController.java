/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class CreateImageController {
    private MainController mainController;
    
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
        
    }
    
    public void onCancel() {
        destroy();
    }
    
    public void onBuscarImagen() {
        
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    
    private void destroy() {
        container.getScene().getWindow().hide();
    }
}
