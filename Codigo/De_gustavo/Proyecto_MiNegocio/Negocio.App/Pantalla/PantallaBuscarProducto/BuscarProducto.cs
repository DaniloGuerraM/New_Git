using Negocio.Libreria.Modelo;
using Negocio.Libreria.Negocio;
using Spectre.Console;

namespace Negocio.App.Pantalla.PantallaBuscarProducto;
public class BuscarProducto : PantallaPrincipal
{
    public void ListarProductos()
    {

        
        AnsiConsole.Clear();
        AnsiConsole.Write(
            new FigletText("MY NEGOCIO")
            .LeftJustified()
            .Color(Color.Fuchsia)
        );
        AnsiConsole.MarkupLine("[springgreen2_1]Buscar el producto[/]");

        try
        {
            BuscarServicio buscarServicio = new BuscarServicio();
            //si desea que te busque sin ingresar una descripcion descomenta la siguiente linia
            //List<Producto> listadoProductos = buscarServicio.ObtenerProductos();
            string descripcion = AnsiConsole.Prompt(new TextPrompt<string>("Introducta la descripcion: "));
            // y comenta la siguiente linea si deseas que te busque sin ingresar una descripcion
            List<Producto> listadoProductos = buscarServicio.ObtenerProductoPorDe(descripcion);
            
            foreach (Producto producto in listadoProductos)
            {
                
                
                
                
                AnsiConsole.MarkupLine("[yellow1]Descriocion de producto: [/]" + producto.Descripcion );
                AnsiConsole.MarkupLine("[yellow1]Codigo EAN:              [/]" + producto.CodigoEAN);
                AnsiConsole.MarkupLine("[yellow1]Tipo de producto:        [/]" + producto.TipoProducto);
                AnsiConsole.MarkupLine("[yellow1]Porcentaje del IVA:      [/]" + producto.PorcentajeIVA);
                AnsiConsole.MarkupLine("[yellow1]Id del Producto:         [/]" + producto.Identificador);
                AnsiConsole.MarkupLine("[yellow1]Precio del Producto:     [/]" + producto.PrecioUnitario);
                Console.WriteLine("--------------------------------------------------------------");
                
                
            }
            Console.WriteLine("Precione una tecla para continua");
            Console.ReadKey();
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
            Console.WriteLine("_");
            Console.WriteLine("_");
        }
    }
}
