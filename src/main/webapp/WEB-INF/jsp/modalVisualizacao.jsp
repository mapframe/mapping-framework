<%-- 
    Document   : modalVisualizacao
    Created on : Nov 23, 2017, 3:58:52 PM
    Author     : rhau
--%>


<%@taglib tagdir="/WEB-INF/tags" prefix="t" %>  

<div id="visualizar_palestra" class="modal fade" tabindex='-1' role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Visualização da palestra</h4>
            </div>
            <div class="row modal-body">
                <form id="visualizacao-form-palestra">
                <div class="col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">Dados da palestra</div>
                        <div class="panel-body">
                            <t:input-text type="text" id="visualizacao_palestra_titulo" name="titulo" label="Título" placeholder="Título da palestra"></t:input-text>
                            <t:input-text type="text" id="visualizacao_palestra_assunto" name="assunto" label="Assunto" placeholder="Área ou linha de pesquisa relacionada"></t:input-text>
                            <t:text-area id="visualizacao_palestra_descricao" name="descricao" label="Descrição" placeholder="Resumo da palestra"></t:text-area>
                            <t:input-date tipo="date" id="visualizacao_palestra_data" name="data" label="Data" placeholder="Selecione a data"></t:input-date>
                            <div class="form-group">
                                <label for="visualizacao_palestra_inicio">Horário</label>
                                <div class="row">
                                    <div class="col-xs-6">
                                        <div class="input-group date timepickercustom">
                                            <input type='text' id="visualizacao_palestra_inicio" name="inicio" placeholder="Início" class="form-control timeinput" required />
                                            <span class="input-group-addon">
                                                <span class="glyphicon glyphicon-time"></span>
                                            </span>
                                        </div>      
                                    </div>
                                    <div class="col-xs-6">
                                        <div class="input-group date timepickercustom">
                                            <input type='text' id="visualizacao_palestra_fim" name="fim" placeholder="Fim" class="form-control timeinput" required />
                                            <span class="input-group-addon">
                                                <span class="glyphicon glyphicon-time"></span>
                                            </span>
                                        </div>            
                                    </div>
                                </div>
                            </div>
                            <t:input-text type="text" id="visualizacao_palestra_sala" name="sala" label="Sala" placeholder="Sala onde ocorrerá a palestra"></t:input-text>
                            <t:input-text type="number" id="visualizacao_palestra_publico" name="publico" label="Número de pessoas" placeholder="Público previsto" extra="min='0'"></t:input-text>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">Dados do palestrante</div>
                        <div class="panel-body">
                            <t:input-text type="text" id="visualizacao_palestrante_nome" name="nome" label="Nome" placeholder="Digite o nome do palestrante"></t:input-text>
                            <div class="form-group">
                                <label for="visualizacao_palestrante_sexo">Sexo</label>
                                <select id="visualizacao_palestrante_sexo" name="sexo" class="form-control" required>
                                    <option value="" selected disabled>Selecione</option>
                                    <option value="M">Masculino</option>
                                    <option value="F">Feminino</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="visualizacao_palestrante_grau_academico">Grau acadêmico</label>
                                <select id="visualizacao_palestrante_grau_academico" name="grau_academico" class="form-control" required>
                                    <option  value="" selected disabled>Selecione</option>
                                    <option value="Tecnólogo">Tecnólogo</option>
                                    <option value="Graduação">Graduação</option>
                                    <option value="Pós graduação">Pós graduação</option>
                                    <option value="Mestrado">Mestrado</option>
                                    <option value="Doutorado">Doutorado</option>
                                </select>                                              
                            </div>
                            <t:input-text type="text" id="visualizacao_palestrante_curso" name="curso" label="Curso de formação" placeholder="Curso de formação do palestrante"></t:input-text>
                            <t:input-text type="text" id="visualizacao_palestrante_instituicao" name="instituicao" label="Instuição de formação" placeholder="Instituição de formação do palestrante"></t:input-text>
                            <img src="img/speaker.png" alt="Palestrante" class="img-responsive center-block" width="168">
                        </div>
                    </div>
                </div>
                </form>
            </div> <!-- ./modal-body -->
            <div class="modal-footer">
                <div class="row">
                    <div class="col-md-12">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                    </div>
                </div>
            </div>
        </div> <!-- ./modal-content -->
    </div> <!-- ./modal-dialog -->
</div> <!-- ./modal -->