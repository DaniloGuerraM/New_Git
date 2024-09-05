using Microsoft.AspNetCore.Mvc;
using API.Libreria.Modelo;
using API.Libreria.Repocitorio;

namespace API.Controllers;

[ApiController]
[Route("wedapidolar/[controller]")]
public class DolarController : ControllerBase
{
    DolarRepocitorio dolarRepocitorio = new DolarRepocitorio();
    private readonly DolarContexto _dolarInstancia;
    public static List<Dolar> Dolar = new List<Dolar>();
    public DolarController()
    {
        _dolarInstancia = DolarContexto.CrearInstancia();
    }
    [HttpGet("/PedirDolar")]
    public List<Dolar> MostrarDolar()
    {
        return Dolar;
    }
    [HttpPost ("/DbDolar")]
    public bool ObtenerDolar()
    {
        //int num = 0;
        try
        {
            List<Dolar> listedDolar = dolarRepocitorio.ObtenerDolar();
            Dolar = listedDolar;
            /*
            foreach (Dolar dolar in listadoDolar)
            {
                if (num < 100)
                { 
                    Dolar.Add(dolar.Ultimo_dolar);
                    num++;
                }
            }
            */
            return true;
        }catch (Exception ex)
        {
            return false;
        }
    }
    [HttpPost ("/ObtenerPor")]
    public bool ObtenerPorDolar()
    {
        return true;
    }
}
/*
    public static List<Dolar> dolar = new List<>();
    [HttpGet]
    public List<Dolar> DevolverDolar()
    {
        return dolar;
    }

*/