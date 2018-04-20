/* global palestras */

$(function() {
    
    $("#finalizar_evento").on("click", function() {
        var $form_evento = $("#form-evento");
        var nome         = $form_evento.find("input[name='nome']").val();
        var descricao    = $form_evento.find("textarea[name='descricao']").val();
        if (descricao === "") descricao = "N/A";
        var inicio       = $form_evento.find("input[name='inicio']").val();
        var fim          = $form_evento.find("input[name='fim']").val();
        var $form_local  = $("#form-local");
        var endereco     = $form_local.find("select[name='endereco']").val();
        var nome_predio  = $form_local.find("[name='predio']").val(); 
        var predio       = new Predio(nome_predio);
        var id_evento    = $form_evento.find("input[name='id_evento_update']").val();
        id_evento = (id_evento === "") ? "0" : id_evento.toString();
        var evento       = new Evento(id_evento, nome, descricao, endereco, inicio, fim, predio, palestras);
        
        $.post({ 
            url: 'eventoServlet',
            data: { eventoJson: JSON.stringify(evento) },
            success: function(data) {
                location = JSON.parse(data).url;
            }
        });       
        
    });
    
});