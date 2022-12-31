import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Scanner;

import Client.Client;

public class App {
    private Client client;


    public static void main(String[] args) throws Exception {
        App app = new App();
        
        boolean bandera=true;
        Scanner in = new Scanner(System.in);
        int opcion=0;

        while (bandera) {

            System.out.println( "Elija una opcion:\n"+
                                "1.- CURPs generadas por minuto.\n" +
                                "2.- Cantidad de registros.\n" +
                                "3.- Bytes de la Base de datos.\n" +
                                "4.- Numeros de Hombre y Mujeres.\n" +
                                "5.- Cantidad de Registros por entidad." +
                                "Presiones 0 para salir.");
            try {
                opcion=in.nextInt();
                app.optionSwich(opcion);
            } catch (Exception e) {
                System.out.println("El valor ingresado no es valido, intente de nuevo\n\n");
                e.printStackTrace();
            }

            if (opcion==0) {
                bandera=false;
            }
            
        }
        in.close();
    }
    public App() {
        this.client = new Client();
    }

    public void optionSwich(int n) throws IOException, InterruptedException {

        HttpResponse<String> res;
        Scanner in = new Scanner(System.in);

        switch (n) {
            case 0:
                res = this.client.sendRequestGET("http://localhost:5004/status_server_db");
                System.out.println(res.body()+"bye.");
                break;
            case 1:
                res = this.client.sendRequestGET("http://localhost:5004//status_generate_db");
                System.out.println(res.body());
                break;
            case 2:
                res = this.client.sendRequestGET("http://localhost:5004/status_list_db");
                System.out.println(res.body());
                break;
            case 3:
                res = this.client.sendRequestGET("http://localhost:5004/length_bytes_list_db");
                System.out.println(res.body());
                break;
            case 4:
                res = this.client.sendRequestGET("http://localhost:5004/count_male_femin_db");
                System.out.println(res.body());
                break;
            case 5:
                res = this.client.sendRequestPOST("http://localhost:5004/count_entity_db", in.nextLine().getBytes());
                System.out.println(res.body());
                break;
            default:
                break;
        }
        in.close();
    }

}
