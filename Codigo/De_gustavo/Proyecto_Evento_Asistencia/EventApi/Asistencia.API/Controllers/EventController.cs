using Asistencia.Application.Interfaces;
using Asistencia.Domain.Entities;
using Microsoft.AspNetCore.Mvc;

namespace Asistencia.API.Controllers;

[ApiController]
[Route("api/[controller]")]
public class EventController : ControllerBase
{
    private readonly IEventServicio _eventServicio;
 
    public EventController(IEventServicio eventServicio){
        _eventServicio = eventServicio;
 
    }

/*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

    [HttpPost]
    public IActionResult AddAlumno([FromBody] EventEntities eventEntities)
    {
        if (_eventServicio.GuardarDatos(eventEntities))
        {
            return Ok("Guardados los datos");
        }
        return BadRequest();
        
    }
/*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    
    [HttpGet]
    public ActionResult<IEnumerable<EventEntities>> GetAlumnos()
    {
        var evento = _eventServicio.Obtener();
        return Ok(evento);
    }

/*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    
    [HttpPost("evento")]
    public IActionResult TomarAsistencia([FromBody] AsistenciaDTO asistenciaDTO)
    {
        if (_eventServicio.TomarAsistenciaPorId(asistenciaDTO))
        {
            return Ok("Asistencia tomada");
        }else{
            return NotFound();
        }
    }
}
