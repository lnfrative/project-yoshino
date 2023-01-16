/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import components.ImageItem;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import models.*;

public class MainController {
    private Integer previousAlbumSelected = -1;
    
    private ArrayList<Album> albumes = new ArrayList();
    private ArrayList<Image> images = new ArrayList();
    
    @FXML
    private Label totalAlbumes;
    
    @FXML
    private Label totalImages;
    
    @FXML
    private VBox container;
    
    @FXML
    private ListView albumesListView;
    
    @FXML
    private ListView<Image> imagenesListView;
    
    public void initialize() {
        imagenesListView.setCellFactory(param -> new ListCell<Image>() {
            private final ImageItem imageItem = new ImageItem();
            
            @Override
            protected void updateItem(Image image, boolean empty) {
                super.updateItem(image, empty);
                
                if (empty) {
                    setGraphic(null);
                }
                
                if (!empty) {
                    imageItem.setImage(image);
                    setGraphic(imageItem);
                }
            }
        });
    }
    
    public void onOpenWindowToCreateAlbum() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/create_album.fxml"));
            Parent root = loader.load();
            
            CreateAlbumController controller = loader.getController();
            controller.setMainController(this);
            
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(container.getScene().getWindow());
            
            stage.setTitle("Create album");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }
    
    public void addAlbum(Album album) {
        albumes.add(album);
        buildAlbumesListView();
    }
    
    public void addImage(Image image) {
        images.add(image);
        buildImagesListView();
    }
    
    public void onRemoveAlbum() {
        int selectedIndex = albumesListView.getSelectionModel().getSelectedIndex();
        Album albumSelected = albumes.get(selectedIndex);
        boolean albumInUse = false;
        
        for (Image image : images) {
            if (albumSelected.getId().equals(image.getAlbumId())) {
                albumInUse = true;
            }
        }
        
        if (selectedIndex != -1 && !albumInUse) {
            albumes.remove(selectedIndex);
            buildAlbumesListView();
        }
    }
    
    public void onSelectAlbum() {
        int selectedIndex = albumesListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != previousAlbumSelected && selectedIndex != -1) {
            buildImagesListView();
        }
        
        previousAlbumSelected = selectedIndex;
    }

    public ArrayList<Album> getAlbumes() {
        return albumes;
    }

    public ArrayList<Image> getImages() {
        return images;
    }
    
    public void onModificarImagen() {
        
    }
    
    public void onEliminarImagen() {
        int selectedIndex = imagenesListView.getSelectionModel().getSelectedIndex();
        
        if (selectedIndex != -1) {
            images.remove(selectedIndex);
            buildImagesListView();
        }
    }
    
    public void onOpenWindowToAddImage() {
        try {            
            int selectedAlbumIndex = albumesListView.getSelectionModel().getSelectedIndex();
            
            if (selectedAlbumIndex != -1) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/create_image.fxml"));
                Parent root = loader.load();

                CreateImageController controller = loader.getController();
                controller.setMainController(this);
                controller.setAlbumId(albumes.get(selectedAlbumIndex).getId());
                
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initOwner(container.getScene().getWindow());

                stage.setTitle("Add image");
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }
    
    private void buildAlbumesListView() {
        albumesListView.getItems().clear();
        
        for (Album album : albumes) {
            albumesListView.getItems().add(album.getName());
        }
        
        totalAlbumes.setText("Álbumes: " + albumes.size());
    }
    
    private void buildImagesListView() {
        imagenesListView.getItems().clear();
        
        Integer albumSelectedIndex = albumes.get(albumesListView.getSelectionModel().getSelectedIndex()).getId();
        
        for (Image image : images) {
            if (albumSelectedIndex == image.getAlbumId()) {
                imagenesListView.getItems().add(image);
            }
        }
        
        totalImages.setText("Imágenes: " + images.size());
    }
}
