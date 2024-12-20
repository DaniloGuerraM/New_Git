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
            const totalAlumnos = document.getElementById('totalAlumnos');
            
            totalAlumnos.textContent = data.length;

            })
        .catch(error => {
            console.error('Error:', error);
            alert('Error al obtener los datos');
        });
}