$(function () {
    let highlightedDate = null;

    $("#datepicker").datepicker({
        dateFormat: "dd/mm/yy",
        beforeShowDay: function (date) {
            const day = date.getDay(); // Obtener el día de la semana (0 = domingo, 6 = sábado)
            const selectedTime = highlightedDate ? highlightedDate.getTime() : null;

            // Si es sábado (6) o domingo (0), aplicar clase "weekend"
            if (day === 0 || day === 6) {
                return [true, "weekend", "Fin de semana"];
            }

            // Resaltar la fecha seleccionada
            if (selectedTime && date.getTime() === selectedTime) {
                return [true, "highlight-date", "Fecha seleccionada"];
            }

            // Resaltar días de semana no seleccionados
            return [true, "non-selected-date", "Día no seleccionado"];
        }
    });

    $("#highlightButton").on("click", function () {
        const inputDate = $("#dateInput").val();
        try {
            const parsedDate = $.datepicker.parseDate("dd/mm/yy", inputDate);
            highlightedDate = parsedDate; 
            $("#datepicker").datepicker("refresh"); 
        } catch (error) {
            console.error("Error al procesar la fecha: ", error);
            alert("Por favor, ingrese una fecha válida en formato dd/mm/yyyy.");
        }
    });
});
/*$(function() {
    $( "#datepicker" ).datepicker( 
            {
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
                yearSuffix: ''
            },
        $.datepicker.regional[ "Es" ]);
});
*/

$(function() {
    $( "#datepicker2" ).datepicker({
         regional: "Es",
           showOn: "button",
           buttonImage: "../assets/image/calendario.jpg",
           buttonImageOnly: true,
           buttonText: "Seleccionar fecha",
           
           numberOfMonths: 3,
           showWeek: true,
           changeMonth: true,
           changeYear: true,
        //Rango de años que se puede visualizar
           yearRange: '2000:3000',

           //Mostrar números de la semana o bloquear días anteriores a hoy, solo poder seleccionar días en el futuro
            dateFormat: 'dd-mm-yy',
		    showWeek: true,
		    //minDate: 0,

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
           yearSuffix: ''},
       $.datepicker.setDefaults($.datepicker.regional['es']))
    });
