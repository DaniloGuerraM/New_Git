using Asistencia.Domain.Entities;

namespace Asistencia.Application.Interfaces;

public interface IEventServicio
{
    IEnumerable<EventEntities> Obtener();
    public bool GuardarDatos(EventEntities eventEntities);
    bool TomarAsistenciaPorId(AsistenciaDTO asistenciaDTO);
    EventEntities BuscarPorId(string idAndroid);
}
