<%@page import="br.ufc.russas.n2s.chronos.model.Atividade"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="n2s">
<link rel="icon" href="favicon.ico">
<title>Chronos - Sistema de Gerenciamento de atividades</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/design.css" />
</head>
<body>
	<c:import url="elements/menu-superior.jsp" charEncoding="UTF-8"></c:import>
	<div class="container-fluid">
		<div class="row row-offcanvas row-offcanvas-right">
			<c:import url="elements/menu-lateral-esquerdo-inicio.jsp"
				charEncoding="UTF-8"></c:import>
			<c:set var="titulo" value="Minhas Atividades"></c:set>
			<c:set var="titulo"
				value="${fn:replace(titulo, 'atividades', 'Atividades')}"></c:set>
			<div class="col-sm-8">
				<nav class="breadcrumb"> <span class="breadcrumb-item">Você
					está em:</span> <a
					class="breadcrumb-item ${titulo eq 'Minhas Atividades' ? 'active': ''}"
					href="/Chronos/atividades/minhas-atividades">Minhas Atividades</a></nav>
				<c:set var="mensagem" value="${sessionScope.mensagem}"></c:set>
				<c:if test="${not empty mensagem}">
					<div class="alert alert-${status} alert-dismissible fade show"
						role="alert">
						${mensagem}
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<c:set scope="session" var="mensagem" value=""></c:set>
					<c:set scope="session" var="status" value=""></c:set>
				</c:if>
				<div class="row col-sm-12">
					<h1 class="text-capitalize">${titulo}</h1>
					<c:if test="${not empty estado}">
						<div class="dropdown right"
							style="right: -13px; position: absolute;">
							<button class="btn dropdown-toggle btn-sm btn-icon filtro_tela"
								type="button" id="dropdownMenuButton" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false">
								<i class="material-icons">filter_list</i> <span>Filtrar</span>
							</button>
							<div class="dropdown-menu dropdown-menu-right"
								aria-labelledby="dropdownMenuButton">
								<a class="dropdown-item" href="/Chronos">Todas as atividade</a>
								<a class="dropdown-item" href="/Chronos/estado/aberta">Atividades
									abertas</a> <a class="dropdown-item"
									href="/Chronos/estado/andamento">Atividades em andamento</a> <a
									class="dropdown-item" href="/Chronos/estado/finalizada">Atividades
									finalizadas</a>
							</div>
						</div>
					</c:if>
				</div>
				<c:if test="${empty atividades}">
					<c:if test="${(fn:contains(permissoes, 'PARTICIPANTE'))}">
						<p class="text-muted">Você não está inscrito em nenhuma atividade!</p>
					</c:if>
					<c:if test="${(fn:contains(permissoes, 'ADMINISTRADOR'))}">
						<p class="text-muted">Nenhuma atividade cadastrada!</p>
					</c:if>
				</c:if>
				<c:set var="pagina"
					value="${(((not empty param.pag) and (param.pag >= 1)) ? param.pag : 1)}"></c:set>
				<c:forEach var="atividade" begin="${((pagina - 1) * 5)}"
					end="${((pagina - 1) * 5) + 4}" items="${atividades}">
					<div class="card">
						<div class="card-body">
							<div class="row" style="padding-left: 13px;">
								<h2 class="card-title text-uppercase font-weight-bold">
									${atividade.nome} <small>(${atividade.sigla})</small>
								</h2>
							</div>
							<h3 class="card-subtitle mb-2 text-muted">
								${fn:replace(atividade.tipoAtividade, '_', ' ')} -
								<fmt:parseDate value="${atividade.realizacao[0].horaInicio}"
									pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
								<fmt:formatDate pattern="EEEE',' dd 'de' MMMM 'de' yyyy HH:mm"
									value="${ parsedDateTime }" />
							</h3>
							<p class="card-text text-justify">
								${fn:substring(atividade.descricao, 0, 400)}
								<c:if test="${fn:length(atividade.descricao) > 400}">
	                                    [...]
	                                </c:if>
							</p>
							<c:set var="nomeUrl" value="${atividade.nome}" />
							<a href="/Chronos/atividades/${atividade.codAtividade}"
								class="card-link">Mais informações</a>
						</div>
					</div>
				</c:forEach>
				<br />
				<nav aria-label=""> 
				<ul class="pagination justify-content-center">
					<li class="page-item "${(pagina <= 1 ? "disabled" : "")}"><a
						class="page-link" href="/Chronos/${categoria}?pag=${pagina - 1}"
						tabindex="-1">Anterior</a></li>
					<c:forEach var="i" begin="1"
						end="${(fn:length(atividades)/5) + (fn:length(atividades)%5 == 0 ? 0 : 1)}">
						<li class="page-item "${(pagina == i ? "active": "")}"><a
							class="page-link" href="/Chronos/${categoria}?pag=${i}">${i}</a></li>
					</c:forEach>
					<li class="page-item  "${(pagina >= ((fn:length(atividades))/5) ? "disabled" : "")}">
						<a class="page-link"
						href="/Chronos/${categoria}?pag=${pagina + 1}">Próximo</a>
					</li>
				</ul>
				</nav>
			</div>
		</div>
	</div>
	<!--  to mexendo nisso -->
	<style rel="stylesheet" type="text/css">
input[type=text], input[type=password] {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

button1 {
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
}

button:hover {
	opacity: 0.8;
}

.cancelbtn {
	width: auto;
	padding: 10px 18px;
	background-color: #f44336;
}

.imgcontainer {
	text-align: center;
	margin: 24px 0 12px 0;
}

img.avatar {
	width: 40%;
	border-radius: 50%;
}

.container {
	padding: 16px;
}

span.psw {
	float: right;
	padding-top: 16px;
}

@media screen and (max-width: 300px) {
	span.psw {
		display: block;
		float: none;
	}
	.cancelbtn {
		width: 100%;
	}
}
</style>
	<c:import url="elements/rodape.jsp" charEncoding="UTF-8"></c:import>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
		integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
		integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
		crossorigin="anonymous"></script>
</body>
</html>