# Empesamos con un nuevo proyecto 
## WhatsApp Proy
basicamente este proyecto se basa en poder desde c# enviar un WhatsApp a un numero cualquiera.
## Proyecto
crearemos dos proyecto:
1) uno tipo Libreria: es donde se encontrara la logica para mandar el WhatsApp; yo le llame "WhatsAppProy".
2) y en otro tipo Consola: solo si queremos agregar opciones como ingresar el numero de WhatsApp; yo le llame "MensajeProye".
Desde el proyecto  Consola agregaremos como referencia al otro proyecto Libreria

## usaremos
Para enviar mensajes a través de la API de WhatsApp usando c#; debe asegurarse de que la última versión de la biblioteca RestSharp : 108.0.1 esté lista y porque usaremos esta biblioteca para enviar mensajes a Ultramsg Gateway.

Para descargar la libreria desde la terminal: dotnet add package RestSharp --version 112.1.0 
algo asi: .\WhatsAppProy> dotnet add package RestSharp --version 112.1.0
## Crearemos WhatsApp
1) Una Carpeta que se llame "Enviar"(porque yo quise), El cual crearas una clase que la llamaras a tu gusto(yo le puse "test") el cual el codigo ara lo siguinete:

Este código implementa una clase llamada Test que contiene un método público y asíncrono llamado SendMessageAsync. Este método está diseñado para enviar un mensaje de WhatsApp utilizando la API de UltraMsg y devolver el resultado de la operación.

### Explicación detallada del código:
#### Parámetros de entrada del método:

instanceId: El identificador único de la instancia configurada en UltraMsg.
token: El token de autenticación asociado a la instancia.
mobile: El número de teléfono del destinatario, incluido el código de país.
message: El contenido del mensaje que se enviará.

#### Construcción de la URL:
Combina la URL base de UltraMsg con el instanceId para construir el endpoint donde se enviará la solicitud.

#### Configuración del cliente HTTP:
Utiliza la biblioteca RestSharp para realizar una solicitud HTTP POST.
Crea un objeto RestClient apuntando a la URL del servicio.
Configura un objeto RestRequest para realizar la solicitud.

#### Encabezados y parámetros:
Agrega un encabezado que indica que el contenido de la solicitud será del tipo application/x-www-form-urlencoded.
Añade los parámetros necesarios para la API:
token: Para autenticar la solicitud.
to: El número de teléfono del destinatario.
body: El mensaje que se enviará.

#### Ejecución de la solicitud:
Usa ExecuteAsync para realizar la solicitud de manera asíncrona.
Recoge la respuesta del servidor en un objeto RestResponse.

#### Resultado devuelto:

Devuelve el contenido (Content) de la respuesta como una cadena de texto. Esto suele contener información sobre el estado de la solicitud (éxito o error).

#### Comportamiento esperado:
Si la solicitud es exitosa, la respuesta podría indicar que el mensaje fue enviado correctamente.
Si hay un error (por ejemplo, credenciales inválidas o un problema con la instancia), la respuesta contendrá detalles sobre el error.
#### Ventajas del diseño:
Asíncrono: Permite realizar otras operaciones mientras se espera la respuesta, mejorando el rendimiento en aplicaciones grandes.
Reutilizable: Se puede usar para enviar mensajes con diferentes configuraciones simplemente cambiando los parámetros.
Sencillo: La lógica está claramente encapsulada en un solo método.

2) dentro del program de el proyecto de Consola esta un codigo que ara lo siguiente:
Este programa es un punto de entrada para una aplicación que utiliza la API de UltraMsg para enviar mensajes de WhatsApp. Al ejecutarlo, realiza las siguientes acciones:

* Muestra en la consola el texto "hola mundo" como una indicación inicial de que el programa se ha iniciado.
* Define varios datos necesarios para enviar un mensaje:
- Identificador de instancia: Es un valor que identifica de forma única la configuración de UltraMsg que se usará.
- Token de autenticación: Un código que valida el acceso autorizado a la API.
- Número de teléfono: El destinatario del mensaje, incluyendo el código de país.
- Mensaje: El contenido que se enviará al destinatario a través de WhatsApp.
* Llama a un método llamado SendMessageAsync, que está definido en la clase Test. Este método envía el mensaje de WhatsApp utilizando los datos previamente definidos y la API de UltraMsg.
* Espera la respuesta de la API, que indicará si el mensaje fue enviado correctamente o si hubo algún error.
* Muestra el resultado de la respuesta en la consola, permitiendo al usuario ver el estado de la operación.

### error
e parado porque para mandar WhatsApp de forma segura hay que pagar y yo no voy a pagar

