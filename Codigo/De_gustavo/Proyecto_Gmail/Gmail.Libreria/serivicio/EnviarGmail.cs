using System.Net.Mail;
using MailKit.Net.Smtp;
using MimeKit;
using SmtpClient = MailKit.Net.Smtp.SmtpClient;


namespace Gmail.Libreria.serivicio;

public class EnviarGmail
{
    public EnviarGmail(string des, string asu, string cue){

        EnviarCorreo(des, asu, cue);
    }
    static void EnviarCorreo(string destinatario, string asunto, string cuerpo)
    {
        var message = new MimeMessage();
        message.From.Add(new MailboxAddress("Danilo", "nachotom02@gmail.com"));
        message.To.Add(new MailboxAddress("", destinatario));
        message.Subject = asunto;

        message.Body = new TextPart("plain")
        {
            Text = cuerpo
        };

        using (var client = new SmtpClient())
        {
            // Conectar al servidor SMTP de Gmail
            client.Connect("smtp.gmail.com", 587, MailKit.Security.SecureSocketOptions.StartTls);

            // Autenticación
            client.Authenticate("nachotom02@gmail.com", "qhel bfct oqpi edpr"); // Usa una contraseña de aplicación aquí

            // Enviar el correo
            client.Send(message);
            client.Disconnect(true);
        }

        Console.WriteLine("Correo enviado con éxito.");
    }
}
