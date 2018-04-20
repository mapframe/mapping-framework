<%-- 
    Document   : tab-dados-evento
    Created on : Nov 17, 2017, 2:15:23 PM
    Author     : rhau
--%>

<%@tag description="aba evento" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="t" %>  

<%@attribute name="titulo" required="true"%>

<%-- any content can be specified here e.g.: --%>
<div id="evento" class="tab-pane fade in active">
    <h2 class="titulo-destaque">${titulo}</h2>
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-primary">
                <div class="panel-heading">Sobre o evento</div>
                <div class="panel-body">
                    <form id="form-evento">
                        <input type="hidden" id="id_evento_update" name="id_evento_update" value="${eventoUpdate.id}">
                        <t:input-text type="text" id="evento_nome" name="nome" label="Nome" placeholder="Digite o nome do evento" value="${eventoUpdate.nome}"></t:input-text>
                        <t:text-area id="evento_descricao" name="descricao" label="Descrição" placeholder="Descreva o evento" value="${eventoUpdate.descricao}"></t:text-area>
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${eventoUpdate.dataInicio}" var="dataInicio" />  
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${eventoUpdate.dataFim}" var="dataFim"/>              
                        <t:input-date tipo="date" id="evento_inicio" name="inicio" label="Data inicio" placeholder="Selecione a data inicial do evento" value="${dataInicio}"></t:input-date>
                        <t:input-date tipo="date" id="evento_fim" name="fim" label="Data fim" placeholder="Selecione a data final do evento" value="${dataFim}"></t:input-date>
                        <input type="submit" class="hidden" />
                    </form>
                </div>
            </div>  
        </div> <!-- ./col -->
    </div> <!-- ./row -->
    <div class="row"> 
        <a href="#local" id="next_evento" data-toggle="tab" class="btn btn-lg btn-primary btn-nav pull-right">Próximo <span class="glyphicon glyphicon-chevron-right"></span></a>
    </div>         
</div> <!-- ./tab-pane -->

   
                        