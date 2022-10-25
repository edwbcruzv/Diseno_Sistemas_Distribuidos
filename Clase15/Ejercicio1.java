
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Ejercicio1 {

	public static void main(String[] args) throws FileNotFoundException,IOException {

		// Fichero del que queremos leer
		FileReader fichero = new FileReader("./BIBLIA_COMPLETA.txt");
		BufferedReader b = new BufferedReader(fichero);
		String cadena;
		// <Caracter, veces que aparece>
		Map<String, Integer> hm = new HashMap<String, Integer>();
		ArrayList<Concurrencia> arr_list = new ArrayList<Concurrencia>();

		try {
			// Leemos el contenido del fichero
			System.out.println("Leyendo fichero ...");
			
			// Leemos linea a linea el fichero
			while ((cadena=b.readLine())!=null) {
				 // Guardamos la linea en un String
				char[] arr = cadena.toCharArray();

				System.out.println(cadena); // Imprimimos la linea
				for (char c : arr) {
					// System.out.print(c);

					if (hm.containsKey(String.valueOf(c))) {
						hm.put(String.valueOf(c), hm.get(String.valueOf(c)) + 1);
					} else {
						hm.put(String.valueOf(c), 1);
					}

				}
			}

			for (Map.Entry<String, Integer> me : hm.entrySet()) {

				// Printing keys
				System.out.print(me.getKey() + ":");
				System.out.println(me.getValue());
				arr_list.add(new Concurrencia(me.getKey(), me.getValue()));
			}

			Collections.sort(arr_list,new ConcurrenciaComp());

			for (Concurrencia c : arr_list) {
				System.out.println(c);
			}

		} catch (Exception ex) {
			System.out.println("Mensaje: " + ex.getMessage());
		} finally {
			// Cerramos el fichero tanto si la lectura ha sido correcta o no
			System.out.println("finale");
		}
	}
}