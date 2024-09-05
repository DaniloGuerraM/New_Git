using System;
using MiDilar.Buscar;
namespace MiDilar;

public class Program
{
    public static void Main()
    {
        try
        {
            Console.WriteLine("Estos son los dolares ");
            BuscarDolar b = new BuscarDolar();
            b.ListarDolar();
        }catch (Exception ex)
        {
            Console.WriteLine("A ocurrido un error", ex.Message);
        }

    }
}