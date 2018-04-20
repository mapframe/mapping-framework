<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">    
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <h2 class="titulo-destaque">Lista de eventos</h2>
            <p class="text-center">Fique por dentro dos próximos eventos que irão ocorrer dentro e fora da UFSM!</p>
            <div class="line"></div> 
        </div>
    </div>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
        <div class="table-responsive">
            <table id="lista_eventos" class="table table-bordered table-striped table-hover" class="display">
                <thead>
                    <tr class="cabecalho">
                        <th>Evento</th>
                        <th>Inicio</th>
                        <th>Fim</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="evento" items="${eventos}">
                        <tr>
                            <td>${evento.nome}</td>
                            <td><fmt:formatDate pattern="dd/MM/yyyy" value="${evento.dataInicio}" /></td>
                            <td><fmt:formatDate pattern="dd/MM/yyyy" value="${evento.dataFim}" /></td>
                            <td>
                                <a href="eventoServlet?action=update&id_evento=${evento.id}" class="btn btn-default"><i class="glyphicon glyphicon-pencil" aria-hidden="true"></i></a>
                                <a data-toggle="modal" data-target="#confirmacao" data-id="${evento.id}" class="modal-confirmacao btn btn-danger"><i class=" glyphicon glyphicon-trash" aria-hidden="true"></i></a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        </div>
    </div>
</div> 

<div class="modal fade" id="confirmacao" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title text-center">Confirmar exclusão</h4>
            </div>
            <div class="modal-body text-center">
                <p>Você tem certeza que deseja deletar o evento?</p>
            </div>
            <div class="modal-footer">
                <form action="eventoServlet" method="get">
                    <input type="hidden" name="action" value="delete" />
                    <input type="hidden" name="id_evento" />
                    <button type="submit" class="btn btn-danger"><i class="fa fa-times" aria-hidden="true"></i> Deletar</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-check" aria-hidden="true"></i> Manter</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        $('#lista_eventos').DataTable( {
            "language": {
                "sEmptyTable": "Nenhum evento cadastrado :(",
                "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ eventos",
                "sInfoEmpty": "Mostrando 0 até 0 de 0 eventos",
                "sInfoFiltered": "(Filtrados de _MAX_ eventos)",
                "sInfoPostFix": "",
                "sInfoThousands": ".",
                "sLengthMenu": "_MENU_ resultados por página",
                "sLoadingRecords": "Carregando...",
                "sProcessing": "Processando...",
                "sZeroRecords": "Nenhum evento encontrado",
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
    
    table > thead > tr > th:last-child,
    table > tbody > tr > td:last-child {
        text-align: center;
    }
    
    table > tbody > tr > td:last-child a {
        margin-top: 3px;
        margin-bottom: 3px;
    } 
    
    table > tbody > tr > td {
        vertical-align: middle !important;
    }
      
    #confirmacao .modal-dialog.modal-sm {
        padding-top: 15%;
    }

    #confirmacao .modal-footer {
        text-align: center;
    }

    #confirmacao .btn {
        width: 100px;
    }
</style>

<script>
    $(function() {
        $("#lista_eventos").on("click", ".modal-confirmacao", function() {
           var id_evento = $( this ).data("id");
           $("#confirmacao").find("input[name='id_evento']").val(id_evento);
        });
    });
</script>