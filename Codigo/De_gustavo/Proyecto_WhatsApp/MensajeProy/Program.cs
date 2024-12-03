using System;
using WhatsAppProy.Enviar;
using SMSProy.EnviarSMS;
using System.Collections.Generic;

public class Program
{
    static async Task Main(string[] args)
    {
        Console.WriteLine("hola mundo");
        /*
        string instanceId = "instance950"; // tu instanceId
        string token = "yourtoken";        // tu token
        string mobile = "2334413771";      // número de teléfono del destinatario
        string message = "WhatsApp API on UltraMsg.com works good"; // mensaje

        // Llamada a la función para enviar el mensaje
        string response = await Test.SendMessageAsync(instanceId, token, mobile, message);

        // Mostrar la respuesta en la consola
        Console.WriteLine(response);
        */
         // Datos necesarios para el envío del SMS
        string username = "myUsername";  // Tu nombre de usuario
        string token = "myToken";        // Tu token de autenticación
        string message = "Don't miss our Sale! Use code XXXX123 for 20% off."; // El mensaje a enviar

        // Lista de números de teléfono a los que se enviará el SMS
        var recipients = new List<string>
        {
            "2334413771"  // Número 1
        };

        // Llamada a la función para enviar el mensaje SMS
        string response = await SMS.SendSMSAsync(username, token, recipients, message);

        // Mostrar la respuesta en la consola
        Console.WriteLine(response);
    }

}