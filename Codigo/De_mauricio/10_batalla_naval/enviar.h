//////////////////////////////// PARA ENVIAR COORDENADAS ////////////////////////////////
bool mienvio();
bool mienvio()
{
  String fi = "";
  String co = "";
  fi += envio[0];
  fi += envio[1];
  co += envio[2];
  co += envio[3];
  int fil = String(fi).toInt();
  int colum = String(co).toInt();
  char tu;
  tuSerial.print(envio);
  while (true)
  {
    if (tuSerial.available())
    {
      tu = tuSerial.read();
      break;
    }
    delay(100);
  }
  switch (tu)
  
  {
    case 'a':
      tablero2[fil][colum] = 'X';
      print_tablero();
      Serial.println(F("____agua____"));
      return true;
      break;
    case 'i':
      tablero2[fil][colum] = '+';
      print_tablero();
      Serial.println(F("____impacto____"));
      return true;
      break;
    case 'h':
      tablero2[fil][colum] = '+';
      print_tablero();
      gane++;
      Serial.println(F("____hundido____"));
      return true;
      break;
    case 'e':
      Serial.println(F("____error de coordenadas____"));
      return false;
      break;
    default:
      return false;
      break;
  }
}
