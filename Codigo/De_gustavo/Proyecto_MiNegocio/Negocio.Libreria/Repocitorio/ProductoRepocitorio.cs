using Negocio.Libreria.Modelo;
namespace Negocio.Libreria.Repocitorio;
public class ProductoRepocitorio
{
    private readonly NegocioContexto _negocioContexto;
    public ProductoRepocitorio()
    {
        _negocioContexto = NegocioContexto.CrearInstancia();
    }
    public List<Producto> ListarProducto()
    {
        return _negocioContexto.producto.ToList();
    }
    internal void CrearProducto(Producto producto)
    {
        _negocioContexto.producto.Add(producto);
        _negocioContexto.SaveChanges();
    }
    
    internal void ModificarProducto(Producto producto)
    {
        _negocioContexto.producto.Update(producto);
        _negocioContexto.SaveChanges();
    }
    
    internal List<Producto> ListarProductoPorCodigoEAN(string codigoean)
    {
        return _negocioContexto.producto.Where(d => d.CodigoEAN.ToLower().Contains(codigoean.ToLower())).ToList();
    }
}
