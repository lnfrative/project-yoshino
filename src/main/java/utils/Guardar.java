/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class Guardar {
    public static void write(String path, Object object) {
        try {
          OutputStream imageOutput = new FileOutputStream(path);
          
            try (ObjectOutputStream os = new ObjectOutputStream(imageOutput)) {
                os.writeObject(object);
                os.close();
            }
        } catch(IOException e) {
          System.out.println(e.getMessage());
        }
    }
    
    public static Object read(String path) {
        Object object = null;
        try {
            InputStream imageInput = new FileInputStream(path);
            
            try (ObjectInputStream in = new ObjectInputStream(imageInput)) {
                object = in.readObject();
                in.close();
            }
        } catch(Exception e) {
          System.out.println(e.getMessage());
        }
        
        return object;
    }
}
