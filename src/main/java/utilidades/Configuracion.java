/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Configuracion {

    private Properties propiedades;

    public Configuracion() {
        EstablecerPropiedades();
    }

    public Configuracion(String nombreArchivoConfig) {
        propiedades = new Properties();
        InputStream entrada = null;
        try {
            entrada = Configuracion.class.getClassLoader().getResourceAsStream(nombreArchivoConfig);
            propiedades.load(entrada);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (entrada != null) {
                try {
                    entrada.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String obtenerValorPropiedad(String nombrePropiedad) {
        return propiedades.getProperty(nombrePropiedad);
    }

    private void EstablecerPropiedades() {

        propiedades = new Properties();
        InputStream entrada = null;

        try {
            entrada = Configuracion.class.getClassLoader().getResourceAsStream("configuracion.properties");
            propiedades.load(entrada);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (entrada != null) {
                try {
                    entrada.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
