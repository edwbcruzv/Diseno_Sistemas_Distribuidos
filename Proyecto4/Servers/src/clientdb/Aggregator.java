package clientdb;

import java.util.List;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Aggregator {
    public ClienteDB client_db;
    private CompletableFuture<HttpResponse<String>>[] futures;
    private List<String> server_list;
    private boolean flag_generate;

    public Aggregator(List<String> server_list) {
        this.client_db = new ClienteDB();
        this.futures = new CompletableFuture[server_list.size()];
        this.server_list= server_list;

        System.out.println("Numero de Servidores BDs:" + this.server_list.size());

        // inicializando los futures con un status
        for (int i = 0; i < this.futures.length; i++) {
            this.futures[i] = this.client_db.sendAsyncRequestGET(this.server_list.get(i) + "/status_server");
            System.out.println("Status future[" + i + "] (" + this.server_list.get(i) + ") ");

            HttpResponse<String> res = this.futures[i].join();
            // al estar terminado el future significa que termino de generarse una CURP
            System.out.println(this.server_list.get(i) + "/status_server ---->Body:" + res.body());

        }
    }

    public void startGenerateCURPs(int segs) {
        this.flag_generate = true;
        int index_server = 0;

        while (this.flag_generate) {
            /*Preguntamos si ya termino algun server en generar una curp */
            if (this.futures[index_server].isDone()) {
                            
                // al estar libre volvemos a generar otra curp   
                this.futures[index_server] =this.client_db.sendAsyncRequestPOST(this.server_list.get(index_server) + "/push_curp",CURP.getCURP().getBytes());

                // obtenemos la respuesta
                HttpResponse<String> res = this.futures[index_server].join();
                System.out.println(this.server_list.get(index_server) + "/push_curp ---->Body:" + res.body());
                try {
                    TimeUnit.SECONDS.sleep(segs);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }

            index_server++;
            if (index_server == this.server_list.size()) {
                // cuando se supera el numero se servidores se reinicia el contador
                index_server = 0;
            }

        }

    }


    public void stopGenerateCURPs(){
        this.flag_generate = false;
    }

    public String statusListDB(){

        String str_res="";
        HttpResponse<String> res;
        int cont_curps=0;

        for (int i = 0; i < this.server_list.size(); i++) {

            try {
                res = this.client_db.sendRequestGET(this.server_list.get(i) + "/status_list");
                cont_curps+=Integer.parseInt(res.body());
                str_res= str_res+this.server_list.get(i) + " tiene " + res.body() + " CURPs.\n";
            } catch (IOException e) {
                str_res= str_res+this.server_list.get(i)+ ": Ocurrio un error al obtener la cantidad de CURPs.\n";
                e.printStackTrace();
            } catch (InterruptedException e) {
                str_res = str_res + this.server_list.get(i) + ": Ocurrio un error al obtener la cantidad de CURPs.\n";
                e.printStackTrace();
            }
            

        }
        return str_res+"Cantidad total de CURPs del servidor:"+ String.valueOf(cont_curps)+"\n";
    }

    public String lengthBytesListDB() {

        String str_res = "";
        HttpResponse<String> res;
        int cont_bytes = 0;

        for (int i = 0; i < this.server_list.size(); i++) {

            try {
                res = this.client_db.sendRequestGET(this.server_list.get(i) + "/length_bytes_list");
                cont_bytes += Integer.parseInt(res.body());
                str_res = str_res + this.server_list.get(i) + " pesa " + res.body() + " bytes.\n";
            } catch (IOException e) {
                str_res = str_res + this.server_list.get(i) + ": Ocurrio un error al obtener los bytes.\n";
                e.printStackTrace();
            } catch (InterruptedException e) {
                str_res = str_res + this.server_list.get(i) + ": Ocurrio un error al obtener los bytes.\n";
                e.printStackTrace();
            }

        }
        return str_res + "Cantidad total de bytes del servidor:" + String.valueOf(cont_bytes) + "\n";
    }

    

    public String countMaleFeminDB() {

        String str_res = "";
        HttpResponse<String> res_male;
        HttpResponse<String> res_femin;
        int cont_male = 0;
        int cont_femin = 0;

        for (int i = 0; i < this.server_list.size(); i++) {

            try {
                res_male = this.client_db.sendRequestGET(this.server_list.get(i) + "/count_male");
                res_femin = this.client_db.sendRequestGET(this.server_list.get(i) + "/count_femin");
                cont_male += Integer.parseInt(res_male.body());
                cont_femin += Integer.parseInt(res_femin.body());
                str_res = str_res + this.server_list.get(i) + " Hombres:" + res_male.body() + ", Mujeres:"
                        + res_femin.body() + ".\n";
            } catch (IOException e) {
                str_res = str_res + this.server_list.get(i) + ": Ocurrio un error al obtener la cantidad de hombres y mujeres.\n";
                e.printStackTrace();
            } catch (InterruptedException e) {
                str_res = str_res + this.server_list.get(i) + ": Ocurrio un error al obtener la cantidad de hombres y mujeres.\n";
                e.printStackTrace();
            }

        }
        return str_res + "Cantidad total Hombres:" + String.valueOf(cont_male)+", Mujeres"+String.valueOf(cont_femin)+ "\n";
    }

    public String countEntityDB(String str_entity) {

        String str_res = "";
        HttpResponse<String> res;
        int cont_entity = 0;

        for (int i = 0; i < this.server_list.size(); i++) {

            try {
                res = this.client_db.sendRequestPOST(this.server_list.get(i) + "/count_entity",str_entity.getBytes());
                cont_entity += Integer.parseInt(res.body());
                str_res = str_res + this.server_list.get(i) + " se encontraron " + res.body() + ".\n";
            } catch (IOException e) {
                str_res = str_res + this.server_list.get(i) + ": Ocurrio un error al obtener las concurrencias.\n";
                e.printStackTrace();
            } catch (InterruptedException e) {
                str_res = str_res + this.server_list.get(i) + ": Ocurrio un error al obtener las concurrencias.\n";
                e.printStackTrace();
            }

        }
        return str_res + "Cantidad total de "+str_entity+"s en el servidor:" + String.valueOf(cont_entity) + "\n";
    }
}
