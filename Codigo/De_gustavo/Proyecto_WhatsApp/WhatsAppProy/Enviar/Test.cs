using RestSharp;
using System.Threading.Tasks;

namespace WhatsAppProy.Enviar;

public class Test
{
    // Definir una función asíncrona que envíe un mensaje
    public static async Task<string> SendMessageAsync(string instanceId, string token, string mobile, string message)
    {
        // Construcción de la URL para la solicitud
        var url = "https://api.ultramsg.com/" + instanceId + "/messages/chat";
        var client = new RestClient(url);
        var request = new RestRequest(url, Method.Post);

        // Configuración de los encabezados y parámetros
        request.AddHeader("content-type", "application/x-www-form-urlencoded");
        request.AddParameter("token", token);
        request.AddParameter("to", mobile);
        request.AddParameter("body", message);

        // Ejecutar la solicitud de manera asíncrona
        RestResponse response = await client.ExecuteAsync(request);

        // Devolver el contenido de la respuesta como resultado
        return response.Content;
    }
}
