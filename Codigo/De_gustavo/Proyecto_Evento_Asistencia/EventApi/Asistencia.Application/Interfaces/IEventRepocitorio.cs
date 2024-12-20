using Asistencia.Domain.Entities;

namespace Asistencia.Application.Interfaces;

public interface IEventRepocitorio
{
    IEnumerable<EventEntities> Get();

    EventEntities GetById(string id);
    public bool GuardarDate(EventEntities eventEntities);
    bool RegistrarAsistencia(AsistenciaEvento asistenciaR);
    EventEntities BuscarPorId(string idAndroid);
    AsistenciaEvento ObtenerUltimaAsistencia(string gmail);
}
