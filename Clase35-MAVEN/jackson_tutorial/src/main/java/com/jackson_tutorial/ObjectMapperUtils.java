package com.jackson_tutorial;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import com.jackson_tutorial.Model.Country;
import com.jackson_tutorial.Model.Info;



public class ObjectMapperUtils {

    /*ObjectMapper proporciona 4 cosntructores para serializar y deserializar
    un POJO a cadena JSON o viceversa  */
    private static ObjectMapper objectMapper = new ObjectMapper();

    private static Country country = new Country("India", 135260000000L, 29, true);
    private static String countryStr = "{\"name\":\"India\",\"population\":135260000000,\"numberOfProvinces\":29,\"developed\":true}";

    /*De POJO a string-JSON (Serializacion)*/
    public static void objectToJsonString() throws JsonProcessingException {
        // el parametro sera el POJO a serializar (Metodo 1)
        String countryAsString = objectMapper.writeValueAsString(country);
        System.out.println("objectToJsonString : " + countryAsString + "\n");
    }
    
    /*De POJO a matriz de Bytes (Serializacion)*/
    public static void objectToBytes() throws JsonProcessingException {
        // el parametro sera el POJO a serializar (Metodo 2)
        byte[] countryAsBytes = objectMapper.writeValueAsBytes(country);
        System.out.println("objectToBytes : " + countryAsBytes + "\n");
    }
    
    /*De POJO a un archivo .JSON (Serializacion)*/
    public static void objectToJsonInFile() throws IOException {
        // el parametro sera el archivo FILE con la ruta destino y nombre 
        // el segundo parametro el POJO a serializar (Metodo 3)
        objectMapper.writeValue(new File("target/country.json"), country);
        System.out.println("objectToJsonInFile: target/country.json creado.\n");
    }

    /*De string-JSON a POJO (Deserializacion)*/
    public static void jsonStrToObject() throws IOException {
        // el parametro sera el string-JSON a deserializar (Metodo 4)
        Country countryFromString = objectMapper.readValue(countryStr, Country.class);
        System.out.println("jsonStrToObject : " + countryFromString + "\n");
    }

    /*De un archivo .JSON a POJO (Deserializacion)*/
    public static void jsonInFileToObject() throws IOException {
        // el parametro sera el FILE con la ruta y nombre del archivo .json (Metodo 5)
        Country countryFromFile = objectMapper.readValue(new File("target/country.json"), Country.class);
        System.out.println("jsonInFileToObject : " + countryFromFile + "\n");
    }

    /*De un string JSON a un Map (Parseo)*/
    public static void readJsonStringAsMap() throws IOException {
        // el parametro sera la cadena con el formato JSON y hacer la referencia al Map 
        Map<String, Object> jsonStringToMap = objectMapper.readValue(countryStr,
                new TypeReference<Map<String, Object>>() {// (siempre sera la misma referencia)
                });
        System.out.println("JsonStringToMap : " + jsonStringToMap + "\n");
    }
    
    /* De un string JSON a un List (Parseo) */
    public static void readJsonArrayStringAsList() throws IOException {
        String countryArrayStr = "[{\"name\":\"India\",\"population\":135260000000,"
                + "\"numberOfProvinces\":29,\"developed\":true},{\"name\":\"SomeCountry\","
                + "\"population\":123456789000,\"numberOfProvinces\":45," + "\"developed\":true}]";
        // el parametro sera la cadena con el formato JSON y hacer la referencia al Map
        List<Country> countryArrayAsList = objectMapper.readValue(countryArrayStr, 
                new TypeReference<List<Country>>() { // (siempre sera la misma referencia, cambiando el timpo de clase POJO)
                });
        System.out.println("JsonArrayStringToList " + countryArrayAsList + "\n");
    }

    /*Para recuperar datos de un nodo especifico  */
    /* De un string JSON a un jsonNode (Parseo) */
    public static void parseJsonStringAsJsonNode() throws IOException {
        // creando jsonNode
        JsonNode jsonNode = objectMapper.readTree(countryStr);
        // obteniendo los nodos o propiedades especificos
        String name = jsonNode.get("name").asText();
        Long population = jsonNode.get("population").asLong();
        Integer provinces = jsonNode.get("numberOfProvinces").asInt();
        boolean isDeveloped = jsonNode.get("developed").asBoolean();

        System.out.println("parseJsonStringAsJsonNode - Name = " + name + " Population = " + population + " Provinces "
                + provinces + " isDeveloped " + isDeveloped + "\n");
    }



