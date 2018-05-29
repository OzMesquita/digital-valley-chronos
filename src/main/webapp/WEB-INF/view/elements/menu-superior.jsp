<%-- 
    Document   : menu-superior
    Created on : 06/10/2017, 10:29:03
    Author     : usuario
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.ArrayList"%>
<%@page import="br.ufc.russas.n2s.chronos.beans.UsuarioBeans"%>
<%@page import="br.ufc.russas.n2s.chronos.util.Constantes"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="<%=Constantes.getAppUrl() %>">
        <img src="${pageContext.request.contextPath}/resources/img/chronos.png" width="100" height="30" class="d-inline-block align-top" alt="Logo do módulo Chronos">
        <!--Chronos-->
    </a>  
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <c:set var="permissoes" value="${sessionScope.usuarioChronos.permissoes}"></c:set>
            <a class="nav-item nav-link" href="<%=Constantes.getAppGuardiaoUrl()%>">Guardião</a>
            <a class="nav-item nav-link" href="<%=Constantes.getAppUrl() %>/minhasAtividades">Minhas Atividades</a>
            <c:if test="${fn:contains(permissoes, 'APOIO') or fn:contains(permissoes, 'ADMINISTRADOR')}">
            <div class="btn-group">
                <a href="" class="nav-item nav-link " data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Administração
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="<%=Constantes.getAppUrl() %>/cadastrarAtividades">Cadastrar Atividade</a>
                    
                    <c:if test="${fn:contains(permissoes, 'ADMINISTRADOR')}">
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="<%=Constantes.getAppUrl() %>/permissoes">Gerenciar permissões</a>
                    </c:if>
                </div>
            </div>
            </c:if>
            
        </div>
        
    </div>
    <a class="btn-sm btn-light text-right" href="<%=Constantes.getAppUrl() %>/sair">Sair</a>
</nav>         