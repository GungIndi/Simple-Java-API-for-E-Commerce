package com.example;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;

public class Response {
    public void getResponse(HttpExchange exchange,String response, int code) throws IOException{
            OutputStream outputstream = exchange.getResponseBody();
            exchange.getResponseHeaders().set("Content-Type","application/json");
            
            exchange.sendResponseHeaders(code,response.length());
            outputstream.write(response.getBytes());
            outputstream.flush();
            outputstream.close();   
    }

    public void sendResponse(HttpExchange exchange, String response) throws IOException {
        OutputStream outputStream = exchange.getResponseBody();
        exchange.sendResponseHeaders(404, response.length());
        outputStream.write(response.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