este es el Link de donde saque esta informacion
(https://blog.ultramsg.com/es/enviar-mensaje-de-whatsapp-por-whatsapp-api-c-sharp/)
## Craremos SMS
como no se puede enviar WhatsApp de forma gratuita bamos a probar por SMS.

### poyecto
Crearemos un nuevo proyecto de tipo libreria llamado en este caso "SMSProy" el cual le tendremos que referenciar al proyecto de consola; en el proyecto SMSProy crearemos una carpeta llamada "EnviarSMS", en la cual crearemos una clase llamada "SMS" la cual en codigo que tiene ara lo siguiente:
#### Clase SMS:
Esta clase contiene un método estático asíncrono llamado SendSMSAsync, que es responsable de enviar los mensajes SMS utilizando la API de LabsMobile.

##### Parámetros del método SendSMSAsync:

* username: El nombre de usuario que se usa para autenticar la solicitud con la API de LabsMobile.
* token: El token de autenticación para validar la solicitud con la API.
* recipients: Una lista de números de teléfono de los destinatarios a quienes se enviará el mensaje.
* message: El contenido del mensaje que se enviará.
##### Proceso dentro de SendSMSAsync:

* Se crea un cliente HTTP (usando RestClient de RestSharp) que se conecta a la API de LabsMobile.
* Se define una solicitud de tipo POST hacia el endpoint /json/send de la API, que es donde se envían los mensajes SMS.

##### Se añaden encabezados HTTP:
* Cache-Control: Se configura en "no-cache" para evitar el almacenamiento en caché de la respuesta.
* Content-Type: Se define como application/json porque el cuerpo de la solicitud estará en formato JSON.
* Authorization: Se configura para usar la autenticación básica con el nombre de usuario y el token, codificados en Base64.
* Cuerpo de la solicitud (payload): Se prepara el JSON que contiene el mensaje, el identificador del remitente (tpoa), y los destinatarios. Los números de teléfono se proporcionan en formato JSON, y el mensaje se coloca en el campo correspondiente.
* Ejecutar la solicitud: Se ejecuta la solicitud HTTP de manera asíncrona con await client.ExecuteAsync(request), lo que permite que el programa no se bloquee mientras espera la respuesta.
* Manejo de respuesta: Si ocurre un error, el programa captura la excepción y muestra el mensaje de error. Si la solicitud es exitosa, se devuelve el contenido de la respuesta (detalles sobre si el mensaje se envió correctamente o no).
#### Clase Program:
Esta es la clase principal que inicia el proceso de envío del mensaje SMS.

##### Proceso en Program:
Primero, se imprime un mensaje en la consola: "hola mundo", lo que indica que el programa ha comenzado a ejecutarse.
##### Definición de datos necesarios:
* username y token: Se proporcionan como credenciales para autenticarte en la API de LabsMobile.
* message: El texto del mensaje que se enviará a los destinatarios. En este caso, es un mensaje promocional.
* recipients: Una lista de números de teléfono de los destinatarios a los que se enviará el SMS. Se define como una lista de cadenas (strings).
* Llamada al método SendSMSAsync: Usando los datos definidos previamente, el método SendSMSAsync es llamado para enviar el mensaje a los destinatarios. La ejecución de este método es asíncrona, por lo que se espera su finalización utilizando await.
* Mostrar la respuesta: Una vez que se obtiene la respuesta de la API (que puede ser un mensaje de éxito o error), se imprime en la consola.

para generar el username y el token necesarios para autenticarte en la API de LabsMobile




Registrarse en LabsMobile:
1. Primero, necesitas tener una cuenta en LabsMobile. Si aún no tienes una cuenta, regístrate en su sitio web: https://www.labsmobile.com/es.

2. Acceder al panel de administración:
Para registrartes es necesario un email corporativo.
Una vez que te hayas registrado y hayas iniciado sesión, accede al panel de administración de LabsMobile. Aquí podrás gestionar tus envíos de SMS y obtener las credenciales necesarias.

3. Obtener el username y el token:
username: El nombre de usuario es el nombre de tu cuenta o ID de usuario dentro del sistema de LabsMobile.

Generalmente, puedes encontrarlo en la configuración de tu cuenta o en el área de API Settings (Configuraciones de la API).
token: El token es una clave de autenticación que se utiliza para realizar solicitudes a la API de LabsMobile.

En el panel de administración de LabsMobile, busca la sección relacionada con API o Integración. Dentro de esta sección, deberías poder generar un token de API.
El token será una cadena larga y única que se utiliza para autenticar las solicitudes que haces a la API.

4. Usar las credenciales en tu código:
Una vez que tengas tanto el nombre de usuario como el token, deberías utilizarlos en tu código para autenticar la solicitud.
El Authorization en tu código debe estar configurado correctamente, codificando las credenciales en Base64. Aquí está el fragmento que se usa en el código:

request.AddHeader("Authorization", "Basic " + Convert.ToBase64String(System.Text.Encoding.UTF8.GetBytes(username + ":" + token)));
Asegúrate de reemplazar username y token con los valores correctos que obtuviste de LabsMobile.

5. Comprobación adicional:
Verifica que tu cuenta esté activa: Algunas veces, si hay problemas con el pago o si la cuenta está inactiva, las credenciales pueden no funcionar.
Verifica el formato de las credenciales: Asegúrate de que el nombre de usuario y el token no tengan espacios extraños y estén correctamente introducidos en el código.