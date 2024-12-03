# Documentacio
## Introduccion
Este es un programa que nos muestra un calendario

## Documentacion de referencia
[Link](https://www.netveloper.com/como-crear-un-calendario-con-jquery-y-html#uno)

##¿Qué es jQuery Datepicker?
Es un widget de calendario interactivo que permite a los usuarios seleccionar fechas en un formulario. Es altamente configurable y permite añadir estilos, personalizaciones, y funcionalidades adicionales.

### Calendario básico
El ejemplo inicial presenta un calendario básico que se muestra al hacer clic en un campo de texto. Por defecto, este calendario usa el formato de fecha en inglés (MM/DD/AAAA). Se puede personalizar el idioma y el formato mediante ficheros de traducción.

### Cambio de idioma
Para mostrar el calendario en español u otros idiomas, se necesita un archivo de traducción específico. El idioma se configura usando $.datepicker.regional['es']. Este objeto define textos como nombres de días, meses, y configuraciones de formato. Es posible usar traducciones preexistentes o crear manualmente nuevos archivos traducidos.

### Tipos de calendarios personalizables
El Datepicker puede configurarse de varias formas:

Calendario en línea sin caja de texto: Se reemplaza el campo de texto por un elemento <div> para mostrar un calendario directamente en la página.
Calendario con ícono: Se puede usar un ícono (por ejemplo, una imagen) para abrir el calendario, añadiendo propiedades como showOn y buttonImage.
Navegación por años y meses: Se habilitan selectores desplegables para cambiar directamente entre meses y años con changeMonth y changeYear. También es posible restringir el rango de años visibles.
Múltiples meses: Se pueden mostrar varios meses simultáneamente en el calendario configurando numberOfMonths.

### Bloqueo de fechas y opciones avanzadas
Se pueden restringir las fechas seleccionables:

Fechas futuras únicamente: Con minDate: 0 se bloquean las fechas anteriores al día actual.
Mostrar semanas: Con showWeek: true se añade una columna que numera las semanas.
### Personalización del diseño
Usando CSS, se puede personalizar el aspecto del calendario. Algunos ejemplos:

Cambiar colores, fuentes o sombras del calendario (.ui-datepicker).
Resaltar días específicos como fines de semana (.ui-datepicker-week-end).
Cambiar el formato de la fecha usando dateFormat (por ejemplo, 'dd-mm-yy').

### Mostrar varios meses
Para casos donde se necesite mayor visibilidad, se pueden mostrar varios meses en pantalla simultáneamente con la propiedad numberOfMonths. También se pueden habilitar opciones de navegación por años y meses para una experiencia más completa.

