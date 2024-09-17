using AlumnosManagement.Application.Interfaces;
using AlumnosManagement.Domain.Entities;

namespace AlumnosManagement.Application;

public class AlumnoServicio : IAlumnoServicio
{
    private readonly IAlumnoRepository _alumnoReposiroty;


    public AlumnoServicio(IAlumnoRepository alumnoRepository)
    {
        _alumnoReposiroty = alumnoRepository;
    }

    public IEnumerable<Alumno> ObtenerAlumnos()
    {
        return _alumnoReposiroty.GetAlumnos();
    }


    public Alumno ObteneralumnoPorIdentificador(int id)
    {
        return _alumnoReposiroty.GetAlumnoById(id);
    }


    public int AgregarAlumno(Alumno alumno)
    {
        _alumnoReposiroty.AddAlumno(alumno);
        return 1;
    }


    public bool ActualizarAlumno(Alumno alumno)
    {
        _alumnoReposiroty.UpdateAlumno(alumno);
        return true;
    }


    public bool BorrarAlumno(int id)
    {
        _alumnoReposiroty.DeleteAlumno(id);
        return true;
    }
}
