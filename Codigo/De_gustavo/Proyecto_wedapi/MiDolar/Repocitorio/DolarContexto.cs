using System.Collections.Generic;
using Microsoft.EntityFrameworkCore;

using MiDilar.Modelo;
using Npgsql;

namespace MiDilar.Repocitorio;

public class DolarContexto : DbContext
{
    private static DolarContexto instanciaContexto;
    private readonly string _cadenaConexion;
    private DolarContexto(string cadenaConexion)
    {
        _cadenaConexion = cadenaConexion;
    }
    public DbSet<Dolar> dolar {get; set;}
    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
    {
        optionsBuilder.UseNpgsql(_cadenaConexion);
        base.OnConfiguring(optionsBuilder);
    }
    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<Dolar>().HasNoKey();
        base.OnModelCreating(modelBuilder);
    }
    public static DolarContexto CrearInstancia()
    {
        try
        {
            if (instanciaContexto == null)
            {
                instanciaContexto = new DolarContexto("host=localhost;port=5432;Database=PrecioDolar;User id=postgres;sslmode=prefer;Password=Nachoytom");
            }
            return instanciaContexto;
        }catch (Exception ex)
        {
            Console.WriteLine("No se puede conectar");
            throw;
        }
    }
}
