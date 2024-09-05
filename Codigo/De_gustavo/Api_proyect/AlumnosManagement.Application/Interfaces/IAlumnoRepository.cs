using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AlumnosManagement.Domain.Entities;

namespace AlumnosManagement.Application.Interfaces
{
    public interface IAlumnoRepository
    {
        IEnumerable<Alumno> GetAlumnos();
        Alumno GetAlumnoById(int id);
        void AddAlumno(Alumno alumno);
        void UpdateAlumno (Alumno alumno);
        void DeleteAlumno(int id);
    }
}