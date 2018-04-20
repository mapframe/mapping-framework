<%-- 
    Document   : index
    Created on : Nov 5, 2017, 4:25:35 PM
    Author     : rhau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="t" %>  

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="author" content="">
    <title>Eventos UFSM</title>
    <link rel="stylesheet" href="recursos/_bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="recursos/_font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="recursos/_datetimepicker/css/datetimepicker.css">
    <link rel="stylesheet" href="recursos/_dataTables/datatables.min.css"/>
    <link rel="stylesheet" href="css/menu-dashboard.css">
    <link rel="stylesheet" href="css/eventos.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="recursos/_bootstrap/js/bootstrap.min.js"></script>
    <script src="recursos/_dataTables/datatables.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script>var $j = jQuery.noConflict();</script>
    <script src="recursos/_datetimepicker/js/moment-with-locales.min.js"></script>
    <script src="recursos/_datetimepicker/js/datetimepicker.js"></script>
    <script src="recursos/_jquery-mask/src/jquery.mask.js"></script>
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<!-- SIDEBAR -->
    <jsp:include page="WEB-INF/jsp/sidebar.jsp" />
<!-- /SIDEBAR -->

<!-- CONTEUDO -->
<div id="content">
    <t:toogle-sidebar tituloSuperior="Gerenciador de eventos da UFSM"></t:toogle-sidebar>

    <c:if test="${not empty page}">
        <jsp:include page="WEB-INF/jsp/${page}" flush="true" />                            
    </c:if>
    
    <c:if test="${empty page}"> 
        <jsp:include page="WEB-INF/jsp/dashboard.jsp" />
    </c:if>
</div>
<!-- /CONTEUDO -->    

    <script>
        $(function () {
            $('#sidebarCollapse').on('click', function () {
                $('#sidebar').toggleClass('active');
            });
        });
    </script>

</body>
</html>