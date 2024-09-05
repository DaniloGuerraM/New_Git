using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

using AlumnosManagement.Application.Interfaces;
using AlumnosManagement.Domain.Entities;
using Microsoft.AspNetCore.Mvc;

namespace AlumnosManagement.API.Controllers
{
    
    [ApiController]
    [Route("api/[controller]")]
    public class AlumnosController : ControllerBase
    {
        private readonly IAlumnoRepository _alumnoRepository;
        public AlumnosController(IAlumnoRepository alumnoRepository)
        {
            _alumnoRepository = alumnoRepository;
        }

        [HttpGet]
        public ActionResult<IEnumerable<Alumno>> GetAlumnos()
        {
            var alumno = _alumnoRepository.GetAlumnos();
            return Ok(alumno);
        }

        [HttpGet("{id}")]
        public ActionResult<Alumno> GetAlumno(int id)
        {
            var alumno = _alumnoRepository.GetAlumnoById(id);
            if (alumno == null)
            {
                return NotFound();
            }
            return Ok(alumno);
        }

        [HttpPost]
        public IActionResult AddAlumno([FromBody] Alumno alumno)
        {
            _alumnoRepository.AddAlumno(alumno);
            return CreatedAtAction(nameof(GetAlumno), new {id = alumno.Id}, alumno);
        }

        [HttpPut("{id}")]
        public IActionResult UpdateAlumno(int id, [FromBody] Alumno alumno)
        {
            if (id != alumno.Id)
            {
                return BadRequest();
            }
            _alumnoRepository.UpdateAlumno(alumno);
            return NoContent();
        }

        [HttpDelete("{id}")]
        public IActionResult DeleteAlumno(int id)
        {
            _alumnoRepository.DeleteAlumno(id);
            return NoContent();
        }
    
    }
}