using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace AlumnosManagement.Domain.Entities
{
    [Table("alumno_api")]
    public class Alumno
    {
        [Key]
        [Column("id")]
        public int Id {get; set;}
        [Column("nombre")]
        public string Nombre {get; set;}
        [Column("apellido")]
        public string Apellido {get; set;}
        [Column("edad")]
        public int edad {get; set;}
    }
}


