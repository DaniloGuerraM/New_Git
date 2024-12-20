using Asistencia.Domain.Entities;
using Microsoft.EntityFrameworkCore;

namespace Asistencia.Infrastructure.Data;

public class ApplicationDbContext : DbContext
{
    public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options) : base(options)
    {}
    public DbSet<EventEntities> EventEntitie{get; set;}
    public DbSet<AsistenciaEvento> AsistenciaEventos{get; set;}
   
    //protected override void OnModelCreating(ModelBuilder modelBuilder)
    //{base.OnModelCreating(modelBuilder);}
    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        base.OnModelCreating(modelBuilder);

        modelBuilder.Entity<EventEntities>()
            .HasKey(e => e.Identificador);

        // Configura Gmail como clave alternativa para EventEntities
        modelBuilder.Entity<EventEntities>()
            .HasAlternateKey(e => e.Gmail);

        modelBuilder.Entity<AsistenciaEvento>()
            .HasKey(a => a.IdRegistro);

        // Configura la relación entre AsistenciaEvento y EventEntities
        modelBuilder.Entity<AsistenciaEvento>()
            .HasOne(a => a.Evento)
            .WithMany() // Sin relación inversa explícita
            .HasForeignKey(a => a.EventoGmail) // Clave externa en AsistenciaEvento
            .HasPrincipalKey(e => e.Gmail); // Gmail como clave principal alternativa en EventEntities
    }

}
