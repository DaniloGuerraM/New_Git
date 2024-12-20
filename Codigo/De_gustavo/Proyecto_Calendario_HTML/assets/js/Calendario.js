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
       
        numberOfMonths: 3,
        showWeek: true,
        changeMonth: true,
        changeYear: true,
       
       
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

        const url = "http://77.81.230.76:5095/api/RegistroAsistencia/" + dni; 

        fetch(url)
            .then(response => {
                if (!response.ok) {
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
    });
});


