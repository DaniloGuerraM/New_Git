using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Asistencia.Domain.Entities;

[Table("registro_evento")]
public class EventEntities
{
    [Key]
    [Column("identificador")]
    [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
    public int Identificador{get; set;}
    [Column("idandroid")]
    public string? IdAndroid {get; set;}
    [Column("gmail")]
    public string Gmail{get; set;}
    [Column("nombre")]
    public string Nombre{get; set;}
}


