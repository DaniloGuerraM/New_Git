#include "controlo.h"
//////////////////////////////// PARA RECIBIR COOREDANAS ////////////////////////////////

void recibo();
void recibo()
{
  
  Serial.println(F("|||||||||| Estoy recibendo ||||||||||"));
  while (true)
  {
    if (tuSerial.available())
    {
      String tun = tuSerial.readStringUntil('\n');
      if (golpe(tun)){
        break;
      }
      else{
        tuSerial.print("e");
      }
    }
  }
  Serial.println(F("|||||||||| ahora envio ||||||||||"));
}
