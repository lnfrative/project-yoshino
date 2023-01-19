/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import models.Image;

public class SearchParameters {
    private Integer albumId;
    
    private String rangoFechas = "";
    
    private String lugares = "";
    
    private String personas = "";

    public String getRangoFechasValue() {
        return rangoFechas;
    }

    public String getLugaresValue() {
        return lugares;
    }

    public String getPersonasValue() {
        return personas;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setRangoFechasValue(String rangoFechas) {
        this.rangoFechas = rangoFechas;
    }

    public void setLugaresValue(String lugares) {
        this.lugares = lugares;
    }

    public void setPersonasValue(String personas) {
        this.personas = personas;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }
    
    public boolean checkIfImageMeetsParameters(Image image) {
        if (!albumId.equals(image.getAlbumId())) {
            return false;
        }
        
        if (!rangoFechas.isEmpty()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
            String[] fechas = rangoFechas.split(", ");
            
            try {
                Date startDate = formatter.parse(fechas[0]);
                Date endDate = formatter.parse(fechas[1]);
                Date imageDate = formatter.parse(image.getFecha());
                
                if (startDate.compareTo(imageDate) * imageDate.compareTo(endDate) < 0) {
                    return false;
                }
            } catch (ParseException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        
        if (!lugares.isEmpty()) {
            String[] lugarList = this.lugares.split(", ");
            
            for (String lugar : lugarList) {
                if (!lugar.equalsIgnoreCase(image.getLugar())) {
                    return false;
                }
            }
        }
        
        if (!personas.isEmpty()) {
            String[] personaList = this.personas.split(", ");
            String[] personasFromImage = image.getGente().split(", ");
            
            for (String persona : personaList) {
                
                for (String personaFromImage : personasFromImage) {
                    
                    if (persona.equalsIgnoreCase(personaFromImage)) {
                        return true;
                    }
                    
                }
            }
            
            return false;
        }
        
        return true;
    }
}
