package com.example;

import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// import java.io.OutputStream;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

public  class Server implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException{
        String[] path = exchange.getRequestURI().getPath().split("/");
        String query = exchange.getRequestURI().getQuery();
        if(!apiAuthorization(exchange)){
            Response response = new Response();
            response.getResponse(exchange, "\n<h1><center>400</center></h1>\n<h3><center>Bad Request!</center></h3>\n<h3>", 400);
            System.exit(0);
        }
        Request request = new Request();
        if("GET".equals(exchange.getRequestMethod())){
            request.handleGetRequest(exchange,path,query);
        }
        if("POST".equals(exchange.getRequestMethod())){
            try {
                request.handlePostRequest(exchange,path,query);
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
        }
        if("PUT".equals(exchange.getRequestMethod())){
            try {
                request.handlePutRequest(exchange,path,query);
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
        }
        if("DELETE".equals(exchange.getRequestMethod())){
            try {
                request.handleDeleteRequest(exchange,path,query);
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static Boolean apiAuthorization(HttpExchange exchange) throws FileNotFoundException{
        Headers requestHeaders = exchange.getRequestHeaders();
        String headersKey = "x-api-key:"+requestHeaders.getFirst("x-api-key");
        File file = new File(".env");
        String api_key = null;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains("x-api-key")) {
                api_key = line;
            } else{
                api_key = "NULL";
            }
        }
        scanner.close();
        if(headersKey.equals(api_key)){
            return true;
        }
        return false;
    }
}