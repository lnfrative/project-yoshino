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
    private ArrayList<String> personas = new ArrayList();
    
    public Image(String path, String description, String lugar, String fecha, ArrayList<Image> images) {
        super(images);
        
        this.path = path;
        this.description = description;
        this.lugar = lugar;
        this.fecha = fecha;
    }
    
    public void addPersona(String persona) {
        this.personas.add(persona);
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

    public ArrayList<String> getPersonas() {
        return personas;
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

    public void setPersonas(ArrayList<String> personas) {
        this.personas = personas;
    }

    public String getPath() {
        return path;
    }
    
    
}
