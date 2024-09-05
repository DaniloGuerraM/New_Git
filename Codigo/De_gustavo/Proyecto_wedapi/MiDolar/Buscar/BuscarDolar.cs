using MiDilar.Repocitorio;
using MiDilar.Modelo;
namespace MiDilar.Buscar;

public class BuscarDolar
{
    public void ListarDolar()
    {
        try
        {
            DolarRepocitorio dolarRepocitorio = new DolarRepocitorio();
            List<Dolar> listadoDolar = dolarRepocitorio.ObtenerDolar();
            foreach (Dolar dolar in listadoDolar)
            {
                Console.Write(dolar.Fecha_dolar);
                Console.Write("-----");
                Console.Write(dolar.Dolar_tipo);
                Console.Write("-----");
                Console.WriteLine(dolar.Ultimo_dolar);
            }
        }
        catch (System.Exception)
        {
            Console.WriteLine("En BuscarDolar esta el error");
            throw;
        }


    }
}