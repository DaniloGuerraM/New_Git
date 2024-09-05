using Negocio.Libreria.Modelo;
using Negocio.Libreria.Repocitorio;
namespace Negocio.Libreria.Negocio;

public class BuscarServicio
{
    private readonly BuscarRepocitorio _buscarRepocitorio;


    public BuscarServicio()
    {
        _buscarRepocitorio = new BuscarRepocitorio();
    }
    public List<Producto> ObtenerProductos()
    {
        return _buscarRepocitorio.ObtenerProductos();
    }
    public List<Producto> ObtenerProductoPorDe(string descripcion)
    {
        return _buscarRepocitorio.ListarProductoPorDescripcion(descripcion);
    }
}
