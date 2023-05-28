package com.example;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sun.net.httpserver.HttpExchange;

public  class Request {
    public void handleGetRequest(HttpExchange exchange, String[] path, String query) throws IOException{
        Response response = new Response();
        Data data = new Data();
        String[] hasil={};
        if ("users".equals(path[1])) {
            data.getConnection(path);
            // for(String i : hasil){
            //     System.out.println(i);
            // }
            response.getResponse(exchange, path, "USERS",200);
        } else if ("orders".equals(path[1])) {
            response.getResponse(exchange, path, "ORDERS", 200);
        } else if ("products".equals(path[1])) {
            response.getResponse(exchange, path, "PRODUCTS", 200);
        }else {
            response.sendResponse(exchange, "\n<h1><center>404</center></h1>\n<h3><center>Not Found!</center></h3>");
        }
    }

    public void handlePostRequest(){

    }

    public void handlePutRequest(){

    }

    public void handleDeleteRequest(){

    }
    
}