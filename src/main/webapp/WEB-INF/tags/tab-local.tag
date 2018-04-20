<%-- 
    Document   : tab-info-local
    Created on : Nov 17, 2017, 2:16:02 PM
    Author     : rhau
--%>

<%@tag description="aba local" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@attribute name="titulo" required="true"%>

<%-- any content can be specified here e.g.: --%>
<div id="local" class="tab-pane fade">
    <h2 class="titulo-destaque">${titulo}</h2>
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-primary">
                <div class="panel-heading">Sobre o local</div>
                <div class="panel-body">
                    <form id="form-local">
                        <div class="form-group">
                            <label for="enderecoSel">Endereço</label>
                            <select id="enderecoSel" name="endereco" class="form-control" required>
                                <option value="" selected disabled>Escolha o endereço</option>
                                <option value="Av. Roraima, 1000 - Camobi">Campus</option>
                                <option value="R. Floriano Peixoto, 1184 - Centro">Antiga Reitoria</option>
                            </select>
                        </div>                                
                        <div class="form-group">
                            <label>Prédio</label>
                            <input type="text" id="predio" class="form-control" disabled/>
                            <select id="predioSel" class="form-control">
                                <option value="" selected disabled>Escolha o Prédio</option>
                                <option value="5">05 - CTISM - Colégio Técnico de Industrial Santa Maria</option>
                                <option value="7">07 - Centro de tecnologia</option>
                                <option value="13">13 - Centro de Ciências Naturais e Exatas</option>
                                <option value="16">16 - Centro de Educação </option>
                                <option value="26">26 - Centro de Ciências da Saúde </option>
                                <option value="40">40 - Centro de Artes e Letras </option>
                                <option value="42">42 - Centro de Ciências Rurais</option>
                                <option value="48">48 - Centro de Processamento de Dados</option>
                                <option value="51">51 - Centro de Educação Física e Desportos</option>
                                <option value="70">70 - Colégio Politécnico</option> 
                                <option value="74">74 - Centro de Ciências Sociais e Humanas</option>
                                <option value="Reitoria">Reitoria</option>
                                <option value="Espaço Multiuso">Espaço Multiuso</option>
                                <option value="Centro de Convenções">Centro de Convenções</option>
                                <option value="Centro de Eventos">Centro de Eventos</option>
                            </select>
                        </div>
                        <input type="submit" class="hidden" />
                    </form>
                </div> <!-- ./panel-body -->
            </div> <!-- ./panel -->
        </div> <!-- ./col -->
    </div> <!-- ./row -->
    <div class="row"> 
        <a href="#evento" id="previous_local" data-toggle="tab" class="btn btn-lg btn-primary btn-nav pull-left"><span class="glyphicon glyphicon-chevron-left"></span> Anterior</a>
        <a href="#palestras" id="next_local" data-toggle="tab" class="btn btn-lg btn-primary btn-nav pull-right">Próximo <span class="glyphicon glyphicon-chevron-right"></span></a>
    </div>
</div> <!-- ./tab-pane -->

<c:if test="${not empty eventoUpdate}">
    <script>
        $(function() {
            var _enderecoSel = $("#enderecoSel");
            var _predio      = $("#predio");
            var _predioSel   = $("#predioSel");
            _enderecoSel.val("${eventoUpdate.endereco}");
            if (_enderecoSel.val() === "R. Floriano Peixoto, 1184 - Centro") {
                _predioSel.hide();
                _predioSel.prop('selectedIndex', 0);
                _predioSel.prop("required", false);
                _predioSel.prop("name", null);
                _predio.show();
                _predio.prop("name", "predio");
                _predio.val("99 - Centro de Ciências Sociais e Humanas");
            } else {
                _predio.hide();
                _predio.prop("name", null);
                _predioSel.show();
                _predioSel.prop("name", "predio");
                _predioSel.prop("required", true);
                _predioSel.val('${eventoUpdate.predio.nome}');
            }
        });
    </script>
</c:if>