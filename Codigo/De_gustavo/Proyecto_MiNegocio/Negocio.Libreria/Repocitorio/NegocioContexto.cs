using Negocio.Libreria.Modelo;
using Microsoft.EntityFrameworkCore;
//using Npgsql;
namespace Negocio.Libreria.Repocitorio;
public class NegocioContexto :  DbContext
{
    private static NegocioContexto instaciaContexto;
    private readonly string _cadenaConexion;
    private NegocioContexto(string cadenaConexion)
    {
        _cadenaConexion = cadenaConexion;
    } 
    public DbSet<Producto> producto {get; set;}
    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
    {
        optionsBuilder.UseNpgsql(_cadenaConexion);
        base.OnConfiguring(optionsBuilder);
    }
    public static NegocioContexto CrearInstancia()
    {
        if (instaciaContexto == null)
        {
            //para conectarse a Neon tech
            //"HOST=ep-cool-forest-92121755.us-east-2.aws.neon.tech;Port=5432;Database=Productos;User Id=nachotom02;Password=tvL2snyuE9AI"

            //para conectarse en PgAdmin
            //"host=localhost;port=5432;Database=bd_aprendizaje;User id=postgres;sslmode=prefer;Password=Nachoytom"
            instaciaContexto = new NegocioContexto("host=localhost;port=5432;Database=bd_aprendizaje;User id=postgres;sslmode=prefer;Password=Nachoytom");
        }
        return instaciaContexto;
    }
}

        /*
            
        PGHOST='ep-cool-forest-92121755.us-east-2.aws.neon.tech'
        PGDATABASE='Productos'
        PGUSER='nachotom02'
        PGPASSWORD='tvL2snyuE9AI'
        ENDPOINT_ID='ep-cool-forest-92121755'
        */
