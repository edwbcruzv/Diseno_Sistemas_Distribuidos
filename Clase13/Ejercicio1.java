import java.io.File;
import java.util.Scanner;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Ejercicio1 {

	public static void main(String[] args) {

		// Fichero del que queremos leer
		File fichero = new File("El_viejo_y_el_mar.txt");
		Scanner s = null;
		// <Caracter, veces que aparece>
		Map<String, Integer> hm = new HashMap<String, Integer>();
		ArrayList<Concurrencia> arr_list = new ArrayList<Concurrencia>();

		try {
			// Leemos el contenido del fichero
			System.out.println("Leyendo fichero ...");
			s = new Scanner(fichero);

			// Leemos linea a linea el fichero
			while (s.hasNextLine()) {
				String linea = s.nextLine(); // Guardamos la linea en un String
				char[] arr = linea.toCharArray();

				// System.out.println(linea); // Imprimimos la linea
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
			try {
				if (s != null)
					s.close();
			} catch (Exception ex2) {
				System.out.println("Mensaje 2: " + ex2.getMessage());
			}
		}
	}
}