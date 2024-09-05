
#include <SoftwareSerial.h>
SoftwareSerial mySerial(3, 5); // RX en pin 10, TX en pin 11

void setup() {
  Serial.begin(9600);  // Iniciar comunicación serie con el monitor serial
  
  while (!Serial)
  {
  }
  mySerial.begin(9600);
  if (!mySerial)
  {
    Serial.print("no anda");
    while(true);
  }
  Serial.println("Comenzamos a comunicarnos");
  
}

void loop() {
  //mySerial.println("Hola Mundo");
   if (mySerial.available()) {
    String nu = mySerial.readString();
    Serial.print("Resivido: ");
    Serial.println(nu);
  }
}

void serialEvent() {

  if (Serial.available())
  {
      Serial.print("enviado: ");
      String m = Serial.readString();
      Serial.print(m);
      mySerial.println(m);  
  }



}
