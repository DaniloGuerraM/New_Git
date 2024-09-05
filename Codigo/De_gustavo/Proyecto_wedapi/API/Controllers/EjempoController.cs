using Microsoft.AspNetCore.Mvc;

namespace API.Controllers;
[ApiController]
[Route("wed/[controller]")]
public class EjempoController : ControllerBase
{
    public static List<string> listaAlumnos = new List<string>();

    [HttpGet]
    public List<string> DevolverListaAlumnos()
    {
        return listaAlumnos;
    }

    [HttpPost]
    public bool AgregarAlumnos([FromQuery] string nombre)
    {
        listaAlumnos.Add(nombre);
        return true;
    }
    [HttpDelete]
    public bool BorrarAlumno([FromQuery] string nombre)
    {
        if (!listaAlumnos.Contains(nombre))
        {
            return false;
        }
        listaAlumnos.Remove(nombre);
        return true;
    }
}
