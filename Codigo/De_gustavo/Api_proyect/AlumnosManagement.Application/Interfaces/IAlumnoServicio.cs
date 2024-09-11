using AlumnosManagement.Domain.Entities;

namespace AlumnosManagement.Application.Interfaces;

public interface IAlumnoServicio
{
    IEnumerable<Alumno> ObtenerAlumnos();
    Alumno ObteneralumnoPorIdentificador(int id);
    int AgregarAlumno(Alumno alumno);
    bool ActualizarAlumno(Alumno alumno);
    bool BorrarAlumno(int id);
}
