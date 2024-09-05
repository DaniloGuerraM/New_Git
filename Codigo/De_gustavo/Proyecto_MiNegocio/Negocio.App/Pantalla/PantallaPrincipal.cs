using Spectre.Console;
using System;
using Negocio.App.Pantalla.PantallaBuscarProducto;
using Negocio.App.Pantalla.PantallaCargarProducto;

namespace Negocio.App.Pantalla;

public class PantallaPrincipal
{
    private string[] _opcionesPrinsipal;
    public PantallaPrincipal()
    {
        _opcionesPrinsipal = new string[]  {"Cargar","Buscar","Salir"};
    }
    public void Menu()
    {

        while(true)
        {
            AnsiConsole.Clear();
            AnsiConsole.Write(
                new FigletText("MY NEGOCIO")
                .LeftJustified()
                .Color(Color.Silver)
            );

            var opcion = AnsiConsole.Prompt(
                new SelectionPrompt<string>()
                .Title("[darkslategray2]Menu[/]")
                .AddChoices(_opcionesPrinsipal)
            );

            switch (opcion.ToLower())
            {
                case "cargar":
                    CargarProducto p = new CargarProducto();
                    p.CargarArchivo();

                    break;
                case "buscar":
                    BuscarProducto buscar = new BuscarProducto();
                    buscar.ListarProductos();
                    break;
                case "salir":
                    Environment.Exit(0);
                    break;
                default:
                   
                    break;
            }
        }
    }
}
