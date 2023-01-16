/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import models.Image;

public class ImageItemController {
    private Image image;
    
    @FXML
    private ImageView imageView;
    
    @FXML
    private Text description;
    
    @FXML
    private Text lugar;
    
    @FXML
    private Text fecha;
    
    @FXML
    private Text gente;
    
    public void initialize() {
        imageView.setImage(new javafx.scene.image.Image(image.getPath()));
        description.setText(image.getDescription());
        lugar.setText(image.getLugar());
        gente.setText(image.getPersonas().get(0));
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
