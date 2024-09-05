using MiDilar.Modelo;
using MiDilar.Repocitorio;
namespace MiDilar.Repocitorio;

public class DolarRepocitorio
{
    private readonly DolarContexto _dolarContexto;
    public DolarRepocitorio()
    {
        _dolarContexto = DolarContexto.CrearInstancia();
    }
    public List<Dolar> ObtenerDolar()
    {
        try
        {
            return _dolarContexto.dolar.ToList();
        }catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
            Console.WriteLine("En DolarRepocitorio esta el error");
            throw;
        }
    }
}
