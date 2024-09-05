using System;
namespace Barcos;

class Program
{
    static void Main ()
    {
        int i=0;
        Console.WriteLine("Hola esto funciona");
        Console.WriteLine("");
        Console.WriteLine("");
        CreoTablero c = new CreoTablero();
        /*
        while (i <2)
        {
            if (c.Coloco())
            {
                i++;
            }
            else
            {
                Console.WriteLine("---------------------------------");
                Console.WriteLine("acelo otra ves");
                Console.WriteLine("---------------------------------");
                Console.WriteLine("");
            }
        }
        */
        c.Imprime();
    }
}