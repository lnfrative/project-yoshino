/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import models.Image;

public class ImageItem extends HBox {
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
    
    public ImageItem() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/image_item.fxml"));
        
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void build() {
        imageView.setImage(new javafx.scene.image.Image(image.getPath()));
        description.setText(image.getDescription());
        lugar.setText(image.getLugar());
        fecha.setText(image.getFecha());
        gente.setText("TODO");
    }

    public void setImage(Image image) {
        this.image = image;
        
        this.build();
    }
}
