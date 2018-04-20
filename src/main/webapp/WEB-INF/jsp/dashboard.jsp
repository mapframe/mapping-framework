<div class="container-fluid">    
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <h2 class="titulo-destaque">Olá, seja bem-vindo!</h2>
            <p class="text-justify">
                Este sistema foi projetado de modo há fornecer a  separação entre as camadas de 
                persistência e aplicação. Em uma aplicação onde essas  camadas são independentes,
                é possível trocar a forma com que os dados são persistidos, sem que isso influencie 
                nos detalhes de exibição dos dados. Tal arquitetura arquitetura de separação de 
                camadas possibilita o suporte a bancos de dados de paradigmas diferentes.
            </p>
            <div class="line"></div> 
        </div>
    </div>
</div>

<div class="container-fluid">    
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-primary">
                <div class="panel-heading">Evento</div>
                <div class="panel-body">
                    <p class="text-justify">Aqui você pode cadastrar um novo evento e gerenciá-los, bem como visualizar suas palestras e demais informações.</p>
                    <div class="botoes">
                        <a href="eventoServlet?action=insert" class="btn btn-primary">Cadastrar</a>
                        <a href="eventoServlet?action=list" class="btn btn-primary">Gerenciar</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>