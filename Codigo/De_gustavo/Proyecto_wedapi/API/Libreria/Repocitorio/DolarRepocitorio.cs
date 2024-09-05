using API.Libreria.Modelo;

namespace API.Libreria.Repocitorio;

public class DolarRepocitorio
{
    private readonly DolarContexto _dolarContexto;
    public DolarRepocitorio()
    {
        _dolarContexto = DolarContexto.CrearInstancia();
    }
    public List<Dolar> ObtenerDolar()
    {
        return _dolarContexto.dolar.Take(100).ToList();
    }
}
