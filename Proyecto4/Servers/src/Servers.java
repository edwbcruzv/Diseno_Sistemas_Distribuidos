/*
 * Proyecto 4
 * Cruz Villalba Edwin Bernardo
 * Grupo 4CM11
 * 
 */


public class Servers{
    public static void main(String[] args) throws Exception {
        
        Server server1 = new Server(5001);
        server1.start();

        Server server2 = new Server(5002);
        server2.start();

        Server server3 = new Server(5003);
        server3.start();

        System.out.println("Funcionando...");
    }
}
