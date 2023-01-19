/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import utils.*;

public class UpdateSearchParametersController {
    private MainController mainController;
    
    @FXML
    private Pane container;
    
    @FXML
    private TextField rangoFechasField;
    
    @FXML
    private TextField lugaresField;
    
    @FXML
    private TextField personasField;
    
    public void onUpdate() {
        SearchParameters searchParameters = this.mainController.getSearchParameters();
        
        searchParameters.setRangoFechas(rangoFechasField.getText());
        searchParameters.setLugares(lugaresField.getText());
        searchParameters.setPersonas(personasField.getText());
        
        destroy();
    }
    
    public void onCancel() {
        destroy();
    }
    
    private void destroy() {
        container.getScene().getWindow().hide();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    
    public void build() {
        SearchParameters searchParameters = this.mainController.getSearchParameters();
        
        rangoFechasField.setText(searchParameters.getRangoFechas());
        lugaresField.setText(searchParameters.getLugares());
        personasField.setText(searchParameters.getPersonas());
    }
}
