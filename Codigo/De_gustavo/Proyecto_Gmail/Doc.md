#Documentación del Código para Enviar Correos Electrónicos

##Descripción General

Este programa en C# utiliza la biblioteca MailKit para enviar correos electrónicos a través del servidor SMTP de Gmail. Permite especificar el destinatario, el asunto y el cuerpo del mensaje.

##Componentes del Código
Espacios de Nombres Utilizados

*using System.Net.Mail;
*using MailKit.Net.Smtp;
*using MimeKit;
*using SmtpClient = MailKit.Net.Smtp.SmtpClient;

System.Net.Mail: Espacio de nombres para las clases que manejan el envío de correo.
MailKit.Net.Smtp: Espacio de nombres que proporciona la funcionalidad de cliente SMTP.
MimeKit: Espacio de nombres para la creación de mensajes MIME.
SmtpClient: Alias para evitar conflictos con la clase SmtpClient de System.Net.Mail.

##Clase Principal

*class Program
*{

* static void Main()
* {
* EnviarCorreo("nachotom02@gmail.com", "desde el ites", "se le agradese de su participacion");
  * }
    *}
Program: Clase principal que contiene el punto de entrada del programa.
Main: Método estático que se ejecuta al iniciar la aplicación. Aquí se llama al método EnviarCorreo con un destinatario, asunto y cuerpo específicos.

##Método para Enviar Correo
*static void EnviarCorreo(string destinatario, string asunto, string cuerpo)
*{* var message = new MimeMessage();
* message.From.Add(new MailboxAddress("Danilo", "nachotom02@gmail.com"));
* message.To.Add(new MailboxAddress("", destinatario));
* message.Subject = asunto;
*
* message.Body = new TextPart("plain")
* {
* Text = cuerpo
  * };
EnviarCorreo: Método estático que envía un correo electrónico.
MimeMessage: Clase que representa el mensaje de correo electrónico.
From: Agrega la dirección de correo del remitente con un nombre visible.
To: Agrega la dirección de correo del destinatario.
Subject: Establece el asunto del mensaje.
Body: Establece el cuerpo del mensaje como texto plano.
##Conexión y Envío del Correo

*using (var client = new SmtpClient())
*{* // Conectar al servidor SMTP de Gmail
* client.Connect("smtp.gmail.com", 587, MailKit.Security.SecureSocketOptions.StartTls);
*
* // Autenticación
* client.Authenticate("correo@gmail.com", ""); // Usa una contraseña de aplicación aquí
*
* // Enviar el correo
* client.Send(message);
* client.Disconnect(true);
  *}


SmtpClient: Clase que proporciona funcionalidad para conectarse a un servidor SMTP.
client.Connect: Conecta al servidor SMTP de Gmail usando el puerto 587 y habilitando TLS para seguridad.
client.Authenticate: Realiza la autenticación con el servidor SMTP usando el correo y una contraseña de aplicación (recomendada por Gmail por razones de seguridad).
client.Send(message): Envía el mensaje de correo electrónico.
client.Disconnect(true): Desconecta el cliente del servidor SMTP.
##Mensaje de Confirmación

*Console.WriteLine("Correo enviado con éxito.");

Muestra un mensaje en la consola confirmando que el correo se ha enviado correctamente.
##Notas de Seguridad

Contraseña de Aplicación: Es recomendable utilizar una contraseña de aplicación generada a través de la configuración de seguridad de la cuenta de Gmail para evitar problemas de seguridad. Las contraseñas de aplicación son más seguras que usar la contraseña principal de la cuenta.
Permisos de Seguridad: Asegúrate de que tu cuenta de Gmail permita el acceso a aplicaciones menos seguras, o usa OAuth2 para mayor seguridad.

##Dependencias

Para utilizar este código, necesitas agregar la biblioteca MailKit a tu proyecto. Puedes hacerlo a través de NuGet Package Manager con el siguiente comando:

*bash

*Install-Package MailKit

##Ejemplo de Uso
Para enviar un correo, simplemente modifica los parámetros de la llamada al método EnviarCorreo en el método Main con los detalles que desees.
