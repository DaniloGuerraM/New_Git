#include "avr/wdt.h"
#include <Arduino.h>
void reiniciar ();


const int Pin = 2;
volatile long tiempo;
volatile int precionado = 0;
volatile int tiempo2 = 0;
long int puntos[5];  

void setup() {

  wdt_disable();
  Serial.println("Star");
  pinMode(Pin, INPUT_PULLUP);
  attachInterrupt(digitalPinToInterrupt(Pin), botonInterrupt, FALLING);
  Serial.begin(115200);
}

void loop() {
  tiempo = millis();
  Serial.println(tiempo);
  if (precionado == 5)
  {
    Serial.println(".");
    reiniciar();
  }
}

void botonInterrupt() {
  if (millis()>(tiempo2+100))
  {
    puntos[precionado] = millis();
    precionado++;
  }
  tiempo2=millis();
}

void reiniciar ()
{
  Serial.println("Lo precionsate en los tiempo");
  Serial.println(puntos[0]);
  Serial.println(puntos[1]);
  Serial.println(puntos[2]);
  Serial.println(puntos[3]);
  Serial.println(puntos[4]);

  Serial.println("Ingrece la letra 'r' para reiniciar ");
  
  while (1)
  {
   
    char r = Serial.read();
    r = tolower(r);
    if (r == 'r')
    {
       wdt_enable(WDTO_15MS);
    }
  }
}
