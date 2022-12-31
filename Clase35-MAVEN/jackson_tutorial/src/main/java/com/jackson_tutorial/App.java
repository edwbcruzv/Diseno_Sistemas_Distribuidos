package com.jackson_tutorial;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println("Iniciando demostracion...\n");

        //String, Bytes y JSON
        ObjectMapperUtils.objectToJsonString();
        ObjectMapperUtils.objectToBytes();
        ObjectMapperUtils.objectToJsonInFile();
        ObjectMapperUtils.jsonStrToObject();
        ObjectMapperUtils.jsonInFileToObject();

        // Colecciones
        ObjectMapperUtils.readJsonStringAsMap();
        ObjectMapperUtils.readJsonArrayStringAsList();

        // jsonNode
        ObjectMapperUtils.parseJsonStringAsJsonNode();
        ObjectMapperUtils.createJsonNodeStr();
        ObjectMapperUtils.demoUnknownField();
        ObjectMapperUtils.demoNullPrimitiveValues();

        //Fechas
        ObjectMapperUtils.objectWithDateToJsonString();
        ObjectMapperUtils.jsonStringWithDateToObject();

        // Serializacion y Deserializacion (Personalizado)
        ObjectMapperUtils.demoCustomSerializer();
        ObjectMapperUtils.demoCustomDeSerializer();
    }
}
