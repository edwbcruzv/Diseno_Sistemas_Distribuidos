

POJO (Plain Old Java Object) (Objeto Java PLano Antiguo).
Es una clase simple e independiente (No dependen de ningun famework),
no posee restricciones especiales y su objetivo es simplificar 
la estructuracion de los desarrollos reduciendo la complejidad,
aumentando la legibilidad y facilitar su reutilizacion.

Los POJOs no pueden:
         - Implementar interfaces.
         - Extender de otras clases.
         - Contener decoradores.


ObjectMapper proporciona 4 metodos contructores para serializar y deserializar un POJO a cadena JSON y viceversa:

Serializar:
writeValueAsString(POJO): regresa un string en formato JSON.
writeValueAsBytes(POJO): regresa un arreglo de bytes.
writeValue(File,POJO): genera el JSON en el fichero.

Deserializar:
readValue(File,MyClass.class):Lee un fichero.json y regresa el POJO
readValue(URL,MyClass.class):Lee un fichero.json desde una URL y regresa el POJO
readValue(srt_JSON,MyClass.class):Lee una cadena en formato JSON y regresa el POJO

Deserializacion con Colecciones muy generales
readValue(jsonSource,Map.class): devuelve una Map
readValue(jsonSource,List.class): devuelve un List

Deserializacion con Colecciones POJO
Map<String, Object> jsonStringToMap = objectMapper.readValue(countryStr,
                new TypeReference<Map<String, Object>>() { });

List<POJO> countryArrayAsList = objectMapper.readValue(countryArrayStr, 
                new TypeReference<List<POJO>>() { });

Para recuperar datos de un nodo en especifico se puede usar JsonNode
readTree(jsonSource): obtenemos la raiz del json
get(propiedad): obtenemos el valor de la propiedad (hacer el parseo para obtener correctamente el valor).


Para crear nuestro propio arbol e ir insertando propiedades manuelamente:

createObjectNode(): obtenemos un nodo raiz vacio
put(propiedad,valor): se inserta la propiedad al nodo raiz
putArray(nombre_array): se inserta un arreglo vacio
add(propiedad):agregar un elemento a un nodo lista.
addPOJO(POJO): agrega un POJO a un nodo lista.