package com.example;

// import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class Data {
  public JSONObject selectDatabase(String[] path, String query){
    try {
      Connection connection = DriverManager.getConnection("jdbc:sqlite:/home/indianajones/Documents/college/pbo/Simple-Java-API-for-E-Commerce/ecommerce.db");
      Statement statement = connection.createStatement();
      System.out.println("connection berhasil");
      if(query != null){
        if(path[1].equals("users")){
          JSONObject jsonObject = new JSONObject();
          JSONArray array = new JSONArray();
          // String[] paramQuery = query.split("?");
          ResultSet rs = statement.executeQuery("select * from " + path[1] );
          while(rs.next()) {
            JSONObject record = new JSONObject();
            record.put("id", rs.getInt("id"));
            record.put("First_Name", rs.getString("first_name"));
            record.put("Last_Name", rs.getString("last_name"));
            record.put("Email", rs.getString("email"));
            record.put("Phone Number", rs.getString("phone_number"));
            record.put("Type", rs.getString("type"));
            array.add(record);
          }
          jsonObject.put("User Information", array);
          return jsonObject;
        }
      }
      System.out.println(query);
      if(path[1].equals("users")){
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        if(path.length == 2){
          ResultSet rs = statement.executeQuery("select * from " + path[1]);
          while(rs.next()) {
            JSONObject record = new JSONObject();
            record.put("id", rs.getInt("id"));
            record.put("First_Name", rs.getString("first_name"));
            record.put("Last_Name", rs.getString("last_name"));
            record.put("Email", rs.getString("email"));
            record.put("Phone Number", rs.getString("phone_number"));
            record.put("Type", rs.getString("type"));
            array.add(record);
          }
          jsonObject.put("User Information", array);
          return jsonObject;
        }
        if(path.length == 3){
          ResultSet rs = statement.executeQuery("select * from " + path[1] + " where id=" + path[2]);
          while(rs.next()) {
            JSONObject record = new JSONObject();
            record.put("id", rs.getInt("id"));
            record.put("First_Name", rs.getString("first_name"));
            record.put("Last_Name", rs.getString("last_name"));
            record.put("Email", rs.getString("email"));
            record.put("Phone Number", rs.getString("phone_number"));
            record.put("Type", rs.getString("type"));
            array.add(record);
          }
          jsonObject.put("User Information", array);
          return jsonObject;
        }
        if(path.length == 4){
          if(path[3].equals("orders")){
            ResultSet rs = statement.executeQuery("select users.id, users.first_name, users.last_name, orders.note, products.title, order_details.quantity, order_details.price from orders inner join users on orders.id_buyer = users.id inner join order_details on order_details.id_order = orders.id inner join products on id_products = order_details.id_product where orders.id=" + path[2]);
            while(rs.next()) {
              JSONObject record = new JSONObject();
              record.put("id", rs.getInt("id"));
              record.put("First_Name", rs.getString("first_name"));
              record.put("Last_Name", rs.getString("last_name"));
              record.put("Note", rs.getString("note"));
              record.put("Title", rs.getString("title"));
              record.put("Quantity", rs.getString("quantity"));
              record.put("Price", rs.getString("price"));
              array.add(record);
            }
            jsonObject.put("User Information", array);
            return jsonObject;
          }
          if(path[3].equals("products")){
            ResultSet rs = statement.executeQuery("select users.id, users.first_name, users.last_name, products.title,products.price,products.description, products.stock from products inner join users on products.id_seller = users.id where products.id_products=" + path[2]);
            while(rs.next()) {
              JSONObject record = new JSONObject();
              record.put("id", rs.getInt("id"));
              record.put("First_Name", rs.getString("first_name"));
              record.put("Last_Name", rs.getString("last_name"));
              record.put("Title", rs.getString("title"));
              record.put("Price", rs.getInt("price"));
              record.put("Description", rs.getString("description"));
              record.put("Stock", rs.getInt("stock"));
              array.add(record);
            }
            jsonObject.put("User Information", array);
            return jsonObject;
          }
          if(path[3].equals("reviews")){
            ResultSet rs = statement.executeQuery("select users.id, users.first_name, users.last_name,reviews.star,reviews.description from reviews inner join orders on reviews.id_order = orders.id inner join users on orders.id_buyer = users.id where reviews.id_order="+ path[2]);
            while(rs.next()) {
              JSONObject record = new JSONObject();
              record.put("id", rs.getInt("id"));
              record.put("First_Name", rs.getString("first_name"));
              record.put("Last_Name", rs.getString("last_name"));
              record.put("Star", rs.getString("star"));
              record.put("Description", rs.getString("description"));
              array.add(record);
            }
            jsonObject.put("User Information", array);
            return jsonObject;
          }  
        }
      }
      else if(path[1].equals("products")){
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        System.out.println(query);
        if(path.length == 2){
          ResultSet rs = statement.executeQuery("select * from " + path[1]);
          while(rs.next()) {
            JSONObject record = new JSONObject();
            record.put("Stock", rs.getInt("stock"));
            record.put("Price", rs.getString("price"));
            record.put("Description", rs.getString("description"));
            record.put("Title", rs.getString("title"));
            record.put("Id", rs.getInt("id_products"));
            array.add(record);
          }
          jsonObject.put("Product Information", array);
          return jsonObject;
        }
        if(path.length == 3){
          ResultSet rs = statement.executeQuery("select * from " + path[1] + " where id_products=" + path[2]);
          while(rs.next()) {
            JSONObject record = new JSONObject();
            record.put("Stock", rs.getInt("stock"));
            record.put("Price", rs.getString("price"));
            record.put("Description", rs.getString("description"));
            record.put("Title", rs.getString("title"));
            record.put("Id", rs.getInt("id_products"));
            array.add(record);
          }
          jsonObject.put("Product Information", array);
          return jsonObject;
        }
      }
      else if(path[1].equals("orders")){
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        if(path.length == 2){
          ResultSet rs = statement.executeQuery("select * from " + path[1]);
          while(rs.next()) {
            JSONObject record = new JSONObject();
            record.put("isPaid", rs.getInt("isPaid"));
            record.put("Discount", rs.getInt("discount"));
            record.put("Total", rs.getInt("total"));
            record.put("Note", rs.getInt("note"));
            record.put("Id", rs.getInt("id"));
            array.add(record);
          }
          jsonObject.put("Orders Information", array);
          return jsonObject;
        }
        if(path.length == 3){
          ResultSet rs = statement.executeQuery("select * from " + path[1] + " where id=" + path[2]);
          while(rs.next()) {
            JSONObject record = new JSONObject();
            record.put("isPaid", rs.getInt("isPaid"));
            record.put("Discount", rs.getInt("discount"));
            record.put("Total", rs.getInt("total"));
            record.put("Note", rs.getInt("note"));
            record.put("Id", rs.getInt("id"));
            array.add(record);
          }
          jsonObject.put("Orders Information", array);
          return jsonObject;
        }
      }
      
    } catch(SQLException e) {
      System.err.println(e.getMessage());
    }
    return null;
  }
  // private JSONObject getData(JSONObject object, JSONArray array, ResultSet rs){

  // }
}