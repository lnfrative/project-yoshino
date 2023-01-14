/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.frative.project.yoshino;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ProjectYoshino extends Application {

    public static void main(String[] args) throws IOException {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/MainView.fxml"));
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }
}
