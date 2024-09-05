using System.ComponentModel.DataAnnotations.Schema;


namespace MiDilar.Modelo;
[Table("dolar")]
public class Dolar
{
    [Column("tipo_dolar")]
    public string Dolar_tipo {get; set;}
    [Column("ultimo")]
    public int Ultimo_dolar {get; set;}
    [Column("minimo")]
    public int Minimo_dolar {get; set;}
    [Column("maximo")]
    public int Maximo_dolar {get; set;}
    [Column("fecha")]
    public DateTime Fecha_dolar {get; set;}
}
