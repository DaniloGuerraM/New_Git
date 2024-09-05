using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Negocio.Libreria.Modelo;
using Negocio.Libreria.Negocio;
using Negocio.Libreria.Repocitorio;
using System.IO;
using Spectre.Console;


namespace Negocio.App.Pantalla.PantallaCargarProducto;

public class CargarProducto : PantallaPrincipal
{
    
    private ProductoServicio _productoServicio;
    public CargarProducto()
    {
        _productoServicio = new ProductoServicio();
    }
    public void CargarArchivo()
    {
        AnsiConsole.Clear();
        AnsiConsole.Write(
            new FigletText("MY NEGOCIO")
            .LeftJustified()
            .Color(Color.Fuchsia)
        );
        AnsiConsole.MarkupLine("[springgreen2_1]Cargar el Archivo[/]");
        string ubicacion = AnsiConsole.Prompt(new TextPrompt<string>("intoduca la ubicacion del archivo:"));
        bool existe = File.Exists(ubicacion);
        if (existe)
        {
            System.IO.StreamReader leer = new System.IO.StreamReader(ubicacion);
            System.IO.StreamReader leer2 = new System.IO.StreamReader(ubicacion);

            string separador = ";";
            string linea = "";
            string linea2 = "";
            leer.ReadLine();
            while ((linea = leer.ReadLine()) != null)
            {
                try
                {
                    string[] fila = linea.Split(separador);
                    int id2= Convert.ToInt32(fila[0]);
                    string codigo_EAN2 =fila[1];
                    string descripcion2 = fila[2];
                    string tipoproducto2 = fila[3];
                    double precio2 = Convert.ToDouble(fila[4]);
                    double porcentaje_IVA2 = Convert.ToDouble(fila[5]);
                    AnsiConsole.MarkupLine("[darkorange] identificador:{0}, EAN:{1}, Decripcion:{2}, tipoproducto:{3}, precio:{4}, IVA:{5} [/]", id2, codigo_EAN2, descripcion2, tipoproducto2, precio2, porcentaje_IVA2);
                    
                }
                catch(Exception ex)
                {
                    AnsiConsole.MarkupLine("[magenta2]a ocurrido un error:[/]");
                    Console.WriteLine(ex.Message);
                }
            }

            if (AnsiConsole.Confirm("¿Deseas SALVAR los datos?"))
            {

                leer2.ReadLine();
                while ((linea2 = leer2.ReadLine())!= null)
                {
                    try
                    {
                        string[] fila = linea2.Split(separador);
                        int id= Convert.ToInt32(fila[0]);
                        string codigo_EAN =fila[1];
                        string descripcion = fila[2];
                        string tipoproducto = fila[3];
                        double precio = Convert.ToDouble(fila[4]);
                        double porcentaje_IVA = Convert.ToDouble(fila[5]);
                        Producto prod = new Producto();
                        prod.Identificador = Convert.ToInt32(fila[0]);
                        prod.CodigoEAN = fila[1];
                        prod.Descripcion = fila[2];
                        prod.TipoProducto = fila[3];
                        prod.PrecioUnitario = Convert.ToDouble(fila[4]);
                        prod.PorcentajeIVA = Convert.ToDouble(fila[5]);


                        ProductoServicio buscarpro = new ProductoServicio();
                        List<Producto> estaProducto = buscarpro.ObtenerProductoPorEAN(codigo_EAN);

                        string estaEan = "";
                        foreach (Producto producto in estaProducto)
                        {
                            estaEan = producto.CodigoEAN;
                        }

                        if (estaEan  == codigo_EAN)
                        {
                            var modificar =  estaProducto.FirstOrDefault(p => p.CodigoEAN == estaEan);
                            if (modificar != null)
                            {
                                try
                                {
                                    modificar.Identificador = id;
                                    modificar.CodigoEAN = codigo_EAN;
                                    modificar.Descripcion = descripcion;
                                    modificar.TipoProducto = tipoproducto;
                                    modificar.PrecioUnitario = precio;
                                    modificar.PorcentajeIVA = porcentaje_IVA;
                                    _productoServicio.ModificarElProducto(modificar);
                                }
                                catch (Exception ex)
                                {
                                    AnsiConsole.MarkupLine("[magenta2]a ocurrido un error al cargar los datos: [/]");
                                    Console.WriteLine(ex.Message);
                                }
                                AnsiConsole.MarkupLine("[yellow] Modificado [/] ");
                            }
                        }
                        else
                        {
                            AnsiConsole.MarkupLine("[red] Agregado[/]");
                            _productoServicio.AgregarProducto(prod);
                        }   
                        

                    }
                    catch (Exception ex)
                    {
                        AnsiConsole.MarkupLine("[magenta2]a ocurrido un error:[/]");
                        Console.WriteLine(ex.Message);
                    }
                        
                }
                Console.WriteLine("Precione una tecla para continuar");
                Console.ReadKey();
           
            }
            else
            {
                AnsiConsole.MarkupLine("[lightcyan1]NO sean SALVADO los datos:[/]");
                Console.WriteLine("Precione una tecla para continuar");
                Console.ReadKey();
            }
        }
        else 
        {
            AnsiConsole.MarkupLine("[magenta2]la ubicacion no exixte [/]");
            Console.WriteLine("Precione una tecla para continuar");
            Console.ReadKey();
        }   
    }
}

