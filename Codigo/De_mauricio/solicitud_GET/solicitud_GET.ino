#include <WiFi.h>
#include <HTTPClient.h>

const char* ssid = "casa guerra";
const char* password= "Nachoytom";

const char* url = "http://www.google.com";
void setup() {
  Serial.begin(115200);

  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED){
    delay(500);
    Serial.print(".");
  }
  Serial.print("conectado a la red WiFi");


  // solicitud GET
  if (WiFi.status() == WL_CONNECTED){
    WiFiClient client;
    HTTPClient http;

    http.begin(client, url);
    int httpResponseCode = http.GET();

    if (httpResponseCode > 0){
      String payload = http.getString();
      Serial.print("///////////////// Respuesta Get: /////////////////");
      Serial.print(payload);
    } else {
      Serial.print("Error en la solicitud GET. respuesta");
      Serial.print(httpResponseCode);
    }
    http.end();
  }
}

void loop() {
  // put your main code here, to run repeatedly:

}