    public static void createJsonNodeStr() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        // creando nodo raiz vacia
        ObjectNode root = objectMapper.createObjectNode();
        // agregando propiedados o nodos
        root.put("asText", "SampleString");
        root.put("asBoolean", false);
        // creando un arreglo en la propiedad o nodo
        ArrayNode array = root.putArray("asArray");
        // creando un POJO
        Country country = new Country("India", 135260000000L, 29, true);
        // leyendo un archivo .json a un POJO (tener cuidado con la exitencia del archivo target/random.json)
        Country countryFromFile = objectMapper.readValue(new File("target/random.json"), Country.class);
        // agregando el POJO al arreglo
        array.addPOJO(country);
        array.addPOJO(countryFromFile);
        System.out
                .println("createJsonNodeStr " + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(root));
    }

    /* Demostracion de una propiedad desconocida que no esta incluida en el POJO */
    public static void demoUnknownField() throws IOException {
        String countryStrUnknownField = "{\"name\":\"India\",\"population\":135260000000,"
                + "\"numberOfProvinces\":29,\"developed\":true, " + "\"extraField\":\"some-value\"}";
        Country countryUnknownField;
        try {
            // sig linea ignora  cualquier propiedad desconocida para el POJO (metodo1)
            // objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); 
            countryUnknownField = objectMapper.readValue(countryStrUnknownField, Country.class);
        } catch (Exception ex) {
            System.out.println("demoUnknownField : Exception - Message " + "\n");
            ex.printStackTrace();
        }
        // sig linea ignora cualquier propiedad desconocida para el POJO (metodo2)
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        countryUnknownField = objectMapper.readValue(countryStrUnknownField, Country.class);
        System.out.println("demoUnknownField " + countryUnknownField + "\n");
    }
    /* Valores primitivos nulos  */
    public static void demoNullPrimitiveValues() throws IOException {
        Country countryPrimitiveNull = null;
        // la siguiente linea permite los valores nulos en las propiedades
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        String countryStrPrimitiveNull = "{\"name\":\"India\"," + "\"population\":135260000000,\"numberOfProvinces\""
                + ":null,\"developed\":true}";
        try {
            countryPrimitiveNull = objectMapper.readValue(countryStrPrimitiveNull, Country.class);
            System.out.println("demoNullPrimitiveValues " + countryPrimitiveNull + "\n");
        } catch (Exception ex) {
            System.out.println("demoNullPrimitiveValues : Exception - Message ");
            ex.printStackTrace();
        }
    }
    
    /*Serializando una Fecha */
    public static void objectWithDateToJsonString() throws JsonProcessingException {
        DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ssZ yyyy");
        objectMapper.setDateFormat(df);
        // creando un POJO Info con una Country y una fecha
        String objWithDateAsJsonString = objectMapper.writeValueAsString(new Info(country, new Date()));
        System.out.println("objectWithDateToJsonString " + objWithDateAsJsonString + "\n");
        
    }
    /*Deserializando una fecha */
    public static void jsonStringWithDateToObject() throws IOException {
        String infoAsString = "{\"country\":{\"name\":\"India\","
                + "\"population\":135260000000,\"numberOfProvinces\":29,"
                + "\"developed\":true},\"now\":\"vie. dic 30 14:46:46-0600 2022\"}";
        Info info = objectMapper.readValue(infoAsString, Info.class);
        System.out.println("jsonStringWithDateToObject " + info.getNow() + "\n");
       
    }

    /* Serializacion Personalizada */
    public static void demoCustomSerializer() throws JsonProcessingException {

        class CustomCountrySerializer extends StdSerializer<Country> {

            private static final long serialVersionUID = 1L;

            public CustomCountrySerializer() {
                this(null);
            }

            public CustomCountrySerializer(Class<Country> clazz) {
                super(clazz);
            }

            @Override
            public void serialize(Country country, JsonGenerator jsonGenerator, SerializerProvider serializer)
                    throws IOException {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("country_name_only_field", country.getName());
                jsonGenerator.writeEndObject();
            }
        }
        ObjectMapper oMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule("CustomCountrySerializer", new Version(1, 0, 0, null, null, null));
        simpleModule.addSerializer(Country.class, new CustomCountrySerializer());
        oMapper.registerModule(simpleModule);
        String countryJsonFromCustomSerializer = oMapper.writeValueAsString(country);
        System.out.println("demoCustomSerializer : " + countryJsonFromCustomSerializer);
    }

    public static void demoCustomDeSerializer() throws JsonMappingException, JsonProcessingException {

        class CustomCountryDeserializer extends StdDeserializer<Country> {

            private static final long serialVersionUID = 1L;

            public CustomCountryDeserializer() {
                this(null);
            }

            public CustomCountryDeserializer(Class<?> clazz) {
                super(clazz);
            }

            @Override
            public Country deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                    throws IOException {
                Country country = new Country();
                JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
                JsonNode customNameNode = jsonNode.get("customObjectName");
                String name = customNameNode.asText();
                country.setName(name);
                country.setNumberOfProvinces(Integer.MAX_VALUE);
                country.setPopulation(Long.MAX_VALUE);
                return country;
            }
        }
        String incompleteCountryJsonStr = "{\"customObjectName\":\"India\"}";
        ObjectMapper oMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule("CustomCountrySerializer", new Version(1, 0, 0, null, null, null));
        simpleModule.addDeserializer(Country.class, new CustomCountryDeserializer());
        oMapper.registerModule(simpleModule);
        Country country = oMapper.readValue(incompleteCountryJsonStr, Country.class);
        System.out.println("demoCustomDeSerializer : " + country);
    }
}
