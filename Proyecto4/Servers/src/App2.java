/*
 * Proyecto 4
 * Cruz Villalba Edwin Bernardo
 * Grupo 4CM11
 * 
 */

import java.util.Arrays;

public class App2{
    public static void main(String[] args) throws Exception {

        String DB_ADDRESS_1 = "http://localhost:5001";
        String DB_ADDRESS_2 = "http://localhost:5002";
        String DB_ADDRESS_3 = "http://localhost:5003";
        
        Server server1 = new Server(5001);
        server1.start();

        Server server2 = new Server(5002);
        server2.start();

        Server server3 = new Server(5003);
        server3.start();

        ServerDB server_db = new ServerDB(80);
        server_db.start();
        server_db.runDB(Arrays.asList(DB_ADDRESS_1,DB_ADDRESS_2,DB_ADDRESS_3));
        
        try {
            server_db.generateDB(Integer.parseInt(args[0]));
        } catch (Exception e) {
            System.out.println("Se olvido de agregar los segundos de timestap de generacion de CURPs.");
            return;
        }

        System.out.println("Funcionando...");
    }
}
