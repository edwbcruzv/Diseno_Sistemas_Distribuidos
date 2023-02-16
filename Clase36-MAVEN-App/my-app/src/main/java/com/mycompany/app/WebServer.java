/*
 *  MIT License
 *
 *  Copyright (c) 2019 Michael Pogrebinsky - Distributed Systems & Cloud Computing with Java
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package com.mycompany.app;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.io.InputStream;  
import java.util.StringTokenizer;

import com.fasterxml.jackson.databind.DeserializationFeature;   
import com.fasterxml.jackson.databind.ObjectMapper;             
import com.fasterxml.jackson.databind.PropertyNamingStrategy;   

public class WebServer {
   
    private static final String STATUS_ENDPOINT = "/status";
    private static final String HOME_ENDPOINT = "/";
    private static final String UI_ASSETS_BASE_DIR = "/ui_assets/"; // no es un endpint es la ruta de los assets (html,css,js)
    private static final String PROCESAR_DATOS_ENDPOINT = "/procesar_datos";

    private final int port; 
    private HttpServer server; 
    private final ObjectMapper objectMapper;

    public WebServer(int port) {
        this.port = port;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public void startServer() {
        try {
            this.server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        HttpContext statusContext = server.createContext(STATUS_ENDPOINT); 
        HttpContext procesarDatosContext = server.createContext(PROCESAR_DATOS_ENDPOINT);
        HttpContext homeContext = server.createContext(HOME_ENDPOINT);
        statusContext.setHandler(this::statusHandlerReq);
        procesarDatosContext.setHandler(this::procesarDatosHandlerReq);
        homeContext.setHandler(this::homeHandlerReq);

        server.setExecutor(Executors.newFixedThreadPool(8));
        server.start();
    }

    private void homeHandlerReq(HttpExchange exchange) throws IOException {
        System.out.println("Metodo en " + this.port + " homeHandlerReq:" + exchange.getRequestMethod());
        if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
            exchange.close();
            return;
        }

        byte[] response;

        String asset = exchange.getRequestURI().getPath(); 

        if (asset.equals(HOME_ENDPOINT)) { 
            response = readUiAsset(UI_ASSETS_BASE_DIR + "index.html");
        } else {
            response = readUiAsset(asset); 
        }
        addContentType(asset, exchange);
        sendResponse(response, exchange);
    }

    private byte[] readUiAsset(String asset) throws IOException {
        InputStream assetStream = getClass().getResourceAsStream(asset);

        if (assetStream == null) {
            return new byte[]{};
        }
        return assetStream.readAllBytes(); 
    }

    private static void addContentType(String asset, HttpExchange exchange) {

        String contentType = "text/html";  
        if (asset.endsWith("js")) {
            contentType = "text/javascript";
        } else if (asset.endsWith("css")) {
            contentType = "text/css";
        }
        exchange.getResponseHeaders().add("Content-Type", contentType);
    }

    private void procesarDatosHandlerReq(HttpExchange exchange) throws IOException {
        System.out.println("Metodo en " + this.port + " procesarDatosHandlerReq:" + exchange.getRequestMethod());
        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) { 
            exchange.close();
            return;
        }

        try {
            FrontendSearchRequest frontendSearchRequest = objectMapper.readValue(exchange.getRequestBody().readAllBytes(), FrontendSearchRequest.class); 
            String frase = frontendSearchRequest.getSearchQuery();
            System.out.println("PRocesando frase:"+frase);
            StringTokenizer st = new StringTokenizer(frase);
            FrontendSearchResponse frontendSearchResponse = new FrontendSearchResponse(frase, st.countTokens());
            byte[] responseBytes = objectMapper.writeValueAsBytes(frontendSearchResponse);
            sendResponse(responseBytes, exchange);

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    private void statusHandlerReq(HttpExchange exchange) throws IOException {
        System.out.println("Metodo en " + this.port + " statusHandlerReq:" + exchange.getRequestMethod());
        if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
            exchange.close();
            return;
        }

        String responseMessage = "El servidor está vivo\n";
        sendResponse(responseMessage.getBytes(), exchange);
    }

    private void sendResponse(byte[] responseBytes, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, responseBytes.length);
        System.out.println("bytes enviados:"+ responseBytes.length);
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(responseBytes);
        outputStream.flush();
        outputStream.close();
    }
}


