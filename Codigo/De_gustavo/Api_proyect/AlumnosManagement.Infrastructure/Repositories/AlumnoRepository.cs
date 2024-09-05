using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AlumnosManagement.Application.Interfaces;
using AlumnosManagement.Domain.Entities;
using AlumnosManagement.Infrastructure.Data;

namespace AlumnosManagement.Infrastructure.Repositories
{
    public class AlumnoRepository : IAlumnoRepository
    {
        private readonly ApplicationDbContext _context;

        public AlumnoRepository(ApplicationDbContext context)
        {
            _context = context;
        }

        public IEnumerable<Alumno> GetAlumnos()
        {
            return _context.Alumnos.ToList();
        }

        public Alumno GetAlumnoById(int id)
        {
            return _context.Alumnos.Find(id);
        }

        public void AddAlumno(Alumno alumno)
        {
            _context.Alumnos.Add(alumno);
            _context.SaveChanges();
        }
        public void UpdateAlumno(Alumno alumno)
        {
            _context.Alumnos.Update(alumno);
            _context.SaveChanges();
        }
        public void DeleteAlumno(int id)
        {
            var alumno = _context.Alumnos.Find(id);
            if (alumno != null)
            {
                _context.Alumnos.Remove(alumno);
                _context.SaveChanges();
            }
        }
    }
}