$(function() {
    var $tab_evento     = $("#tab_evento");
    var $tab_local      = $("#tab_local");
    var $tab_palestras  = $("#tab_palestras");
    var $form_evento    = $("#form-evento");
    var $form_local     = $("#form-local");
    $(".tab-content a").click(function(event) {
        var id_btn = $( this ).attr('id');
        switch (id_btn) {
            case 'next_evento':
                if (! $form_evento[0].checkValidity() ) { // verifica se campos estao validos
                    event.preventDefault(); // previne mudanca de tab pela ancora
                    event.stopPropagation();
                    $form_evento.find(":submit").click(); // exibir os campos invalidos com msg padrao
                } else {
                    $tab_evento.removeClass("active");
                    $tab_local.addClass("active");
                }
                break;
            case 'next_local': 
                if (! $form_local[0].checkValidity() ) {    
                    event.preventDefault();
                    event.stopPropagation();
                    $form_local.find(":submit").click();
                } else {
                    $tab_local.removeClass("active");
                    $tab_palestras.addClass("active");
                }
                break;
            case 'previous_local':
                $tab_local.removeClass("active");
                $tab_evento.addClass("active");
                break;
            case 'previous_palestras':
                $tab_palestras.removeClass("active");
                $tab_local.addClass("active");
                break;
        }
    }); 
});