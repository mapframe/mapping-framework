<%-- 
    Document   : text-area
    Created on : Nov 17, 2017, 7:33:58 PM
    Author     : rhau
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="id" required="true"%>
<%@attribute name="name" required="true"%>
<%@attribute name="label" required="true"%>
<%@attribute name="placeholder" required="true"%>
<%@attribute name="value" required="false"%>

<%-- any content can be specified here e.g.: --%>
<div class="form-group">
    <label for="${id}">${label}</label>
    <textarea id="${id}" name="${name}" class="form-control" placeholder="${placeholder}">${value}</textarea>
</div>  