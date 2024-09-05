
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;


namespace Negocio.Libreria.Modelo;
[Table("producto")]
public class Producto
{
    [Column("producto_id")]
    public int Identificador { get; set; }
    [Key]
    [Column("codigo_ean")]
    public string CodigoEAN { get; set; }
    [Column("descripcion")]
    public string Descripcion { get; set; }
    [Column("tipo_producto")]
    public string TipoProducto { get; set; }
    [Column("precio_unitario")]
    public double PrecioUnitario { get; set; }
    [Column("porcentaje_iva")]
    public double PorcentajeIVA { get; set; }

    public string Devolver()
    {
        return Identificador + " " + CodigoEAN +  " " + Descripcion + " " + TipoProducto + " " +PrecioUnitario + " " + PorcentajeIVA;
    }
}
