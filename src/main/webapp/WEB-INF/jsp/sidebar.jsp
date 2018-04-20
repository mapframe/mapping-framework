<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav id="sidebar">
    <div class="sidebar-header">
        <a href="index.jsp">
            <img src="img/brasao-logo-ufsm.png" class="img-logo" alt="UFSM" />
            <h3>EVENTOS</h3>
            <strong>UFSM</strong>
        </a>
    </div>
    <ul class="list-unstyled components">
        <li <c:if test="${empty menuAtivo}">class="active"</c:if> id="menu_index">
            <a href="index.jsp">
                <i class="glyphicon glyphicon-home"></i> Home
            </a>
        </li>
        <li id="menu_eventos">
            <a href="eventoServlet?action=list">
                <i class="glyphicon glyphicon-briefcase"></i> Eventos
            </a>
        </li>
        <li id="menu_palestras">
            <a href="palestraServlet">
                <i class="glyphicon glyphicon-duplicate"></i> Palestras
            </a>
        </li>
    </ul>
    <ul class="list-unstyled CTAs" >
        <li>
            <a href="https://github.com/isasakis" class="article" title="GitHub @isasakis" target="_blank">
                <div class="text-center">
                    <span class="fa-stack fa-lg">
                        <i class="fa fa-github fa-stack-2x fa-icon"></i>
                    </span> @isasakis
                </div>
            </a>
        </li>
        <li>
            <a href="https://github.com/rwfazul" class="article" title="GitHub @rwfazul" target="_blank">
                <div class="text-center">                    
                    <span class="fa-stack fa-lg">
                        <i class="fa fa-github fa-stack-2x fa-icon"></i>
                    </span> @rwfazul
                </div>
            </a>
        </li>
    </ul>
</nav>

<c:if test="${not empty menuAtivo}">
    <script>
        $(function() {
            $("#sidebar").find(".active").removeClass("active");
            $("#sidebar li[id='${menuAtivo}']").addClass("active");
        });
    </script>
    <c:remove var="menuAtivo" />
</c:if> 