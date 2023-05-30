# Simple-Java-API-for-E-Commerce

Anak Agung Indi Kusuma Putra
2205551079

## About
Program Simple Java API for E-Commerce merupakan program  API Server dalam bahasa java yang digunakan untuk menghubungkan request dari user ke database yang berbasis sqlite .

## Alur Program
<br>

### Otorisasi API-Key

Untuk otorisasi API-Key pada API ini saya membuat method apiAuthorization() yang berisi kode sebagi berikut

```
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
```

Kode Program Diatas akan mengambil API-Key yang ada pada request headers yang dikirim oleh user dan akan membandingkanya dengan API-Key yang sudah ada pada file `.env` dimana jika valuenya sama, maka akan me return true dan jika tidak sesuai maka akan me return false

### Melihat Semua User

Untuk melihat semua user yang ada pada server. bisa dengan mengirimkan url berupa  `http:localhost:8079/users` dengan method berupa `GET` dan nantinya akan mendapatkan data JSON yang berisi object berupa kolom dan value yang ada pada database ecommerce.db. JSON yang didapat sebagai berikut

```
{
    "User Information": [
        {
            "First_Name": "Candra",
            "Type": "Seller",
            "Email": "candrawijaya@email.com",
            "Last_Name": "Wijaya",
            "Phone_Number": "12354",
            "Id": 2
        },
        {
            "First_Name": "Bayu",
            "Type": "Buyer",
            "Email": "bayuriki@email.com",
            "Last_Name": "Riki",
            "Phone_Number": "54321",
            "Id": 3
        },
        {
            "First_Name": "Sunia",
            "Type": "Buyer",
            "Email": "suniadevta@email.com",
            "Last_Name": "Devta",
            "Phone_Number": "54231",
            "Id": 4
        },
        {
            "First_Name": "Tude",
            "Type": "Seller",
            "Email": "tudeprayatna@email.com",
            "Last_Name": "Prayatna",
            "Phone_Number": "53421",
            "Id": 5
        },
        {
            "First_Name": "Sukma",
            "Type": "Buyer",
            "Email": "sukmanigraha@email.com",
            "Last_Name": "Nigraha",
            "Phone_Number": "44444",
            "Id": 6
        },
        {
            "First_Name": "Liangga",
            "Type": "Seller",
            "Email": "lianggaristiana@email.com",
            "Last_Name": "Ristiana",
            "Phone_Number": "23415",
            "Id": 7
        },
        {
            "First_Name": "Sindhu",
            "Type": "Seller",
            "Email": "sindhuwedana@email.com",
            "Last_Name": "Wedana",
            "Phone_Number": "13245",
            "Id": 8
        },
        {
            "First_Name": "Davin",
            "Type": "Seller",
            "Email": "davinditya@email.com",
            "Last_Name": "Ditya",
            "Phone_Number": "35412",
            "Id": 9
        },
        {
            "First_Name": "Putu",
            "Type": "Buyer",
            "Email": "putuputri@email.com",
            "Last_Name": "Putri",
            "Phone_Number": "23232",
            "Id": 10
        }
    ]
}
```

### Melihat User dengan Id tertentu

Untuk melihat user dengan id tertentu bisa dengan mengirimkan request url berupa `http:localhost:8079/users/10` dengan method