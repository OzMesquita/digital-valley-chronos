<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.ufc.russas.n2s.chronos.util.Constantes"%>
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
<title>Chronos - Sistema de Gerenciamento de Atividades</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/design.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/timeline.css" />
<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"
	rel="stylesheet">
<style>
ul {
	display: block;
	list-style-type: disc;
	margin-left: 0;
	margin-right: 0;
	padding-left: 40px;
}

.panel {
	border: 1px solid #ddd;
	background-color: #fcfcfc;
}

.panel .btn-group {
	margin: 15px 0 30px;
}

.panel .btn-group .btn {
	transition: background-color .3s ease;
}

.table-filter {
	background-color: #fff;
	border-bottom: 1px solid #eee;
}

.table-filter tbody tr:hover {
	cursor: pointer;
	background-color: #eee;
}

.table-filter tbody tr td {
	padding: 10px;
	vertical-align: middle;
	border-top-color: #eee;
}

.table-filter tbody tr.selected td {
	background-color: #eee;
}

.table-filter tr td:first-child {
	width: 38px;
}

.table-filter tr td:nth-child(2) {
	width: 35px;
}

.ckbox {
	position: relative;
}

.ckbox input[type="checkbox"] {
	opacity: 0;
}

.ckbox label {
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

.ckbox label:before {
	content: '';
	top: 1px;
	left: 0;
	width: 18px;
	height: 18px;
	display: block;
	position: absolute;
	border-radius: 2px;
	border: 1px solid #bbb;
	background-color: #fff;
}

.ckbox input[type="checkbox"]:checked+label:before {
	border-color: #2BBCDE;
	background-color: #2BBCDE;
}

.ckbox input[type="checkbox"]:checked+label:after {
	top: 3px;
	left: 3.5px;
	content: '\e013';
	color: #fff;
	font-size: 11px;
	font-family: 'Glyphicons Halflings';
	position: absolute;
}

.table-filter .star {
	color: #ccc;
	text-align: center;
	display: block;
}

.table-filter .star.star-checked {
	color: #F0AD4E;
}

.table-filter .star:hover {
	color: #ccc;
}

.table-filter .star.star-checked:hover {
	color: #F0AD4E;
}

.table-filter .media-photo {
	width: 35px;
}

.table-filter .media-body {
	display: block;
	/* Had to use this style to force the div to expand (wasn't necessary with my bootstrap version 3.3.6) */
}

.table-filter .media-meta {
	font-size: 11px;
	color: #999;
}

.table-filter .media .title {
	color: #2BBCDE;
	font-size: 14px;
	font-weight: bold;
	line-height: normal;
	margin: 0;
}

.table-filter .media .title span {
	font-size: .8em;
	margin-right: 20px;
}

.table-filter .media .title span.pagado {
	color: #5cb85c;
}

.table-filter .media .title span.pendiente {
	color: #f0ad4e;
}

.table-filter .media .title span.cancelado {
	color: #d9534f;
}

.table-filter .media .summary {
	font-size: 14px;
}
</style>
</head>
<body>
	<c:import url="elements/menu-superior.jsp" charEncoding="UTF-8"></c:import>
	<c:set var="permissoes"
		value="${sessionScope.usuarioChronos.permissoes}"></c:set>
	<c:set var="nome" scope="session"
		value="${sessionScope.usuarioChronos.nome}" />
	<div class="container-fluid">
		<div class="row row-offcanvas row-offcanvas-right">
			<c:import url="elements/menu-lateral-esquerdo.jsp"
				charEncoding="UTF-8"></c:import>
			<div class="col-sm-8">
				<nav class="breadcrumb"> <span class="breadcrumb-item">Você
					está em:</span> <a class="breadcrumb-item" href="/Chronos">Início</a> <c:if
					test="${atividade.pai!=null}">
					<a class="breadcrumb-item" href="${atividade.pai.codAtividade}">${atividade.pai.nome}</a>
					<a class="breadcrumb-item" href="${atividade.codAtividade}">${atividade.nome}</a>
				</c:if> <c:if test="${atividade.pai==null}">
					<a class="breadcrumb-item" href="/Chronos/atividades/${atividade.codAtividade}">${atividade.nome}</a>
				</c:if>
				<a class="breadcrumb-item active" href="/Chronos/atividades/listar/${atividade.codAtividade}">Lista de Participantes</a>
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
					<div class="container">
					<h1>Participantes da Atividade: <label>${atividade.nome}</label></h1>
							<table class="table table-striped custab">
								<thead>
									<tr>
										<th>Nome</th>
									</tr>
								</thead>
								<c:forEach var="participante" items="${participantes}">
									<tr>
										<td><label>${participante.nome}</label></td>
									</tr>
								</c:forEach>



							</table>
						</div>
					</div>		
				<br> <br>
			</div>
		</div>
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
	<script>
		$("#navEtapas").addClass(function(index, currentClass) {
			var addedClass;

			if (screen.width <= 575) {
				addedClass = "flex-column";
			}

			return addedClass;
		});
		$("#timeline").removeClass(function(index, currentClass) {
			var addedClass;

			if (screen.width <= 575) {
				addedClass = "timeline";
			}

			return addedClass;
		});
	</script>
	<script>
		$(document).ready(
				function() {

					$('.star').on('click', function() {
						$(this).toggleClass('star-checked');
					});

					$('.ckbox label').on('click', function() {
						$(this).parents('tr').toggleClass('selected');
					});

					$('.btn-filter').on(
							'click',
							function() {
								var $target = $(this).data('target');
								if ($target != 'all') {
									$('.table tr').css('display', 'none');
									$(
											'.table tr[data-status="' + $target
													+ '"]').fadeIn('slow');
								} else {
									$('.table tr').css('display', 'none')
											.fadeIn('slow');
								}
							});

				});
	</script>
</body>
</html>