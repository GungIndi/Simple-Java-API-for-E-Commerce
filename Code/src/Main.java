import java.net.InetSocketAddress;
// import java.util.concurrent.Executor;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.Executors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        try{
            HttpServer httpserver = HttpServer.create(new InetSocketAddress("localhost", 8079),0);
                httpserver.createContext("/", new RequestHandler());
                httpserver.setExecutor(Executors.newSingleThreadExecutor());
                httpserver.start();
            System.out.println("Listening on port 8079...");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static class RequestHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException{
            String path = exchange.getRequestURI().getPath();
            System.out.println("query = " + exchange.getRequestURI().getQuery());
            System.out.println("Request path: " + path);
            if (path.startsWith("/user")) {
                handleUserRequest(exchange, path);
            } else if (path.startsWith("/admin")) {
                handleAdminRequest(exchange, path);
            } else {
                sendResponse(exchange, "<h1>MAAF PATH YANG KAMU MASUKAN SALAH</h1>");
            }
        }
        private void handleUserRequest(HttpExchange exchange,String path) throws IOException{
            Pattern pattern = Pattern.compile("^/user/(\\d+)$");
            Matcher matcher = pattern.matcher(path);
            if (matcher.matches()) {
                String id = matcher.group(1);
                System.out.println("HALO USER DENGAN ID "+ id);
            }
            if("GET".equals(exchange.getRequestMethod())){
                OutputStream outputstream = exchange.getResponseBody();
                String response = "<h1> HALO USER KAMU BERHASIL! </h1>";
                exchange.sendResponseHeaders(200,response.length());
                outputstream.write(response.getBytes());
                outputstream.flush();
                outputstream.close();   
            }
        }

        private void handleAdminRequest(HttpExchange exchange,String path) throws IOException{
            Pattern pattern = Pattern.compile("^/user/(\\d+)$");
            Matcher matcher = pattern.matcher(path);
            if (matcher.matches()) {
                String id = matcher.group(1);
                System.out.println("HALO ADMIN DENGAN ID "+ id);
            }
            if("GET".equals(exchange.getRequestMethod())){
                OutputStream outputstream = exchange.getResponseBody();
                String response = "<h1> HALO ADMIN KAMU BERHASIL! </h1>";
                exchange.sendResponseHeaders(200,response.length());
                outputstream.write(response.getBytes());
                outputstream.flush();
                outputstream.close();   
            }
        }
        private void sendResponse(HttpExchange exchange, String response) throws IOException {
            OutputStream outputStream = exchange.getResponseBody();
            exchange.sendResponseHeaders(200, response.length());
            outputStream.write(response.getBytes());
            outputStream.flush();
            outputStream.close();
        }
    }
}
