/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ramiro
 */
public class CargadorDB {


    public ArrayList<String> cargarJugadores() {

        
        String archivoCSV =  "/home/ramiro/Escritorio/jugadores.csv";
        
        BufferedReader br = null;
        String line = "";
        String separador = ",";
        ArrayList<String> nombres = new ArrayList<>();
        ArrayList<String> apellidos = new ArrayList<>();
        ArrayList<String> respuesta = new ArrayList<>();

        try {

            br = new BufferedReader(new FileReader(archivoCSV));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] contenido = line.split(separador);

                nombres.add(contenido[0]);
                apellidos.add(contenido[1]);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        for (int i = 0; i < nombres.size(); i++) {
            respuesta.add(nombres.get(randInt(0, nombres.size() - 1)) + " " + apellidos.get(randInt(0, apellidos.size() - 1)));
        }

        return respuesta;
    }

    public static int randInt(int min, int max) {

        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
