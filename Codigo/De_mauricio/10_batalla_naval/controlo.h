//////////////////////////////// CONTROLA LAS COORDENADA QUE RECIBO ////////////////////////////////
bool golpe(String);
bool golpe(String disparo)
{
  int esundido = 0;
  int control = 0;
  String fi = "";
  String co = "";
  if (isDigit(disparo[0]) && isDigit(disparo[1])&& isDigit(disparo[2])&& isDigit(disparo[3]) )
  {
    fi += disparo[0];
    fi += disparo[1];
    co += disparo[2];
    co += disparo[3];
    int fil = String(fi).toInt();
    int colum = String(co).toInt();
     if (fil >= 0 && fil < Fi && colum >= 0 && colum < C)
     {
      if (tablero1[fil][colum] == 'O'  )
      {
        tuSerial.print("a");
        return true;
      }
      else if (tablero1[fil][colum] == '+')
      {
          tuSerial.print("i");
          return true;
      }
      else
      {
        tablero1[fil][colum] = '+';
        for (int i =(colum-2); i < (colum+2);i++)
        {
          if (tablero1[fil][i] == '+' && tablero1[fil][i+1] == '+' && tablero1[fil][i+2] == '+')
          {
            control++;
          }
        }
        if (control == 1)
        {
          print_tablero();
          tuSerial.print("h");
          perdi++;
          Serial.println(F("Me hundieron un barco"));
          return true;
        }
        else
        {
          print_tablero();
          tuSerial.print("i");
          return true;
        }
      }
     }
     else
     {
      tuSerial.print("e");
      return false;
     }
  }
  else
  {
    tuSerial.print("e");
    return false;
  }
}
