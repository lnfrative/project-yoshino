/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.ArrayList;

public class Image extends Model {
    private String path;
    private String description;
    private String lugar;
    private String fecha;
    private int albumId;
    private String gente;
    
    public Image(String path, String description, String lugar, String fecha, ArrayList<Image> images, int albumId, String gente) {
        super(images);
        
        this.path = path;
        this.description = description;
        this.lugar = lugar;
        this.fecha = fecha;
        this.albumId = albumId;
        this.gente = gente;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public void setGente(String gente) {
        this.gente = gente;
    }

    public String getGente() {
        return gente;
    }

    public String getDescription() {
        return description;
    }

    public String getLugar() {
        return lugar;
    }

    public String getFecha() {
        return fecha;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPath() {
        return path;
    }

    public int getAlbumId() {
        return albumId;
    }
    
    
}
