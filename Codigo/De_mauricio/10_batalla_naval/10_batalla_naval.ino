#include "mis_variables.h"
#include "imprime_tablero.h"
#include "coloco_barcos.h"
#include "creo.h"
#include "enviar.h"
#include "recibo.h"


////////////////////////////////SETUP////////////////////////////////
void setup() {
  Serial.begin(9600); // Inicia la comunicación serial
  tuSerial.begin(9600);
  
  crear_tablero();
  mis_barcos();
  print_tablero();
  while(!tuSerial.available())
  {
    Serial.println(F("no conexion"));
    tuSerial.print("hola");
    delay(1000);
  }
  tuSerial.print("hola");

  Serial.println(F("BIENVENIDO A LA BATALLA NAVAL"));
  
  Serial.println(F("Recibo(r) o Envio(e)"));
  while(true)
  {
    if (tuSerial.available())
    {
       char quer = tuSerial.read();
      if (quer == 'r')
      {
        que = 'r';
        Serial.println(F("Empesamos"));
        break;
      }
      else if (quer == 'e')
      {
        que = 'e';
        Serial.println(F("Empesamos envieando"));
        break;
      }
    }
    if (Serial.available())
    {
      que = Serial.read();
      if (que == 'r')
      {
        Serial.println(F("Empesamos"));
        tuSerial.print('e');
        break;
      }
      else if(que == 'e')
      {
        tuSerial.print('r');
        Serial.println(F("Empesamos envieando"));         
        break;
      }
      else
      {
        Serial.println(F("caracter no valido"));
      }
    }
  }
}
//////////////////////////////// LOOP ////////////////////////////////
void loop() {
  if (que == 'r')
  {
    recibo();
    que = 'e';
  }
  else if (que == 'e')
  {
    if (envio != "")
    {
      if (mienvio())
      {
        envio = "";
        que = 'r';
      }
      envio = "";
    }
  }
  if (gane > 2)
  {
    Serial.println(F("He ganado"));
    while(true);
  }else if (perdi > 2)
  {
    Serial.println(F("He perdio"));
    while(true);
  }
  
}
//////////////////////////////// SERIAL EVENT ////////////////////////////////

void serialEvent()
{
  while(Serial.available())
  {
    envio = Serial.readStringUntil('\n');
  }
}
