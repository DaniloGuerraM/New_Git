namespace AlumnosManagement.Application;

public class AlumnoServicio : IAlumnoRepository
{
    private readonly IAlumnoRepository _alumnoReposiroty;

    //private readonly ILogger<AlumnoServicio> _logger;

    public AlumnoServicio(IAlumnoRepository alumnoRepository/*, ILogger<AlumnoServicio> logger*/)
    {
        _alumnoReposiroty = alumnoRepository;
        
        //_logger = logger;

        //_logger.LogInformation("Create Alumno servicio");
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
