public class Ejercicio4{
    public static void main(String[] args) {

        int posicion=20;

        long siguiente = 1, actual = 0, anterior = 0,temp=0;
		for (long x = 1; x <= posicion; x++) {
			System.out.print(actual + ", ");
            temp=siguiente;
			siguiente = siguiente + anterior + actual;

			anterior = actual;
			actual = temp;
		}
		System.out.println(actual);
    }
}