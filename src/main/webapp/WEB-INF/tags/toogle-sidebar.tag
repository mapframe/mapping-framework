<%-- 
    Document   : toogle-sidebar
    Created on : Nov 7, 2017, 8:48:45 PM
    Author     : rhau
--%>

<%@tag description="toogle sidebar" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="tituloSuperior" required="true"%>

<%-- any content can be specified here e.g.: --%>
<!-- TOOOGLE -->
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" id="sidebarCollapse" class="btn btn-info navbar-btn">
                <i class="glyphicon glyphicon-align-left"></i>
                <span class="sr-only">Toggle Sidebar</span>
            </button>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="#">${tituloSuperior}</a></li>
        </ul>
    </div>
</nav> 
<!-- ./TOOGLE -->