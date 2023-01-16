/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.File;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import models.Album;
import models.Image;

public class UpdateImageController {
    final private BooleanProperty updatedProperty = new SimpleBooleanProperty();
    
    private MainController mainController;
    
    private Image image;
    
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
    
    @FXML
    private ComboBox<Album> albumComboBox;
    
    public void build() {
        if (image != null) {
            descriptionField.setText(image.getDescription());
            lugarField.setText(image.getLugar());
            fechaField.setText(image.getFecha());
            genteField.setText(image.getGente());
            imageField.setText(image.getPath());
            

            
            albumComboBox.setItems(FXCollections.observableArrayList(mainController.getAlbumes()));
            
            albumComboBox.setConverter(new StringConverter<Album>() {
                @Override
                public String toString(Album album) {
                    if (album != null) {
                        return album.getName();
                    }
                    return null;
                }
                
                @Override
                public Album fromString(String string) {
                    return null;
                }
            });
            
            albumComboBox.getSelectionModel().select(mainController.getAlbumesListView().getSelectionModel().getSelectedIndex());
        }
    }
    
    public void oUpdate() {
        image.setAlbumId(albumComboBox.getSelectionModel().getSelectedItem().getId());
        image.setDescription(descriptionField.getText());
        image.setFecha(fechaField.getText());
        image.setGente(genteField.getText());
        image.setLugar(lugarField.getText());
        image.setPath(imageField.getText());
        
        updatedProperty.set(true);
        
        destroy();
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
    
    public void setImage(Image image) {
        this.image = image;
        
        this.build();
    }

    public BooleanProperty getUpdatedProperty() {
        return updatedProperty;
    }
    
    private void destroy() {
        container.getScene().getWindow().hide();
    }
}
