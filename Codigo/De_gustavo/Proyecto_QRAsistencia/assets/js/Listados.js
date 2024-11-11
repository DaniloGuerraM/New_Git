document.addEventListener('DOMContentLoaded', obtenerDatos);

function obtenerDatos() {
    const url = "http://77.81.230.76:5095/api/alumno";

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error en la solicitud');
            }
            return response.json();
        })
        .then(data => {
            const resultadoTable = document.getElementById('resultado').querySelector('tbody');
            const totalAlumnos = document.getElementById('totalAlumnos');
            resultadoTable.innerHTML = ''; // Limpiar cualquier contenido previo

            // Mostrar el número total de alumnos
            totalAlumnos.textContent = data.length;

            // Construir una fila para cada alumno
            data.forEach(alumno => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${alumno.dni}</td>
                    <td>${alumno.nombre}</td>
                    <td>${alumno.apellido}</td>
                    <td>${alumno.mac || 'No disponible'}</td>
                `;
                resultadoTable.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error al obtener los datos');
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