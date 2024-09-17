#include <WiFi.h>
#include <HTTPClient.h>


const char* ssid = "laboratorio";
const char* password = "laboratorio";


const char* serverUrl = "http://172.23.5.195:3001/holamundo/prueba";

void setup() {

  Serial.begin(115200);


  WiFi.begin(ssid, password);
  Serial.print("Conectando a WiFi...");
  
 
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.print(".");
  }

  Serial.println();
  Serial.println("Conectado a WiFi");
}

void loop() {
 
  if (WiFi.status() == WL_CONNECTED) {
    HTTPClient http;

 
    http.begin(serverUrl);
    
 
    http.addHeader("Content-Type", "application/json");

 
    String postData = "{\"nombre\": \"ESP32\", \"valor\": 100}";

 
    int httpResponseCode = http.POST(postData);

 
    if (httpResponseCode > 0) {
      Serial.println(httpResponseCode);
      String response = http.getString();
      Serial.println("Respuesta del servidor:");
      Serial.println(response);
    } else {
 
      Serial.print("Error en la solicitud POST. Código de error: ");
      Serial.println(httpResponseCode);
    }

 
    http.end();
  }


  delay(10000);
}
