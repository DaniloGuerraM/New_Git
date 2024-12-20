using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;


namespace Asistencia.Domain.Entities;

[Table("asistencia_evento")]
public class AsistenciaEvento
{
    [Key]
    [Column("registro_id")]
    [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
    public int IdRegistro { get; set; }
    
    [Column("fecha")]
    public long Fecha { get; set; }

    [Column("gmail")]
    public string EventoGmail { get; set; }

    public EventEntities Evento { get; set; }
    
}
