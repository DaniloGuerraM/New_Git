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
        /*
        private readonly IAlumnoRepository _alumnoRepository;
        public AlumnosController(IAlumnoRepository alumnoRepository)
        {
            _alumnoRepository = alumnoRepository;
        }*/
        private readonly IAlumnoServicio _alumnoServicio;
        public AlumnosController(IAlumnoServicio alumnoServicio)
        {
            _alumnoServicio = alumnoServicio;
        }

        [HttpGet]
        public ActionResult<IEnumerable<Alumno>> GetAlumnos()
        {
            //var alumno = _alumnoRepository.GetAlumnos();
            var alumno = _alumnoServicio.ObtenerAlumnos();
            return Ok(alumno);
        }

        [HttpGet("{id}")]
        public ActionResult<Alumno> GetAlumno(int id)
        {
            //var alumno = _alumnoRepository.GetAlumnoById(id);
            var alumno = _alumnoServicio.ObteneralumnoPorIdentificador(id);
            if (alumno == null)
            {
                return NotFound();
            }
            return Ok(alumno);
        }

        [HttpPost]
        public IActionResult AddAlumno([FromBody] Alumno alumno)
        {
            //_alumnoRepository.AddAlumno(alumno);
            _alumnoServicio.AgregarAlumno(alumno);
            return CreatedAtAction(nameof(GetAlumno), new {id = alumno.Id}, alumno);
        }

        [HttpPut("{id}")]
        public IActionResult UpdateAlumno(int id, [FromBody] Alumno alumno)
        {
            if (id != alumno.Id)
            {
                return BadRequest();
            }
            //_alumnoRepository.UpdateAlumno(alumno);
            _alumnoServicio.ActualizarAlumno(alumno);
            return NoContent();
        }

        [HttpDelete("{id}")]
        public IActionResult DeleteAlumno(int id)
        {
            //_alumnoRepository.DeleteAlumno(id);
            _alumnoServicio.BorrarAlumno(id);
            return NoContent();
        }
    
    }
}