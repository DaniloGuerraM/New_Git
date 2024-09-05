
#include <SoftwareSerial.h>
SoftwareSerial mySerial(9, 11); // RX en pin 10, TX en pin 11

void setup() {
  Serial.begin(9600);  // Iniciar comunicación serie con el monitor serial
  while (!Serial){}
  mySerial.begin(9600);
  if (!mySerial)
  {
    Serial.print("no anda");
    while(true);
  }
  Serial.println("Comenzamos a comunicarnos");
}

void loop() {  
     while (mySerial.available()) {
    String nu = mySerial.readString();
    Serial.print("Resibido: ");
    Serial.println(nu);
     }
}

void serialEvent() {
 while(Serial.available())
  {
      Serial.print("enviado: ");
      String m = Serial.readString();
      Serial.print(m);
      mySerial.println(m);
  }
}
