
// esta funcion es para obtener a un alumno y sus asistencia
$(function () {
    let highlightedDates = []; // Array para almacenar las fechas a resaltar

    // Inicializa el calendario
    $("#dniInput").datepicker({
        regional: "Es",
        showOn: "button",
        buttonImage: "../assets/image/calendario.jpg",
        buttonImageOnly: true,
        buttonText: "Seleccionar fecha",
        
        numberOfMonths: 3,
        showWeek: true,
        changeMonth: true,
        changeYear: true,
        
        yearRange: '2000:3000',

        dateFormat: 'dd-mm-yy',
        showWeek: true,

        closeText: 'Cerrar',
        prevText: '<Ant',
        nextText: 'Sig>',
        currentText: 'Hoy',
        monthNames: ['enero','febrero','marzo','abril','mayo','junio',
        'julio','agosto','septiembre','octubre','noviembre','diciembre'],
        monthNamesShort: ['ene','feb','mar','abr','may','jun',
        'jul','ago','sep','oct','nov','dic'],
        dayNames: ['domingo','lunes','martes','miércoles','jueves','viernes','sábado'],
        dayNamesShort: ['dom','lun','mar','mié','jue','vie','sáb'],
        dayNamesMin: ['D','L','M','X','J','V','S'],
        weekHeader: 'Sm',
        dateFormat: 'dd/mm/yy',
        firstDay: 1,
        isRTL: false,
        showMonthAfterYear: false,
        yearSuffix: '',
       
       
       
        dateFormat: 'dd/mm/yy', 
        firstDay: 1, 
        beforeShowDay: function (date) {
            const day = date.getDay();
            const dateString = $.datepicker.formatDate("dd/mm/yy", date); 
            const isHighlighted = highlightedDates.includes(dateString); 

            if (day === 0 || day === 6) {
                return [true, "weekend", "Fin de semana"];
            }

            if (isHighlighted) {
                return [true, "highlight-date", "Fecha resaltada"];
            }

            return [true, "non-selected-date", "Día no seleccionado"];
        },
        onClose: function () {
            console.log("Calendario cerrado");
        }
    });

    $.datepicker.setDefaults($.datepicker.regional['es']);

    $("#fetchDatesButton").on("click", function () {
        const dni = $("#dniInput").val(); 
        if (!dni) {
            alert("Por favor, ingrese un DNI válido.");
            return;
        }
        // url para obtener las asistencias
        const url = "http://77.81.230.76:5095/api/RegistroAsistencia/" + dni; 
        // url para obtener al alumno
        const url2 = "http://77.81.230.76:5095/api/alumno/Dni?dni=" + dni;


        fetch(url)
        .then(response => {
            if (!response.ok) {
                mostrarAlerta('Error al obtener los datos de la API.');
                throw new Error("Error al obtener los datos de la API.");
            }
            return response.json();
        })
        .then(data => {
            highlightedDates = []; 

            data.forEach(item => {
                const date = new Date(item.fecha * 1000); 
                const formattedDate = $.datepicker.formatDate("dd/mm/yy", date); 
                highlightedDates.push(formattedDate); 
            });

            $("#datepicker").datepicker("refresh"); 
        })
        .catch(error => {
            console.error("Error:", error);
            alert("Error al obtener las fechas. Por favor, intente nuevamente.");
        });

        fetch(url2)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al obtener los datos de la API.');
            }
            return response.text(); 
        })
        .then(responseText => {
            let data;
            try {
                data = JSON.parse(responseText);
            } catch (e) {
                
                document.getElementById('DNI').innerText = 'sin datos';
                document.getElementById('Nombre').innerText = 'sin datos';
                document.getElementById('Apellido').innerText = 'sin datos';
                document.getElementById('Mac').innerText = 'sin datos';
                return;
            }
            document.getElementById('DNI').innerText = data.dni;
            document.getElementById('Nombre').innerText = data.nombre;
            document.getElementById('Apellido').innerText = data.apellido;
            document.getElementById('Mac').innerText = data.mac;
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('UnAlumno').innerText = 'Error al obtener los datos';
        });
    });
});
// funcion para mostrar alertas
function mostrarAlerta(mensaje, tipo) {
    var tipoa = 'alerta' + tipo;
    const alerta = document.getElementById(tipoa);
    const alertaMensaje = document.getElementById(tipo);
    
    alertaMensaje.textContent = `${mensaje}`;
    alerta.style.display = 'block';

    // Cierra la alerta después de 3 segundos
    setTimeout(() => {
        alerta.style.display = 'none';
    }, 3000);
}
// para poder agregar a un alumno ingresando  en DNI, Nombre y Apellido del Alumno a agregar
function PostRequest() {

    const dni = parseInt(document.getElementById('dniPost').value, 10);
    const nombre = document.getElementById('nombrePost').value;
    const apellido = document.getElementById('apellidoPost').value;
    const mac = null;
    const data = {
        dni: dni,
        nombre: nombre,
        apellido: apellido,
        mac: mac
    };

    const url = 'http://77.81.230.76:5095/api/Alumno';

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        const contentType = response.headers.get("content-type");
        if (contentType && contentType.includes("application/json")) {
            return response.json();
        } else {
            return response.text();
        }
    })
    .then(data => {
        console.log('Respuesta del servidor:', data);
        //alert(`Solicitud POST exitosa: ${typeof data === 'string' ? data : JSON.stringify(data)}`);
        mostrarAlerta('Agregado con exito', '1');
    })
    .catch(error => {
        console.error('Error:', error);
        //alert(`Error en solicitud POST: ${error.message}`);
        mostrarAlerta('Error al Agregar', '1');
    });
}
// para poder actualizar a un alumno ingresando el DNI como referencia, le podras cambiar el nombre y el apellido
function AlumnoPutRequest() {
    const dni = parseInt(document.getElementById('dniPutA').value, 10);
    const nombre = document.getElementById('nombrePutA').value;
    const apellido = document.getElementById('apellidoPutA').value;
    const mac = null;


    if (isNaN(dni) || !nombre || !apellido ) {
        //alert("");
        mostrarAlerta('Por favor, completa todos los campos correctamente.');
        return;
    }


    const data = {
        dni: dni,
        nombre: nombre,
        apellido: apellido,
        mac: mac
    };


    const url = "http://77.81.230.76:5095/api/Alumno/dni";


    fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (!response.ok) throw new Error(`Error ${response.status}: ${response.statusText}`);
        return response.text(); // Procesa la respuesta del servidor
    })
    .then(data => {
        //console.log('Respuesta del servidor:', data);
        //alert(`Solicitud PUT exitosa: ${data}`);
        mostrarAlerta('Actualizado con exito', '2');
    })
    .catch(error => {
        //console.error('Error:', error);
        //alert(`Error en solicitud PUT: ${error.message}`);
        mostrarAlerta('Error', '2');
    });
}

// para poder remover a un alumno ingresando solo el DNI
function DeleteRequest() {
    

    const dni = parseInt(document.getElementById('dniDelete').value, 10);

    
    if (isNaN(dni)) {
        alert("Por favor, ingresa un DNI válido.");
        return;
    }

    
    const url = "http://77.81.230.76:5095/api/Alumno?dni=" + dni;
    
    fetch(url, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (!response.ok) throw new Error(`HTTP status ${response.status}`);
        return response.text();
    })
    .then(data => {
        console.log('Respuesta del servidor:', data);
        //alert(`Solicitud DELETE exitosa: ${data}`);
        mostrarAlerta('Removido con exito', '3');

    })
    .catch(error => {
        console.error('Error:', error);
        //alert(`Error en solicitud DELETE: ${error.message}`);
        mostrarAlerta('Error al Remover', '3');
    });
}
