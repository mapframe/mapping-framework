<%-- 
    Document   : gerenciarPalestras
    Created on : 23/11/2017, 01:37:11
    Author     : isabella
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">    
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <h2 class="titulo-destaque">Lista de Palestras</h2>
            <p class="text-center">Fique por dentro das palestras que irão ocorrer nos próximos eventos!</p>
            <div class="line"></div> 
        </div>
    </div>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
        <div class="table-responsive">
            <table id="data_table" class="table table-bordered table-striped table-hover" class="display">
                <thead>
                    <tr class="cabecalho">
                        <th>Título</th>
                        <th>Palestrante</th>
                        <th>Data</th>
                        <th>Evento</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="evento" items="${eventos}">
                        <c:forEach var="palestra" items="${evento.palestras}">
                            <tr>
                                <td>${palestra.titulo}</td>
                                <td>${palestra.palestrante.nome}</td>
                                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${palestra.data}" /></td>
                                <td>${evento.nome}</td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        </div>
    </div>
</div> 

<script>
    $(function () {
        $('#data_table').DataTable( {
            "language": {
                "sEmptyTable": "Nenhuma palestra cadastrada :(",
                "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ palestras",
                "sInfoEmpty": "Mostrando 0 até 0 de 0 palestras",
                "sInfoFiltered": "(Filtrados de _MAX_ palestras)",
                "sInfoPostFix": "",
                "sInfoThousands": ".",
                "sLengthMenu": "_MENU_ resultados por página",
                "sLoadingRecords": "Carregando...",
                "sProcessing": "Processando...",
                "sZeroRecords": "Nenhuma palestra encontrada",
                "sSearch": "Pesquisar",
                "oPaginate": {
                    "sNext": "Próximo",
                    "sPrevious": "Anterior",
                    "sFirst": "Primeiro",
                    "sLast": "Último"
                },
                "oAria": {
                    "sSortAscending": ": Ordenar colunas de forma ascendente",
                    "sSortDescending": ": Ordenar colunas de forma descendente"
                }
            }
        });
    });
</script>

<style>
    .cabecalho {
        background: #337AB7;
        color: #FFF;
    }
    
    .table-hover tbody tr:hover {
        color: #337AB7;
    }
    
    table > tbody > tr > td:last-child a {
        margin-top: 3px;
        margin-bottom: 3px;
    } 
    
    table > tbody > tr > td {
        vertical-align: middle !important;
    }
</style>
