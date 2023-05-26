import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
// import java.io.OutputStream;
import com.sun.net.httpserver.HttpExchange;

public  class Request implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException{
        String[] path = exchange.getRequestURI().getPath().split("/");
        String query = exchange.getRequestURI().getQuery();
        if("GET".equals(exchange.getRequestMethod())){
            handleGetRequest(exchange,path,query);
        }
        if("POST".equals(exchange.getRequestMethod())){
            // handleGetRequest(exchange,path,query);
        }
        if("PUT".equals(exchange.getRequestMethod())){
            // handleGetRequest(exchange,path,query);
        }
        if("DELETE".equals(exchange.getRequestMethod())){
            // handleGetRequest(exchange,path,query);
        }
    }
    private void handleGetRequest(HttpExchange exchange, String[] path, String query) throws IOException{
        Response response = new Response();
        if ("users".equals(path[1])) {
            response.getResponse(exchange, path, "USERS",200);
        } else if ("orders".equals(path[1])) {
            response.getResponse(exchange, path, "ORDERS", 200);
        } else if ("products".equals(path[1])) {
            response.getResponse(exchange, path, "PRODUCTS", 200);
        }else {
            response.sendResponse(exchange, "<h1>MAAF PATH YANG KAMU MASUKAN SALAH</h1>");
        }
    }
}
