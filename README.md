# Simple-Java-API-for-E-Commerce

Anak Agung Indi Kusuma Putra
2205551079

## About

Program Simple Java API for E-Commerce merupakan program  API Server dalam bahasa java yang digunakan untuk menghubungkan request dari user ke database yang berbasis sqlite .

## Alur Program

Simplenya program ini akan membuka port 8079 di localhost dan akan menjadi http server untuk menerima http request yang dikirim oleh user
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

Kode Program di atas akan mengambil API-Key yang ada pada request headers yang dikirim oleh user dan akan membandingkanya dengan API-Key yang sudah ada pada file `.env` dimana jika valuenya sama, maka akan me return true dan jika tidak sesuai maka akan me return false

### Melihat Semua User

Untuk melihat semua user yang ada pada server bisa dengan mengirimkan url berupa  `http:localhost:8079/users` dengan method berupa `GET` dan nantinya akan mendapatkan data JSON yang berisi object berupa kolom dan value yang ada pada database ecommerce.db. JSON yang didapat sebagai berikut


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


```
{
    "User Information": [
        {
            "First_Name": "Candra",
            "Type": "Seller",
            "Email": "candrawijaya@email.com",
            "Address": "Jalan Matahari",
            "Last_Name": "Wijaya",
            "Phone_Number": "12354",
            "Id": 2,
            "City": "Denpasar",
            "Province": "Bali"
        }
    ]
}
```

### Melihat Daftar Produk dengan Id User Tertentu

Untuk dapat melihat produk bedasarkan pada id user tertentu maka diaplikasikan pengiriman request url `http:localhost:8079/users/5/products` seperti di bawah ini


```
{
    "User Information": [
        {
            "First_Name": "Tude",
            "Description": "Topi Koboi",
            "Last_Name": "Prayatna",
            "Price": 50000,
            "Title": "Topi",
            "Id": 5,
            "Stock": 10
        }
    ]
}

```
### Melihat Daftar Order dengan Id User Tertentu

Untuk dapat melihat daftar order yang didasarkan pada id user tertentu maka diaplikasikan pengiriman request url `http:localhost:8079/users/10/orders` seperti yang terlihat di bawah ini

```
{
    "User Information": [
        {
            "First_Name": "Putu",
            "Last_Name": "Putri",
            "Price": "50000",
            "Note": "Tas",
            "Title": "Tas",
            "Quantity": "1",
            "Id": 10
        }
    ]
}
```
### Melihat Review Milik Id User Tertentu

Untuk dapat melihat review yang dimiliki id user tertentu maka dapat dengan mengirimkan request url `http:localhost:8079/users/10/reviews` seperti di bawah ini

```
{
    "User Information": [
        {
            "First_Name": "Putu",
            "Description": "Bahan Bagus",
            "Last_Name": "Putri",
            "Star": "4",
            "Id": 10
        }
    ]
}
```

### Melihat Informasi Order, Buyer, Order Detail, Review, Product Title, dan Price

 Untuk menampakkan informasi order, buyer, order detail, review, product title, dan price dapat dilakukan dengan menirimkan request url `http:localhost:8079/orders/5` seperti yang terlihat di bawah ini

 ```
 {
    "Orders Information": [
        {
            "First_Name": "Putu",
            "isPaid": 1,
            "Discount": 0,
            "Price": 50000,
            "Last_Name": "Putri",
            "Id_Buyer": 10,
            "Total": 1,
            "Quantity": 1,
            "Title": "Tas",
            "Id": 2
        }
    ]
}
 ```
### Melihat Daftar Seluruh Produk

Untuk dapat melihat daftar seluruh informasi produk maka dapat dengan mengirimkan request url `http:localhost:8079/products` seperti di bawah ini

