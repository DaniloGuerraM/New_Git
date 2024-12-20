using Asistencia.Application.Interfaces;
using Asistencia.Domain.Entities;

namespace Asistencia.Application;

public class EventServicio : IEventServicio
{
    private readonly IEventRepocitorio _eventRepocitorio;
    private readonly IControlQRServicio _controlQRServicio;
    public EventServicio(IEventRepocitorio eventRepocitorio, IControlQRServicio controlQRServicio)
    {
        _eventRepocitorio = eventRepocitorio;
        _controlQRServicio = controlQRServicio;
    }

    /***************************** GUARDAR *******************************************/
    public bool GuardarDatos(EventEntities eventEntities)
    {
        var idCelular = _eventRepocitorio.GetById(eventEntities.IdAndroid);

        if (idCelular == null)
        {
            if (!String.IsNullOrEmpty(eventEntities.Gmail) && !String.IsNullOrEmpty(eventEntities.Nombre))
            {
                if (_eventRepocitorio.GuardarDate(eventEntities))
                {

                    return true;
                }
            }
        }
        else if (idCelular.Gmail == eventEntities.Gmail)
        {

            return true;
        }

        return false;
    }
    /********************************* OBTENER ASISTENCIA ****************************************/
    public IEnumerable<EventEntities> Obtener()
    {
        return _eventRepocitorio.Get();
    }
    /************************* OBTENER ASISTENCIA POR ID_ANDROID *********************************/
    public EventEntities BuscarPorId(string idAndroid)
    {
        return _eventRepocitorio.BuscarPorId(idAndroid);
    }
    /*********************************** TOMAR ASISTENCIA ****************************************/
    public bool TomarAsistenciaPorId(AsistenciaDTO asistenciaDTO)
    {
        var existe = BuscarPorId(asistenciaDTO.IdAndroid);
        string[] parte = asistenciaDTO.CodigoQR.Split('-');
        if (existe != null)
        {

            var microDTO = _controlQRServicio.ObtenerQR();
            if (microDTO.Key == parte[0] && microDTO.Valor == parte[1])
            {
                AsistenciaEvento sis = new AsistenciaEvento();
                sis.EventoGmail = existe.Gmail;
                sis.Fecha = new DateTimeOffset(DateTime.UtcNow).ToUnixTimeSeconds();
                var ultimaA = _eventRepocitorio.ObtenerUltimaAsistencia(sis.EventoGmail);

                if (ultimaA == null)
                {
                    _eventRepocitorio.RegistrarAsistencia(sis);
                    return true;
                }
            }
        }
        return false;
    }
}
