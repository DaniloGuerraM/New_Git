using Asistencia.Application.Interfaces;
using Asistencia.Domain.Entities;
using Microsoft.AspNetCore.Mvc;

namespace Asistencia.API.Controllers;

[ApiController]
[Route("api/[controller]")]
public class CodigoQRController : ControllerBase
{
    
    private readonly IControlQRServicio _controlQRServicio;
    public CodigoQRController(IEventServicio eventServicio, IControlQRServicio controlQRServicio){
        
        _controlQRServicio = controlQRServicio;
    }
    [HttpPost("qr")]
    public IActionResult AgregarQR([FromBody] MicroDTO microDTO)
    {
        if (_controlQRServicio.GuardarQR(microDTO)){
            return Ok("Codigo guardado");
        }else{
            return NotFound("Codigo no guardado");
        }
    }

}