```
{
    "Product Information": [
        {
            "Description": "Baju Kemeja",
            "Price": "50000",
            "Title": "Baju",
            "Id": 1,
            "Stock": 5,
            "Id_Seller": 2
        },
        {
            "Description": "Topi Koboi",
            "Price": "50000",
            "Title": "Topi",
            "Id": 2,
            "Stock": 10,
            "Id_Seller": 5
        },
        {
            "Description": "Tas Gunung",
            "Price": "50000",
            "Title": "Tas",
            "Id": 3,
            "Stock": 10,
            "Id_Seller": 7
        },
        {
            "Description": "Sepatu Sneakers",
            "Price": "50000",
            "Title": "Sepatu",
            "Id": 4,
            "Stock": 14,
            "Id_Seller": 8
        },
        {
            "Description": "Kacamata Anti Radiasi",
            "Price": "50000",
            "Title": "Kacamata",
            "Id": 5,
            "Stock": 20,
            "Id_Seller": 9
        },
        {
            "Description": "Baju Kemeja",
            "Price": "50000",
            "Title": "Baju",
            "Id": 6,
            "Stock": 5,
            "Id_Seller": 7
        },
        {
            "Description": "Sandal Jepit",
            "Price": "50000",
            "Title": "Sandal",
            "Id": 7,
            "Stock": 10,
            "Id_Seller": 9
        }
    ]
}
```

### Melihat Daftar Produk dengan Id tertentu

Untuk dapat melihat produk dengan id tertentu dapat dengan mengirimkan request url `http:localhost:8079/products/3` sebagai contoh jika kita ingin melihat informasi dari produk dengan id 3. request url diatas akan mendapat response berupa data JSON dari produk seperti berikut

```
{
    "Product Information": [
        {
            "Description": "Tas Gunung",
            "Price": "50000",
            "Title": "Tas",
            "Id": 3,
            "Stock": 10,
            "Id_Seller": 7
        }
    ]
}
```


### Memfilter dengan Query Params

Untuk dapat melihat informasi seluruh user yang memiliki tipe buyer maka dapat dengan mengirimkan request url `http:localhost:8079/users?type="Buyer"`seperti di bawah ini

```
{
    "User Information": [
        {
            "First_Name": "Bayu",
            "Type": "Buyer",
            "Email": "bayuriki@email.com",
            "Address": "Jalan Meteor",
            "Last_Name": "Riki",
            "Phone_Number": "54321",
            "Id": 3,
            "City": "Denpasar",
            "Province": "Bali"
        },
        {
            "First_Name": "Sunia",
            "Type": "Buyer",
            "Email": "suniadevta@email.com",
            "Address": "Jalan Pluto",
            "Last_Name": "Devta",
            "Phone_Number": "54231",
            "Id": 4,
            "City": "Denpasar",
            "Province": "Bali"
        },
        {
            "First_Name": "Sukma",
            "Type": "Buyer",
            "Email": "sukmanigraha@email.com",
            "Address": "Jalan Asteroid",
            "Last_Name": "Nigraha",
            "Phone_Number": "44444",
            "Id": 6,
            "City": "Denpasar",
            "Province": "Bali"
        },
        {
            "First_Name": "Putu",
            "Type": "Buyer",
            "Email": "putuputri@email.com",
            "Address": "Jalan Venus",
            "Last_Name": "Putri",
            "Phone_Number": "23232",
            "Id": 10,
            "City": "Denpasar",
            "Province": "Bali"
        }
    ]
}
```

### Menambahkan Data ke Server dengan Format JSON

Untuk menambahkan data ke dalam server menggunakan API ini, maka kita bisa menggunakan postman dengan metode `POST` menggunakan request url seperti `http:localhost:8079/users`. lalu pada request body kita tambahkan data yang ingin kita tambahkan dalam bentuk JSON. disini data yang saya tambahkan sebagai berikut

```
{
    "First_Name": "Indi",
    "Type": "Buyer",
    "Email": "indiputra@email.com",
    "Last_Name": "Putra",
    "Phone_Number" : "32121"
}
```
<br>

### Mengupdate Data ke Server dengan Format JSON

Untuk mengubah data yang ada dalam server menggunakan API ini, maka kita bisa menggunakan postman dengan metode `PUT` menggunakan request url seperti `http:localhost:8079/users/2`. lalu pada request body kita tambahkan data yang ingin kita tambahkan dalam bentuk JSON. disini data yang saya tambahkan sebagai berikut


```
{
    "First_Name": "Indi",
    "Type": "Buyer",
    "Email": "indiputra@email.com",
    "Last_Name": "Putra",
    "Phone_Number" : "32121"
}
```
<br>

### Menghapus Data dalam Server

Untuk menghapus data yang ada di server menggunakan API ini, maka kita bisa mengunakan postman dengan request url berupa `http:localhost:8079/users/2` dengan metode `DELETE` dimana url request tersebut artinya kita ingin menghapus entitas yang berada di kolom user dengan id sama dengan 2
