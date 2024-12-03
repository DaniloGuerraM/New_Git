using System;

public class Program
{
    public static void Main()
    {
        string cadena = "";
        string cadena2 = "";
        cadena = Console.ReadLine();

        for (int i = cadena.Length - 1; i >= 0; i--)
        {
            cadena2 += cadena[i];
        }        
        Console.WriteLine(cadena2);
    }
}