package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class Data {
  private Connection connection;

  public JSONObject selectData(String[] path, String query){
    try {
      Statement statement = getConnection().createStatement();
      System.out.println("Connection Succes..");
      if(query != null){
        if(path[1].equals("users")){
          JSONObject jsonObject = new JSONObject();
          JSONArray array = new JSONArray();
          ResultSet rs = statement.executeQuery("select * from " + path[1] + " inner join address on address.id_users = users.id where " + query);;
          while(rs.next()) {
            JSONObject record = new JSONObject();
            record.put("Id", rs.getInt("id"));
            record.put("First_Name", rs.getString("first_name"));
            record.put("Last_Name", rs.getString("last_name"));
            record.put("Email", rs.getString("email"));
            record.put("Phone_Number", rs.getString("phone_number"));
            record.put("Type", rs.getString("type"));
            record.put("Address", rs.getString("address"));
            record.put("City", rs.getString("city"));
            record.put("Province", rs.getString("province"));
            array.add(record);
          }
          jsonObject.put("User Information", array);
          return jsonObject;
        }
      }

      if(path[1].equals("users")){
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        if(path.length == 2){
          ResultSet rs = statement.executeQuery("select * from " + path[1]);
          while(rs.next()) {
            JSONObject record = new JSONObject();
            record.put("Id", rs.getInt("id"));
            record.put("First_Name", rs.getString("first_name"));
            record.put("Last_Name", rs.getString("last_name"));
            record.put("Email", rs.getString("email"));
            record.put("Phone_Number", rs.getString("phone_number"));
            record.put("Type", rs.getString("type"));
            array.add(record);
          }
          jsonObject.put("User Information", array);
          return jsonObject;
        }
        if(path.length == 3){
          ResultSet rs = statement.executeQuery("select * from " + path[1] + " inner join address on address.id_users = users.id where id=" + path[2]);
          while(rs.next()) {
            JSONObject record = new JSONObject();
            record.put("Id", rs.getInt("id"));
            record.put("First_Name", rs.getString("first_name"));
            record.put("Last_Name", rs.getString("last_name"));
            record.put("Email", rs.getString("email"));
            record.put("Phone_Number", rs.getString("phone_number"));
            record.put("Type", rs.getString("type"));
            record.put("Address", rs.getString("address"));
            record.put("City", rs.getString("city"));
            record.put("Province", rs.getString("province"));
            array.add(record);
          }
          jsonObject.put("User Information", array);
          return jsonObject;
        }
        if(path.length == 4){
          if(path[3].equals("orders")){
            ResultSet rs = statement.executeQuery("select users.id, users.first_name, users.last_name, orders.note, products.title, order_details.quantity, order_details.price from orders inner join users on orders.id_buyer = users.id inner join order_details on order_details.id_order = orders.id inner join products on id_products = order_details.id_product where orders.id_buyer=" + path[2]);
            while(rs.next()) {
              JSONObject record = new JSONObject();
              record.put("Id", rs.getInt("id"));
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
            ResultSet rs = statement.executeQuery("select users.id, users.first_name, users.last_name, products.title,products.price,products.description, products.stock from products inner join users on products.id_seller = users.id where products.id_seller=" + path[2]);
            while(rs.next()) {
              JSONObject record = new JSONObject();
              record.put("Id", rs.getInt("id"));
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
            ResultSet rs = statement.executeQuery("select users.id, users.first_name, users.last_name,reviews.star,reviews.description from reviews inner join orders on reviews.id_order = orders.id inner join users on orders.id_buyer = users.id where orders.id_buyer="+ path[2]);
            while(rs.next()) {
              JSONObject record = new JSONObject();
              record.put("Id", rs.getInt("id"));
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
            record.put("Id_Seller", rs.getInt("id_seller"));
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
            record.put("Id_Seller", rs.getInt("id_seller"));
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
            record.put("Id_Buyer", rs.getInt("id_buyer"));
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
            record.put("Id_Buyer", rs.getInt("id_buyer"));
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
  
  public String postData(JSONObject requestBodyJson, String[] path){
    if(path[1].equals("users") && requestBodyJson.size() == 5){
      String first_name = (String) requestBodyJson.get("First_Name");
      String last_name = (String) requestBodyJson.get("Last_Name");
      String email = (String) requestBodyJson.get("Email");
      String phone_number = (String) requestBodyJson.get("Phone_Number");
      String type = (String) requestBodyJson.get("Type");
      PreparedStatement statement = null;
      int rowsAffected = 0;
      String query = "INSERT INTO users(first_name, last_name, email, phone_number, type) VALUES(?,?,?,?,?)";
      try {
          statement = getConnection().prepareStatement(query);
          statement.setString(1, first_name);
          statement.setString(2, last_name);
          statement.setString(3, email);
          statement.setString(4, phone_number);
          statement.setString(5, type);
          rowsAffected = statement.executeUpdate();
      }catch (SQLException e){
          e.printStackTrace();
      }
      System.out.println(rowsAffected + " rows inserted!");
      return rowsAffected + " rows inserted!";
    }
    else if(path[1].equals("orders") && requestBodyJson.size() == 5){
      int id_buyer = Integer.parseInt(requestBodyJson.get("Id_Buyer").toString());
      int note = Integer.parseInt(requestBodyJson.get("Note").toString());
      int total = Integer.parseInt(requestBodyJson.get("Total").toString());
      int discount = Integer.parseInt(requestBodyJson.get("Discount").toString());
      int isPaid = Integer.parseInt(requestBodyJson.get("isPaid").toString());
      PreparedStatement statement = null;
      int rowsAffected = 0;
      String query = "INSERT INTO orders(id_buyer, note, total, discount, isPaid) VALUES(?,?,?,?,?)";
      try {
          statement = getConnection().prepareStatement(query);
          statement.setInt(1, id_buyer);
          statement.setInt(2, note);
          statement.setInt(3, total);
          statement.setInt(4, discount);
          statement.setInt(5, isPaid);
          rowsAffected = statement.executeUpdate();
      }catch (SQLException e){
          e.printStackTrace();
      }
      System.out.println(rowsAffected + " rows inserted!");
      return rowsAffected + " rows inserted!";
    }
    else if(path[1].equals("products") && requestBodyJson.size() == 5){
      int id_seller = Integer.parseInt(requestBodyJson.get("Id_Seller").toString());
      String title = requestBodyJson.get("Title").toString();
      String description = requestBodyJson.get("Description").toString();
      int price = Integer.parseInt(requestBodyJson.get("Price").toString());
      int stock = Integer.parseInt(requestBodyJson.get("Stock").toString());
      PreparedStatement statement = null;
      int rowsAffected = 0;
      String query = "INSERT INTO products(id_seller, title, description, price, stock) VALUES(?,?,?,?,?)";
      try {
          statement = getConnection().prepareStatement(query);
          statement.setInt(1, id_seller);
          statement.setString(2, title);
          statement.setString(3, description);
          statement.setInt(4, price);
          statement.setInt(5, stock);
          rowsAffected = statement.executeUpdate();
      }catch (SQLException e){
          e.printStackTrace();
      }
      System.out.println(rowsAffected + " rows inserted!");
      return rowsAffected + " rows inserted!";
    } else{
      return "Data tidak sesuai!";
    }
  }
  
  public String putData(JSONObject requestBodyJson, String[] path){
    if(path[1].equals("users") && requestBodyJson.size() == 4){
      String first_name = (String) requestBodyJson.get("First_Name");
      String last_name = (String) requestBodyJson.get("Last_Name");
      String email = (String) requestBodyJson.get("Email");
      String phone_number = (String) requestBodyJson.get("Phone_Number");
      String type = (String) requestBodyJson.get("Type");
      PreparedStatement statement = null;
      int rowsAffected = 0;
      String query = "UPDATE users SET first_name = ?, last_name = ?, email = ?, phone_number = ?, type = ? WHERE id=" + path[2];
      try {
          statement = getConnection().prepareStatement(query);
          statement.setString(1, first_name);
          statement.setString(2, last_name);
          statement.setString(3, email);
          statement.setString(4, phone_number);
          statement.setString(5, type);
          rowsAffected = statement.executeUpdate();
      }catch (SQLException e){
          e.printStackTrace();
      }
      System.out.println(rowsAffected + " rows updated!");
      return rowsAffected + " rows updated!";
    }
    else if(path[1].equals("orders") && requestBodyJson.size() == 5){
      int id_buyer = Integer.parseInt(requestBodyJson.get("Id_Buyer").toString());
      int note = Integer.parseInt(requestBodyJson.get("Note").toString());
      int total = Integer.parseInt(requestBodyJson.get("Total").toString());
      int discount = Integer.parseInt(requestBodyJson.get("Discount").toString());
      int isPaid = Integer.parseInt(requestBodyJson.get("isPaid").toString());
      PreparedStatement statement = null;
      int rowsAffected = 0;
      String query = "UPDATE orders SET id_buyer = ?, note = ?, total = ?, discount = ?, isPaid = ? WHERE id=" + path[2];
      try {
          statement = getConnection().prepareStatement(query);
          statement.setInt(1, id_buyer);
          statement.setInt(2, note);
          statement.setInt(3, total);
          statement.setInt(4, discount);
          statement.setInt(5, isPaid);
          rowsAffected = statement.executeUpdate();
      }catch (SQLException e){
          e.printStackTrace();
      }
      System.out.println(rowsAffected + " rows updated!");
      return rowsAffected + " rows updated!";
    }
    else if(path[1].equals("products") && requestBodyJson.size() == 5){
      int id_seller = Integer.parseInt(requestBodyJson.get("Id_Seller").toString());
      String title = requestBodyJson.get("Title").toString();
      String description = requestBodyJson.get("Description").toString();
      int price = Integer.parseInt(requestBodyJson.get("Price").toString());
      int stock = Integer.parseInt(requestBodyJson.get("Stock").toString());
      PreparedStatement statement = null;
      int rowsAffected = 0;
      String query = "UPDATE products SET id_seller = ?, title = ?, description = ?,  price = ?, stock = ? WHERE id_products=" + path[2];
      try {
          statement = getConnection().prepareStatement(query);
          statement.setInt(1, id_seller);
          statement.setString(2, title);
          statement.setString(3, description);
          statement.setInt(4, price);
          statement.setInt(5, stock);
          rowsAffected = statement.executeUpdate();
      }catch (SQLException e){
          e.printStackTrace();
      }
      System.out.println(rowsAffected + " rows updated!");
      return rowsAffected + " rows updated!";
    }
    return null;
  } 

  public String deleteData(String[] path){
    PreparedStatement statement = null;
    int rowsAffected = 0;
    try {
        String query = "DELETE FROM " + path[1] + " WHERE id=" + path[2];
        statement = getConnection().prepareStatement(query);
        rowsAffected = statement.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e);
    }
    return rowsAffected + " rows deleted!";
  } 

  public Connection getConnection() throws SQLException{
      try {
        this.connection = null;
        String rootPath = System.getProperty("user.dir");
        String url = "jdbc:sqlite:" + rootPath + "/ecommerce.db";
        System.out.println(url);
        this.connection = DriverManager.getConnection(url);
    }catch (SQLException e){
        System.out.println(e.getMessage());
    }
    return this.connection;
  }

}

