//////////////////////////////// COLOCO LOS BARCOS ////////////////////////////////
void mis_barcos();
void mis_barcos()
{
  String f = "";
  String c = "";
  int colocados = 1;
  Serial.println(F("Ingrese las coordenadas"));
  while (colocados <= 3)
  {
    while (Serial.available())
    {
      String coordenadas = Serial.readStringUntil('\n');
      if (coordenadas.length() == 4 && isDigit(coordenadas[0]) && isDigit(coordenadas[1])&& isDigit(coordenadas[2])&& isDigit(coordenadas[3]) )
      {
        f = coordenadas[0];
        f += coordenadas[1];
        c = coordenadas[2];
        c += coordenadas[3];
        int fila = String(f).toInt() ;
        int columnas = String(c).toInt() ;
        if (fila >= 0 && fila <= Fi && columnas >= 0 && columnas <= C)
        {
          if (columnas+2 < C && tablero1[fila][columnas] == 'O' && tablero1[fila][columnas+1] == 'O' && tablero1[fila][columnas+2] == 'O' )
          {
            if (tablero1[fila][columnas-1] != '#' && tablero1[fila][columnas+3] != '#' )
            {
              
              tablero1[fila][columnas] = '#';
              tablero1[fila][columnas+1] = '#';
              tablero1[fila][columnas+2] = '#';
              Serial.println(F("/////////////El barco colocado/////////////"));
              colocados++; 
            }
            else
            {
              Serial.println(F("los barcos no pueden estar juntos"));
            }
          }
          else{
            Serial.println(F("no son validas las coordenadas"));
          }
        }
        else{
          Serial.println(F("estas fuera de rango"));
        }
      }
      else{
        Serial.println(F("Estan mal las coordenadas"));
      }
    }
    f = "";
    c = "";
  }
}
