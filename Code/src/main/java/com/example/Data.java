package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.net.httpserver.HttpExchange;

public class Data {
  public void getConnection(String[] path){
    Connection connection = null;
    try {
        connection = DriverManager.getConnection("path to your db");
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30); // Wait only 30 seconds to connect
        ResultSet rs = statement.executeQuery("select * from " + path[1]);
        while(rs.next()) {
          // Read the entered data
          System.out.println("ID : " + rs.getInt("id"));
          System.out.println("Name : " + rs.getString("first_name"));
        }
      } catch(SQLException e) {
        // If the error message is: "out of memory",
        // Probably error creating(permission) or database path
        System.err.println(e.getMessage());
      }
      finally {
        try {
          if(connection != null){
            connection.close();
          }
        } catch(SQLException e) {
          // Also failed to close the file
          System.err.println(e.getMessage());
        }
      }
  }
}
