namespace Barcos;

public class CreoTablero
{
    string sen;
    int columna ,fila, tamanio;

    
    public static char[,] tablero = new char[10, 10];
    public CreoTablero()
    {
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                tablero[i, j] = '.';
            }
        }
    }
    public bool Coloco()
    {
        int num1 = 0;
        int num2 = 0;
        try
        {
            Console.WriteLine("porfavor ingresar");
            Console.WriteLine("");
            Console.WriteLine("La fila que sea entre 0 y 9");
            string leerfi = Console.ReadLine();
            while(true)
            {
                if(!int.TryParse(leerfi, out fila))
                {
                    Console.WriteLine("Ingrese un numero valido para la fila");
                    leerfi = Console.ReadLine();
                }else if(fila > 9 || fila < 0)
                {
                    Console.WriteLine("Ingrese un numero entre 0 y 9");
                    leerfi = Console.ReadLine();
                }else
                {
                    break;
                }

            }
            Console.WriteLine("");
            Console.WriteLine("La columna que sea entre 0 y 9");
            string leerco = Console.ReadLine();
            
            while(true)
            {
                if (!int.TryParse(leerco, out columna))
                {
                    Console.WriteLine("Ingrese un numero valido para la columna");
                    leerco = Console.ReadLine();
                }else if (columna < 0 || columna > 9)
                {
                    Console.WriteLine("Ingrese un numero entre 0 y 9");
                    leerco = Console.ReadLine();
                }else
                {
                    break;
                }
            }
            Console.WriteLine("");
            Console.WriteLine("El tamaño del Barco(no mayor a 4 ni ni menor a 1 )");
            string leerta = Console.ReadLine();
            while(true)
            {
                if (!int.TryParse(leerta, out tamanio))
                {
                    Console.WriteLine("Ingrese un numero valido para el tamanio");
                    leerta = Console.ReadLine();
                }
                else if (tamanio > 4 && tamanio <= 0)
                {
                    Console.WriteLine("Ingrese un numero menor/igual a 4 o mayor/ igual a 0");
                    leerta = Console.ReadLine();
                }
                else
                {
                    break;
                }
            }
            Console.WriteLine("");
            Console.WriteLine("Ingrese el sentiro: Orizontal(o) o Vertical(v)");
            sen = Console.ReadLine();
            while (sen != "v" && sen != "o")
            {
                Console.WriteLine("Ingrese un sentido valido (v O o)");
                sen = Console.ReadLine();
            }
        }
        catch (System.Exception ex)
        {
            Console.WriteLine(ex.Message);
            throw;
        }
        if (sen == "v")
        {
            for (int i=0; i<tamanio ; i++)
            {
                if ((fila + i)<10)
                {
                    if (tablero[fila+i, columna] == '.' && tablero[fila+i, columna] != 'X')
                    {
                        if ((columna-1) < 0)
                        {
                            if ( tablero[fila+i, columna+1] != 'X')
                            {
                                num1++;
                            }
                        }else if ((columna+1) > 9)
                        {
                            if ( tablero[fila+i, columna-1] != 'X')
                            {
                                num1++;
                            }
                        }
                        else
                        {
                            if (tablero[fila+i, columna-1] != 'X' && tablero[fila+i, columna+1] != 'X')
                            {
                                num1++;
                            }
                        }
                    }
                }
                if ((fila -i) >= 0 )
                {
                    if (tablero[fila-i, columna] == '.' && tablero[fila-i, columna] != 'X')
                    {
                        if ((columna-1) < 0)
                        {
                            if ( tablero[fila-i, columna+1] != 'X')
                            {
                                num2++;
                            }
                        }else if ((columna+1) > 9)
                        {
                            if ( tablero[fila-i, columna-1] != 'X')
                            {
                                num2++;
                            }
                        }
                        else
                        {
                            if (tablero[fila-i, columna-1] != 'X' && tablero[fila+i, columna+1] != 'X')
                            {
                                num2++;
                            }
                        }
                    }
                }
            }
            if (num1 == tamanio)
            {
                for (int i=0; i<tamanio; i++)
                {
                    tablero[fila+i, columna] = 'X';
                }
                return true;
            }else if (num2 == tamanio)
            {
                for (int i=0; i<tamanio; i++)
                {
                    tablero[fila-i, columna] = 'X';
                }
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            for (int i=0; i<tamanio; i++)
            {
                if ((columna + i) < 10)
                {
                    if (tablero[fila, columna+i] == '.' && tablero[fila, columna+i] != 'X')
                    {
                        if ((fila-1) < 0)
                        {
                            if (tablero[fila+1, columna+i] != 'X')
                            {
                                num1++;
                            }
                        }else if ((fila+1) > 9)
                        {
                            if (tablero[fila-1, columna+i] != 'X')
                            {
                                num1++;
                            }
                        }else
                        {
                            if (tablero[fila-1, columna+i] != 'X' && tablero[fila+1, columna+i] != 'X')
                            {
                                num1++;
                            }
                        }
                    }
                }
                if ((columna - i) >= 0)
                {
                    if (tablero[fila, columna-i] == '.' && tablero[fila, columna-i] != 'X')
                    {
                        if ((fila-1) < 0)
                        {
                            if (tablero[fila+1, columna-i] != 'X')
                            {
                                num2++;
                            }
                        }else if ((fila+1) > 9)
                        {
                            if (tablero[fila-1, columna-i] != 'X')
                            {
                                num2++;
                            }
                        }else
                        {
                            if (tablero[fila-1, columna-i] != 'X' && tablero[fila+1, columna-i] != 'X')
                            {
                                num2++;
                            }
                        }
                    }
                }
            }
            if (num1 == tamanio)
            {
                for (int i=0; i<tamanio; i++)
                {
                    tablero[fila, columna+i] = 'X';
                }
                return true;
            }else if (num2 == tamanio)
            {
                for (int i=0; i<tamanio; i++)
                {
                    tablero[fila, columna-i] = 'X';
                }
                return true;
            }
            else
            {
                return false;
            }
        }
    }
    public void Imprime()
    {
        Console.Write(" ");
        for (int i=0; i<10; i++)
        {
            Console.Write( "_______"+ i );
        }
        Console.WriteLine(" ");
        for (int i = 0; i < 10; i++)
        {
            Console.Write(i+ "|" + "\t");
            for (int j = 0; j < 10; j++)
            {
                Console.Write(tablero[i, j] + "\t");//
            }
            Console.WriteLine();
        }
    }
}
