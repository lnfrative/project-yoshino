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
import utils.*;

public class MainController {
    private Integer previousAlbumSelected = -1;
    
    private SearchParameters searchParameters = new SearchParameters();
    
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
        Object imageModelsObject = Guardar.read("image.models");
        Object albumModelsObject = Guardar.read("album.models");
        
        if (imageModelsObject != null) {
            images = (ArrayList<Image>) Guardar.read("image.models");
        }
        
        if (albumModelsObject != null) {
            albumes = (ArrayList<Album>) Guardar.read("album.models");
        }
        
        buildAlbumesListView();
        
        updateTotalImages();
        
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
    
    public void onOpenParametersWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/update_search_parameters.fxml"));
            Parent root = loader.load();
            
            UpdateSearchParametersController controller = loader.getController();
            controller.setMainController(this);
            controller.build();
            
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(container.getScene().getWindow());
            
            stage.setTitle("Actualizar parámetros de visualización");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }
    
    public void addAlbum(Album album) {
        albumes.add(album);
        buildAlbumesListView();
        
        Guardar.write("album.models", albumes);
    }
    
    public void addImage(Image image) {
        images.add(image);
        buildImagesListView();
        
        Guardar.write("image.models", images);
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
            
            Guardar.write("album.models", albumes);
        }
    }
    
    public void onSelectAlbum() {
        int selectedIndex = albumesListView.getSelectionModel().getSelectedIndex();
        
        if (selectedIndex != previousAlbumSelected && selectedIndex != -1) {
            
            searchParameters.setAlbumId(albumes.get(selectedIndex).getId());
            
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
        try {
            Image selectedImage = imagenesListView.getSelectionModel().getSelectedItem();
            
            if (selectedImage != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/update_image.fxml"));
                Parent root = loader.load();

                UpdateImageController controller = loader.getController();
                
                controller.setMainController(this);
                controller.setImage(images.get(images.indexOf(selectedImage)));
                controller.getUpdatedProperty().addListener((obs, oldVal, newVal) -> {
                    buildImagesListView();
                    Guardar.write("image.models", images);
                });
                
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initOwner(container.getScene().getWindow());

                stage.setTitle("Update imagen");
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }
    
    public void onEliminarImagen() {
        Image selectedImage = imagenesListView.getSelectionModel().getSelectedItem();
        
        if (selectedImage != null) {
            images.remove(images.indexOf(selectedImage));
            buildImagesListView();
            
            Guardar.write("image.models", images);
        }
    }
    
    public void onSlideMode() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/slide_mode.fxml"));
            Parent root = loader.load();
            
            SlideModeController controller = loader.getController();
            
            ArrayList<Image> slideImages = new ArrayList();
            
            for (Image image : images) {
                if (searchParameters.checkIfImageMeetsParameters(image)) {
                    slideImages.add(image);
                }
            }
            
            controller.setImages(slideImages);
            
            new Thread(controller).start();
            
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            
            stage.setTitle("Slide mode");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }
    
    public void onParametersUpdated() {
        buildImagesListView();
    }

    public ListView getAlbumesListView() {
        return albumesListView;
    }

    public SearchParameters getSearchParameters() {
        return searchParameters;
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
        
        updateTotalAlbumes();
    }
    
    private void buildImagesListView() {
        imagenesListView.getItems().clear();
        
        for (Image image : images) {
            if (searchParameters.checkIfImageMeetsParameters(image)) {
                imagenesListView.getItems().add(image);
            }
        }
        
        updateTotalImages();
    }
    
    private void updateTotalImages() {
        totalImages.setText("Imágenes: " + images.size());
    }
    
    private void updateTotalAlbumes() {
        totalAlbumes.setText("Álbumes: " + albumes.size());
    }
}
