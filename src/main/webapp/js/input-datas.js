/* global $j */

$j(function () {
    $j(".datepickercustom").datetimepicker( {
        format: 'DD/MM/YYYY'
    });

    $j(".dateinput").mask('00/00/0000');

    $j(".timepickercustom").datetimepicker( {
        format: 'HH:mm'
    });

    $j(".timeinput").mask('00:00'); 
});
                     
