using Negocio.Libreria.Repocitorio;
using Negocio.Libreria.Modelo;
namespace Negocio.Libreria.Repocitorio;

public class BuscarRepocitorio
{
    private readonly NegocioContexto _negocioContexto;
    public BuscarRepocitorio()
    {
        _negocioContexto = NegocioContexto.CrearInstancia();
    }
    public List<Producto> ObtenerProductos()
    {
       return _negocioContexto.producto.ToList();
    }
    internal List<Producto> ListarProductoPorDescripcion(string descripcion)
    {
        return _negocioContexto.producto.Where(d => d.Descripcion.ToLower().Contains(descripcion.ToLower())).ToList();
    }
}
