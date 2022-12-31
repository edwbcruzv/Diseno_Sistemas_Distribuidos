/*
 * Proyecto 2 (Parte 1)
 * Nombre: Cruz Villalba Edwin Bernardo
 * Grupo 4CM11
 *  
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Lector {
    // usar set y hsahset

    public static Set leerArchivo(String nombre_archivo) {
        // File file = new File(nombre_archivo);
        System.out.println("Leyendo:" + nombre_archivo);
        Set set = new HashSet<String>();

        try {
            BufferedReader in = new BufferedReader(new FileReader(nombre_archivo));

            try {

                String linea = "";
                do {
                    // System.out.println(linea);
                    StringTokenizer st = new StringTokenizer(linea);

                    while (st.hasMoreTokens()) {
                        ;
                        // System.out.println(st.nextToken());
                        set.add(st.nextToken());
                    }
                    st = null;
                    linea = in.readLine();
                } while (linea != null);

                in.close();
                return set;

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static List listaArchivos(String nombre_carpeta) {
        File carpeta = new File(nombre_carpeta);
        List lista = new ArrayList<String>();

        if (carpeta.exists()) {
            System.out.println("La Carpeta si existe");
        } else {
            System.out.println("La carpeta no existe");
            return null;
        }

        if (carpeta.list() == null) {
            System.out.println("No hay archivos en la carpeta");
            return null;
        }

        lista = Arrays.asList(carpeta.list());
        return lista;
    }

    public static List separador(String frase) {
        StringTokenizer st = new StringTokenizer(frase);
        List lista = new ArrayList<String>();

        while (st.hasMoreTokens()) {
            ;
            lista.add(st.nextToken());
        }
        return lista;
    }
}
