using AlumnosManagement.Domain.Entities;
using Microsoft.EntityFrameworkCore;

namespace AlumnosManagement.Infrastructure.Data;

public class ApplicationDbContext : DbContext
{
    public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options) : base(options)
    {}

    public DbSet<Alumno> Alumnos {get; set;}

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        base.OnModelCreating(modelBuilder);
        //modelBuilder.Entity<Alumno>().ToTable("alumno_api");
    }
}
