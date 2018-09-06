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
			<c:import url="elements/menu-lateral-esquerdo.jsp"
				charEncoding="UTF-8"></c:import>
			<c:set var="titulo" value="${fn:replace(categoria, '_', ' ')}"></c:set>
			<c:set var="titulo"
				value="${fn:replace(titulo, 'atividades', 'Atividades')}"></c:set>
			<div class="col-sm-8">
				<nav class="breadcrumb"> <span class="breadcrumb-item">Você
					está em:</span>
				<li class="breadcrumb-item" aria-current="page"><a
					href="/Chronos">Início</a></li>
				<li class="breadcrumb-item" aria-current="page"><a
					href="/Chronos/atividades/${atividade.codAtividade}">${atividade.nome}</a></li>
				<li class="breadcrumb-item active" aria-current="page"><a
					href="/Chronos/editarAtividade/${atividade.codAtividade}">Editar
						Atividade</a></li>
				<li class="breadcrumb-item active" aria-current="page">Cadastrar
					Apoiadores</li>
				</nav>
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
					<h1 class="text-capitalize">Apoiadores</h1>
					<br>
				</div>
				<c:if test="${empty atividade.apoiadores}">
					<p class="text-muted">Nenhum apoio cadastrado!</p>
				</c:if>
				<c:if test="${not empty atividade.apoiadores}">
					<c:forEach var="apoiador" items="${atividade.apoiadores}">
						<div class="card">
							<div class="card-body">
								<div class="row" style="padding-left: 13px;">
									<h2 class="card-title text-uppercase font-weight-bold">
										${apoiador.nomeInstituicao}</h2>
								</div>
								<p class="card-text text-justify">${apoiador.tipoApoio}</p>
								</p>
								<fmt:parseDate value="${apoiador.dataPagamento}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
								<fmt:formatDate pattern="EEEE',' dd 'de' MMMM 'de' yyyy" value="${ parsedDateTime }" var="dateTime" />
								<h3 class="card-subtitle mb-2 text-muted">
									${dateTime}</h3>
								<p class="card-text text-justify">
									${apoiador.siteInstituicao}</p>
							</div>
						</div>
					</c:forEach>
					<br />
				</c:if>
				<a href="/Chronos/cadastrarApoio/${atividade.codAtividade}"	class="timeline-badge primary">
					<li style="list-style: none; text-align: center;">
						<button class="btn btn-circle">
							 <i class="material-icons" title="Adicionar novo apoio">add</i>
						</button>
					</li>
				</a>
				<c:set var="pagina"
					value="${(((not empty param.pag) and (param.pag >= 1)) ? param.pag : 1)}"></c:set>
				<br />
				<br>
			</div>
		</div>
	</div>
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