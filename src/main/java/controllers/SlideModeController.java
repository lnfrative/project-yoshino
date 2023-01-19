/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import models.Image;

public class SlideModeController implements Runnable {
    private boolean running = true;
    
    private ArrayList<Image> images = new ArrayList();
    
    @FXML
    private ImageView imageView;
    
    @FXML
    private TextField milisecondsField;
    
    @Override
    public void run() {
        milisecondsField.setText(Integer.toString(1000));
        
        int index = 0;
        
        while (true) {
            System.out.println(running);
            
            try {
                while (running) {
                    imageView.setImage(new javafx.scene.image.Image(images.get(index).getPath()));

                    index++;

                    Thread.sleep(Integer.parseInt(milisecondsField.getText()));
                    
                    if (index > (images.size() - 1)) {
                        index = 0;
                    }
                }
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }
    

    
    public void onPlay() {
        this.running = true;
    }
    
    public void onPausa() {
        this.running = false;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }
    
}
