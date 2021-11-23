<%@page import="model.User"%>
<%@page session="true" %>



<jsp:include page="components/head.jsp">
    <jsp:param  name="nombre" value="Index"/>
</jsp:include>


<jsp:include page="components/sidebar.jsp">
    <jsp:param  name="usuario" value=""/>
</jsp:include>


<%@include  file="components/contentheader.jsp"  %>


<div class="mb-auto h-100">

    INDEX

</div>


<%@include  file="components/contentfooter.jsp"  %>

<%@include  file="components/foot.jsp"  %>