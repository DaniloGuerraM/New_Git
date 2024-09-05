//////////////////////////////// IMPRIMO LOS TABLERO ////////////////////////////////

void print_tablero();
void print_tablero()
{
  for (int i = 0 ; i < 20; i++)
  {
    Serial.println(F(" "));
  }
  Serial.print(F("  ")); // Espacio para los números a los lados
  for(int c = 0; c < 15; c++) {
    if (c < 10)
    {
      Serial.print(F("  "));    
      Serial.print(c);
    }else
    {
      Serial.print(F(" "));
      Serial.print(c);
    }
  }             
  Serial.print(F("///////////////"));
    for(int c = 0; c < 15; c++) {
    if (c < 10)
    {
      Serial.print(F("  "));
      Serial.print(c);
    }else
    {
      Serial.print(F(" "));
      Serial.print(c);
    }
  }
  Serial.println();
  for(int i = 0; i < 15; i++) {
    // Imprime el número de fila al inicio
    Serial.print(F(" "));
    Serial.print(i);
    Serial.print(F(" "));
    if (i<10)
    {
      Serial.print(F(" "));
    }
    for(int j = 0; j < 15; j++) {
      Serial.print(tablero1[i][j]); // Imprime una celda del primer tablero
      Serial.print(F("  "));
    }               
    Serial.print(F("            "));
    Serial.print(i);
    Serial.print(F(" "));
    if (i<10)
    {
      Serial.print(F(" "));
    }
    for(int j = 0; j < 15; j++) {
      Serial.print(tablero2[i][j]); // Imprime una celda del segundo tablero
      Serial.print(F("  "));
    }
    Serial.println(); // Salto de línea al final de cada fila
  }
}
