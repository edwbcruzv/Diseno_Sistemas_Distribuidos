
/*
 * Proyecto 2 (Parte 1)
 * Nombre: Cruz Villalba Edwin Bernardo
 * Grupo 4CM11
 *  
 */
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class App {
    public static void main(String[] args) throws Exception {
        // en la sig ruta debe de ir los .txt de los libros que se deben leer.
        String path_carpeta = "/LIBROS_TXT";

        Scanner in = new Scanner(System.in);

        List list_archivos = Lector.listaArchivos(path_carpeta);
        Set palabras = new HashSet<String>();

        // System.out.println(list_archivos);
        // Set set=Lector.leerArchivo(path_carpeta+"/"+(String)list_archivos.get(0));
        // System.out.println(set);

        for (Object texto : list_archivos) {
            // System.out.println(texto);
            Set aux = Lector.leerArchivo(path_carpeta + "/" + (String) texto);
            palabras.addAll(aux);
            // System.out.println("longitud:"+palabras.size());
        }

        // Runtime.getRuntime().exec("clear");
        System.out.println("Diccionario listo, ingrese alguna palabra o frase:");
        // System.out.println(palabras);
        List list_frase = Lector.separador(in.nextLine());
        while (list_frase.isEmpty() != true) {
            for (Object cad : list_frase) {

                if (palabras.contains((String) cad)) {
                    System.out.println("=> \"" + (String) cad + "\" es correcta");
                } else {
                    System.out.println("=> \"" + (String) cad + "\" esta mal escrita");
                }
            }
            System.out.println("(Presione enter para salir o siga escribiendo)");
            list_frase = Lector.separador(in.nextLine());

        }

    }
}
