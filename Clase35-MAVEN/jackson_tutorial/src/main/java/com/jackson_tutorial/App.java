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
        
        ObjectMapperUtils.objectToJsonString();
        ObjectMapperUtils.objectToJsonInFile();
        ObjectMapperUtils.objectToBytes();
        ObjectMapperUtils.jsonStrToObject();
        ObjectMapperUtils.jsonInFileToObject();
        ObjectMapperUtils.readJsonStringAsMap();
        ObjectMapperUtils.readJsonArrayStringAsList();
        ObjectMapperUtils.parseJsonStringAsJsonNode();
        ObjectMapperUtils.createJsonNodeStr();
        ObjectMapperUtils.demoUnknownField();
        ObjectMapperUtils.demoNullPrimitiveValues();
        ObjectMapperUtils.objectWithDateToJsonString();
        ObjectMapperUtils.jsonStringWithDateToObject();
        ObjectMapperUtils.demoCustomSerializer();
        ObjectMapperUtils.demoCustomDeSerializer();
    }
}
