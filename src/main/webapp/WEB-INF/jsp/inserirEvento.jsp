<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="t" %>  

<script src="js/tabs-navegation.js"></script>

<div class="container-fluid">
    <div class="row">
        <ul class="nav nav-tabs nav-justified"> 
            <li id="tab_evento" class="active"><a>Dados do evento</a></li>
            <li id="tab_local"><a>Dados do local</a></li>
            <li id="tab_palestras"><a>Palestras</a></li>
        </ul>
        <div class="tab-content">
            <c:choose>
                <c:when test="${not empty eventoUpdate}">
                    <t:tab-evento titulo="Alterar informações do evento"></t:tab-evento>
                    <t:tab-local titulo="Alterar informações do local"></t:tab-local>
                    <t:tab-palestras titulo="Alterar palestras"></t:tab-palestras>
                </c:when>
                <c:otherwise>
                    <t:tab-evento titulo="Informações do evento"></t:tab-evento>
                    <t:tab-local titulo="Informações do local"></t:tab-local>
                    <t:tab-palestras titulo="Palestras"></t:tab-palestras>
                </c:otherwise>
            </c:choose>  
        </div>
    </div> <!-- ./row -->
</div> <!-- ./container-fluid -->

<script src="js/input-datas.js"></script>
<script src="js/select-predios.js"></script>
<script src="js/evento-objetos.js"></script>
<script src="js/palestras.js"></script>
<script src="js/ajax_evento.js"></script>
<c:if test="${not empty eventoUpdate}">
    <script>
        <c:forEach var="p" items="${eventoUpdate.palestras}">
            var p_pal_nome        = "<c:out value="${p.palestrante.nome}" escapeXml="true" />";
            var p_pal_sexo        = "<c:out value="${p.palestrante.sexo}" escapeXml="true" />";
            var p_pal_grau        = "<c:out value="${p.palestrante.grauAcademico}" escapeXml="true" />";
            var p_pal_curso       = "<c:out value="${p.palestrante.cursoFormacao}" escapeXml="true" />";
            var p_pal_instituicao = "<c:out value="${p.palestrante.instituicaoFormacao}" escapeXml="true" />";
            var p_pal             = new Palestrante(p_pal_nome, p_pal_sexo, p_pal_grau, p_pal_curso, p_pal_instituicao);
            
            var p_id              = 0; 
            <c:if test="${not empty p.id}">
                p_id = ${p.id};
            </c:if>
                console.log(p_id);
            var p_sala_nome       = "<c:out value="${p.sala.nome}" escapeXml="true" />";
            var p_sala_publico    = ${p.sala.publicoPrevisto};
            var p_sala            = new Sala(p_sala_nome, p_sala_publico);
            var p_titulo          = "<c:out value="${p.titulo}" escapeXml="true" />";
            var p_assunto         = "<c:out value="${p.assunto}" escapeXml="true" />";
            var p_descricao       = "<c:out value="${p.descricao}" escapeXml="true" />";
            <fmt:formatDate pattern="dd/MM/yyyy" value="${p.data}" var="p_data" />
            <fmt:formatDate pattern="HH:mm" value="${p.horaInicio}" var="p_horaInicio" />  
            <fmt:formatDate pattern="HH:mm" value="${p.horaFim}" var="p_horaFim" />
            var p_data            = "${p_data}"; 
            var p_inicio          = "${p_horaInicio}";
            var p_fim             = "${p_horaFim}";            
             
            var palestra          = new Palestra(num_palestra, p_id, p_titulo, p_assunto, p_descricao, p_data, p_inicio, p_fim, p_sala, p_pal);

            palestras.push( palestra );

            // add resumo da palestra no html
            $(".list_palestras").append(
                "<div id='palestra-"+ num_palestra +"' class='resumo_palestra col-md-4 pull left'>\
                    <div class='panel panel-primary'>\
                        <div class='panel-heading'>Resumo\
                            <span class='palestra-btns pull-right'>\
                                <button value='"+ num_palestra + "' data-toggle='modal' data-target='#visualizar_palestra' data-keyboard='true' class='visualizar-palestra btn btn-success'><span class='fa fa-search-plus'></span></button>\
                                <button value='"+ num_palestra + "' class='remover-palestra btn btn-danger'><span class='glyphicon glyphicon-trash'></span></button>\
                            </span>\
                        </div>\
                        <div class='panel-body'>\
                            <p><label>Título:</label> "+ p_titulo +"</p>\
                            <p><label>Data:</label> "+ p_data +"</p>\
                            <p><label>Palestrante:</label> "+ p_pal_nome +"</p>\
                        </div>\
                    </div>\
                </div>"
            );
            num_palestra++;               
        </c:forEach>
    </script>
</c:if>