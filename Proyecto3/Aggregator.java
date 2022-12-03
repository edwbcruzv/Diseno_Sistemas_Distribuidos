/*
 * Proyecto 3
 * Cruz Villalba Edwin Bernardo
 * Grupo 4CM11
 * 
 */
import java.util.List;
import java.util.Map;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CompletableFuture;

public class Aggregator {
    private WebClient webClient;

    public Aggregator() {
        this.webClient = new WebClient();
    }

    public List<String> sendTasksToWorkers(List<String> server_list, ArrayList<String> tasks) {
        CompletableFuture<HttpResponse<String>>[] futures = new CompletableFuture[server_list.size()];

        System.out.println("Numero de Servidores:"+server_list.size());
        System.out.println("Numero de tareas:"+tasks.size());
        //inicializando los futures

        for (int i = 0; i < futures.length; i++) {
            String task = tasks.get(i);
            System.out.println("Inicializando future[" + i + "] (" + server_list.get(i) + ") realizara tarea["
            + (i+ 1) + "]:" + task);
            futures[i] = webClient.sendTask(server_list.get(i), task.getBytes());
        }

        System.out.println();


        // aqui se almacenaran los resultados de cada tarea
        List<String> results = new ArrayList();

        boolean bandera=true;
        int index_server=0;
        int index_finish_task=0;
        int index_next_task= futures.length;

        
        while (bandera) {

            if(futures[index_server].isDone()) {

                HttpResponse<String> res = futures[index_server].join();
                // al estar terminado el fucture se lee el resultado de la tarea que se le asigno y se carga otra
                results.add(res.headers().toString() + "\nTarea:\""+tasks.get(index_finish_task)+"\" ---->Body:" + res.body());
                index_finish_task++;

                if (index_next_task< tasks.size()) {
                    // Al estar libre el fucture se le asigna una tarea
                    String task = tasks.get(index_next_task);
                    futures[index_server] = webClient.sendTask(server_list.get(index_server), task.getBytes());
                    System.out.println("Termino future[" + index_server + "] (" + server_list.get(index_server) + "). Ahora realizara tarea[" + (index_next_task + 1) + "]:\"" + task+"\"");
                    // al asignarse una tarea se pasa a la siguiente
                    index_next_task++;
                }
            }
            
            index_server++;
            if (index_server == server_list.size()) {
                // cuando se supera el numero se servidores se reinicia el contador
                index_server = 0;
            }

            if (index_finish_task== tasks.size()) {
                // cuando terminamos de mandar todas las tareas y cuando se tengan los resultados de todas las tareas
                break;
            }   
        }

        return results;
    }
}
