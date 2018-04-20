<%-- 
    Document   : input-date
    Created on : Nov 17, 2017, 7:29:33 PM
    Author     : rhau
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="tipo" required="true"%>
<%@attribute name="id" required="true"%>
<%@attribute name="name" required="true"%>
<%@attribute name="label" required="true"%>
<%@attribute name="placeholder" required="true"%>
<%@attribute name="value" required="false"%>

<%-- any content can be specified here e.g.: --%>
<div class="form-group">
    <label for="${id}">${label}</label>
    <div class="input-group date ${tipo}pickercustom">
        <input type='text' id="${id}" name="${name}" placeholder="${placeholder}" class="form-control ${tipo}input" value="${value}" required />
        <span class="input-group-addon">
            <span class="glyphicon glyphicon-calendar"></span>
        </span>
    </div>
</div>