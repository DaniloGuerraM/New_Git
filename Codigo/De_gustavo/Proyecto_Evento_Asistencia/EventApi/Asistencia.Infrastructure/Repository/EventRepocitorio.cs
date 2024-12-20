using Asistencia.Application.Interfaces;
using Asistencia.Domain.Entities;
using Asistencia.Infrastructure.Data;

namespace Asistencia.Infrastructure.Repository;

public class EventRepocitorio : IEventRepocitorio
{
    private readonly ApplicationDbContext _applicationDbContext;
    public EventRepocitorio(ApplicationDbContext applicationDbContext)
    {
        _applicationDbContext = applicationDbContext;
    }
/*************************************************************************/
    public IEnumerable<EventEntities> Get()
    {
        try
        {
            return _applicationDbContext.EventEntitie.ToList();
        }
        catch (System.Exception)
        {
            
            return null;
        }
        
    }
/*************************************************************************/
    public EventEntities GetById(string id)
    {
        /*        return _applicationDbContext.Alumnos
        .Where(d => d.MAC.ToLower().Contains(mac.ToLower()))
        .FirstOrDefault();*/
        var idCel =_applicationDbContext.EventEntitie.Where(d => d.IdAndroid.ToLower().Contains(id.ToLower())).FirstOrDefault();
        return idCel;
    }

    /*************************************************************************/
    public bool GuardarDate(EventEntities eventEntities)
    {
        try{
            _applicationDbContext.EventEntitie.Add(eventEntities);
            _applicationDbContext.SaveChanges();
            return true;
        } catch (Exception ex){
            Console.WriteLine(ex.Message);
            return false;
        }
    }
    public EventEntities BuscarPorId(string idAndroid){
        
        return _applicationDbContext.EventEntitie.Where(d => d.IdAndroid.ToLower().Contains(idAndroid.ToLower())).FirstOrDefault();
    }

    public bool RegistrarAsistencia(AsistenciaEvento asistenciaR)
    {
        try
        {
            _applicationDbContext.AsistenciaEventos.Add(asistenciaR);
            _applicationDbContext.SaveChanges();
            return true;
        }
        catch (System.Exception)
        {
            return false;
        }
    }

    public AsistenciaEvento ObtenerUltimaAsistencia(string gmail)
    {
       return _applicationDbContext.AsistenciaEventos.Where(d => d.EventoGmail.ToLower().Contains(gmail.ToLower())).OrderByDescending(a => a.Fecha).FirstOrDefault();
    }
}
