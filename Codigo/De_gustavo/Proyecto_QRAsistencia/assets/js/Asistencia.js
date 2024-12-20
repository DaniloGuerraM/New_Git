document.addEventListener('DOMContentLoaded', obtenerDatos);

function obtenerAsistencia(event) {
    event.preventDefault();
    
    
    const dniAsistencia = parseInt(document.getElementById('dniAsistencia').value, 10);

    
    if (isNaN(dniAsistencia)) {
        alert("Por favor, ingresa un DNI válido.");
        return;
    }
    const url = "http://77.81.230.76:5095/api/RegistroAsistencia/"+ dniAsistencia;

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error en la solicitud');
            }
            return response.json();
        })
        .then(data => {
            const resultadoTable = document.getElementById('Asistencia').querySelector('tbody');
            resultadoTable.innerHTML = '';
			data.forEach(alumno => {
				const row = document.createElement('tr');
				const date = new Date(alumno.fecha * 1000);//.toISOString().slice(0, 19).replace('T', ' ');//.toLocaleTimeString("en-US");//.toLocaleDateString("en-US");//.toLocaleTimeString("en-US");
				//const date = moment.unix(alumno.fecha).format("DD/MM/YYYY");
				//date.toDateString();
				row.innerHTML = `
                    <td>${date.getFullYear()}</td>
					<td>${date.toLocaleTimeString()}</td>
					
				`;
				//<td>${alumno.idRegistro}</td>
				//<td>${alumno.alumnoDNI}</td>
				resultadoTable.appendChild(row);
			});			
            
        })
        .catch(error => {
            console.error('Error:', error);
            //alert('Error al obtener los datos');
            alert(error);
        });
}