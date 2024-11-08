function PostRequest() {

    const dni = parseInt(document.getElementById('dniPost').value, 10);
    const nombre = document.getElementById('nombrePost').value;
    const apellido = document.getElementById('apellidoPost').value;
    const mac = document.getElementById('macPost').value;

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
        alert(`Solicitud POST exitosa: ${typeof data === 'string' ? data : JSON.stringify(data)}`);
    })
    .catch(error => {
        console.error('Error:', error);
        alert(`Error en solicitud POST: ${error.message}`);
    });
}
////////////////////////////////////////////////////////////////////////////////////////////////////////
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
        alert(`Solicitud DELETE exitosa: ${data}`);
    })
    .catch(error => {
        console.error('Error:', error);
        alert(`Error en solicitud DELETE: ${error.message}`);
    });
}
////////////////////////////////////////////////////////////////////////////////////////////////////////
function PutRequest() {
    const dni = parseInt(document.getElementById('dniPut').value, 10);
    const idAndroid = document.getElementById('idAndroidPut').value;

    const data = {
        dni: dni,
        mac: idAndroid
    };

    const url = "http://77.81.230.76:5095/api/Alumno/mac";

    fetch(url, {
        method: 'PUT',
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
        alert(`Solicitud PUT exitosa: ${typeof data === 'string' ? data : JSON.stringify(data)}`);
    })
    .catch(error => {
        console.error('Error:', error);
        alert(`Error en solicitud PUT: ${error.message}`);
    });
}
////////////////////////////////////////////////////////////////////////////////////////////////////////
function obtenerAlumno(event) {
    event.preventDefault();
    
    const dniget = parseInt(document.getElementById('dniGet').value, 10);

    if (isNaN(dniget)) {
        alert("Por favor, ingresa un DNI válido.");
        return;
    }

    const url = "http://77.81.230.76:5095/api/alumno/Dni?dni=" + dniget;

    fetch(url)
        .then(response => {
            if (response.ok)
            {
                var instanciaobjeto = response.json();
                return instanciaobjeto;
            }
        })
        .then(data => {
            
            document.getElementById('DNI').innerText = data.dni;
            document.getElementById('Nombre').innerText = data.nombre;
            document.getElementById('Apellido').innerText = data.apellido;
            document.getElementById('Mac').innerText = data.mac;


            //document.getElementById('UnAlumno').innerText = JSON.stringify(data, null, 2);
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('UnAlumno').innerText = 'Error al obtener los datos';
        });
}
////////////////////////////////////////////////////////////////////////////////////////////////////////
function AlumnoPutRequest() {
    const dni = parseInt(document.getElementById('dniPutA').value, 10);
    const nombre = document.getElementById('nombrePutA').value;
    const apellido = document.getElementById('apellidoPutA').value;
    const mac = document.getElementById('macPutA').value;


    if (isNaN(dni) || !nombre || !apellido || !mac) {
        alert("Por favor, completa todos los campos correctamente.");
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
        console.log('Respuesta del servidor:', data);
        alert(`Solicitud PUT exitosa: ${data}`);
    })
    .catch(error => {
        console.error('Error:', error);
        alert(`Error en solicitud PUT: ${error.message}`);
    });
}
