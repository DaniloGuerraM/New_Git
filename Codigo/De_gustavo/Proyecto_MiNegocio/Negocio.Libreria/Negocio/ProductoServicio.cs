using Negocio.Libreria.Modelo;
using Negocio.Libreria.Repocitorio;

namespace Negocio.Libreria.Negocio;

public class ProductoServicio
{
    private readonly ProductoRepocitorio _productoRepocitorio;
    public ProductoServicio()
    {
        _productoRepocitorio = new ProductoRepocitorio();
    }
    public void AgregarProducto(Producto producto)
    {
        _productoRepocitorio.CrearProducto(producto);
    }
    
    public void ModificarElProducto(Producto producto)
    {
        _productoRepocitorio.ModificarProducto(producto);
    }
    

    public List<Producto> ObtenerProductos()
    {
        return _productoRepocitorio.ListarProducto();
    }
    public List<Producto> ObtenerProductoPorEAN(string codigoean)
    {
        return _productoRepocitorio.ListarProductoPorCodigoEAN(codigoean);
    }
}

