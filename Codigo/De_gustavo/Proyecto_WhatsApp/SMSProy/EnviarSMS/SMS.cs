using RestSharp;

namespace SMSProy.EnviarSMS;

public class SMS
{
    public static async Task<string> SendSMSAsync(string username, string token, List<string> recipients, string message)
    {
        var client = new RestClient("https://api.labsmobile.com");
        var request = new RestRequest("/json/send", Method.Post);
        
        // Configuración de los encabezados
        request.AddHeader("Cache-Control", "no-cache");
        request.AddHeader("Content-Type", "application/json");
        request.AddHeader("Authorization", "Basic " + Convert.ToBase64String(System.Text.Encoding.UTF8.GetBytes(username + ":" + token)));

        // Construcción del cuerpo de la solicitud (payload)
        var recipientsJson = string.Join(",", recipients.Select(r => $"{{ \"msisdn\": \"{r}\" }}"));
        var body = $@"{{
            ""message"": ""{message}"",
            ""tpoa"": ""Sender"",
            ""recipient"": [{recipientsJson}]
        }}";

        request.AddParameter("application/json", body, ParameterType.RequestBody);

        // Ejecución de la solicitud
        RestResponse response = await client.ExecuteAsync(request);

        // Comprobación de la respuesta
        if (response.ErrorException != null)
        {
            return "Error: " + response.ErrorException.Message;
        }
        else
        {
            return response.Content; // Devuelve el contenido de la respuesta
        }
    }
}


