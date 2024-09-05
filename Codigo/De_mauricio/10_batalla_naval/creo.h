//////////////////////////////// CREA EL TABLERO ////////////////////////////////
void crear_tablero();
void crear_tablero()
{
  for (int i = 0; i <Fi;i++)
  {
    for (int j = 0;j < C; j++)
    {
      tablero1[i][j]= 'O';
      tablero2[i][j]= 'O';
    }
  }
}
