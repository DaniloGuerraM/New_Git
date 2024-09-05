// See https://aka.ms/new-console-template for more information
namespace MyWed;

public class Program 
{
    public static void Main(string[] args)
    {
        Console.WriteLine("Ingresa tu nombre:");
        string nombre = Console.ReadLine();
        Wed w = new Wed();
        w.Nombre = nombre;
        w.Imprime();
    }
}
