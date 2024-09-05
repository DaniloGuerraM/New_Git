#include <WiFi.h>
#include <HTTPClient.h>

// Replace with your WiFi data
const char* ssid = "casa guerra";//laboratorio
const char* password = "Nachoytom";//laboratorio
String url = "http://www.google.com";
//String url = "http://172.23.5.222:3001/esp32";

void setup()
{
  Serial.begin(115200);
  delay(10);

  // Connect to WiFi
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) 
    delay(500);

//WiFi.printDiag((Print) &Serial);

    
}

void loop()
{
  HTTPClient http;
  WiFiClient client;

//bool begin(NetworkClient &client, String host, uint16_t port, String uri = "/", bool https = false);

  //if (http.begin(client, url,443,"/v1/current.json?q=eduardo&lang=es&key=87637c428a6a496098d230942242604",0)) //Initiate connection
  if (http.begin(client, url))
  {
    Serial.print("[HTTP] GET...\n");
    int httpCode = http.GET();  // Make request

    if (httpCode > 0) {
      Serial.printf("[HTTP] GET... code: %d\n", httpCode);

      if (httpCode == HTTP_CODE_OK || httpCode == HTTP_CODE_MOVED_PERMANENTLY) {
        String payload = http.getString();  // Get response
        Serial.println(payload);  // Display response via serial
      }
    }
    else {
      Serial.printf("[HTTP] GET... failed, error: %s\n", http.errorToString(httpCode).c_str());
    }

    http.end();
  }
  else {
    Serial.printf("[HTTP} Unable to connect\n");
  }

  delay(30000);
}