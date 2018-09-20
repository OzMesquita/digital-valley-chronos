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
	<div class="container-fluid">
		<div class="row row-offcanvas row-offcanvas-right">
			<c:import url="elements/menu-lateral-esquerdo.jsp"
				charEncoding="UTF-8"></c:import>
			<div class="col-sm-8">
				<nav class="breadcrumb"> <span class="breadcrumb-item">Você
					está em:</span> <a class="breadcrumb-item" href="/Chronos">Início</a> <a
					class="breadcrumb-item active" href="${atividade.codAtividade}">${atividade.nome}</a>
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
				<!-- Mensagem de solicitando a divulgação da seleção -->
				<c:if
					test="${(fn:contains(permissoes, 'ADMINISTRADOR')) and (not atividade.divulgada)}">
					<!--  and (not atividade.divulgada)}"> -->
					<div class="jumbotron jumbotron-fluid"
						style="padding-top: 40px; padding-bottom: 30px;">
						<div class="container">
							<h1 style="font-size: 20px; font-weight: bold;">Divulgue sua
								atividade!</h1>
							<br>
							<p style="font-size: 15px;">
								Para permitir que os outros usuários tenham acesso a sua
								atividade, você precisa divulga-lá. Você deseja divulgar a
								atividade? <input type="button" style="font-size: 15px;"
									class="btn btn-link" value="Divulgar a atividade"
									data-toggle="modal" data-target="#divulgar">
							</p>
						</div>
					</div>
					<!-- Modal -->
					<div class="modal fade" id="divulgar" tabindex="-1" role="dialog"
						aria-labelledby="modalLabel" aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="modalLabel">Divulgar atividade</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									<p>Após divulgar sua atividade todos os outros usuários
										poderão visualizar e participar dela.</p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary btn-sm"
										data-dismiss="modal">Cancelar</button>
									<a class="btn btn-sm btn-primary"
										href="/Chronos/editarAtividade/divulga/${atividade.codAtividade}">
										Divulgar a atividade</a>
								</div>
							</div>
						</div>
					</div>
				</c:if>
				<div class="column" style="padding-left: 15px;">
					<h1 class="text-uppercase" style="font-size: 20px;">${atividade.nome}
						(${atividade.sigla})</h1>
					<c:if test="${(fn:contains(permissoes, 'ADMINISTRADOR'))}">
						<a href="/Chronos/editarAtividade/${atividade.codAtividade}"
							class="btn btn-primary btn-sm"
							style="height: 33px; margin-left: 30px; margin-top: 5px;">
							Editar Atividade </a>
						<a class="btn btn-danger btn-sm"
							style="height: 33px; margin-left: 30px; margin-top: 5px;"
							data-toggle="modal" data-target="#remover"><font color="#FFFFFF"> Remover Atividade </font> 
						</a>
						<!-- Modal -->
						<div class="modal fade" id="remover" tabindex="-1" role="dialog"
							aria-labelledby="modalLabel" aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="modalLabel">Remover atividade</h5>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">
										<p>Remover esta atividade implica em remover todas as suas
											subatividades cadastradas, e após concluida esta ação não
											podera ser desfeita!</p>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary btn-sm"
											data-dismiss="modal">Cancelar</button>
										<a class="btn btn-danger btn-sm"
											href="/Chronos/editarAtividade/remover/${atividade.codAtividade}">
											Remover atividade</a>
									</div>
								</div>
							</div>
						</div>
					</c:if>
				</div>
				<br>
				<p class="text-justify">${atividade.descricao}</p>
				<p class="text-justify">
				<hr>
				<c:if test="${not empty atividade.pai}">
					<b>EVENTO: </b> ${pai.nome}<br />
					<br />
				</c:if>
				<c:if test="${not empty atividade.tipoAtividade}">
					<b>TIPO DA ATIVIDADE: </b> ${atividade.tipoAtividade}<br />
					<br />
				</c:if>
				<c:if test="${not empty atividade.local}">
					<b>LOCAL DA ATIVIDADE: </b> ${atividade.local}<br />
					<br />
				</c:if>
				<c:if test="${not empty atividade.preRequisitos}">
					<b>PRÉ REQUISITOS: </b> ${atividade.preRequisitos}<br />
					<br />
				</c:if>
				<c:if
					test="${atividade.totalVagas == 0 and atividade.totalVagasComunidade == 0}">
					<b>NÚMERO DE VAGAS: </b> Indeterminadas<br />
					<br />
				</c:if>
				<c:if
					test="${not (atividade.totalVagas == 0 and atividade.totalVagasComunidade == 0)}">
					<b>NÚMERO DE VAGAS: </b>
					<ul>
						<li>TOTAL: <b>${atividade.totalVagas}</b></li>
						<li>VAGAS PARA COMUNIDADE: <b>${atividade.totalVagasComunidade}</b></li>
					</ul>
				</c:if>
				<c:if test="${not empty atividade.tipoPagamento}">
					<b>TIPO DE PAGAMENTO: </b> ${atividade.tipoPagamento}<br />
					<br />
				</c:if>
				<c:if test="${empty atividade.tipoPagamento}">
					<b>TIPO DE PAGAMENTO: </b> Gratuito<br />
					<br />
				</c:if>
				<c:if test="${not empty atividade.local}">
					<b>LOCAL DO PAGAMENTO: </b> ${atividade.local}<br />
					<br />
				</c:if>
				<c:if test="${empty atividade.local}">
					<b>LOCAL DO PAGAMENTO: </b> Indeterminado<br />
					<br />
				</c:if>
				<hr />
				</p>
				<br />
				<c:if test="${empty atividade.subAtividade}">
					<b style="text-align: center">Esta atividade não possui
						subatividades.</b>
					<br>
				</c:if>

				<c:if test="${not empty atividade.subAtividade}">
					<!-- lista de subatividades -->
					<div class="container">
						<div class="row col-md-12 col-md-offset-2 custyle">
							<i class="glyphicon glyphicon-eye-open"></i> <a href="#">Visualizar
								cronograma completo</a>
							<table class="table table-striped custab">
								<thead>
									<tr>
										<th>Atividade</th>
										<th>Responsável</th>
										<th>Local</th>
										<th>Data/Horário</th>
										<th>Vagas</th>
										<th class="text-center">Editar</th>
										<th class="text-center">Remover</th>
									</tr>
								</thead>	
								<%-- Verifica se o usuario é administrador e então exibe a opção de editar atividade --%> 														
								<c:if test="${(fn:contains(permissoes, 'ADMINISTRADOR'))}">
									<c:forEach var="atividades" items="${atividade.subAtividade}">
										<tr>
											<td>${atividades.nome}</td>
											<td>${atividades.responsavel.nome}</td>
											<td>${atividades.local}</td>
											<td>22/05 - 08:00</td>
											<td>20/20</td>
											<td class="text-center"><a href="/Chronos/editarAtividade/${atividades.codAtividade}"
												class="btn btn-primary btn-sm">
													Editar </a></td>
											<td class="text-center"><a class="btn btn-danger btn-sm"
											href="/Chronos/editarAtividade/remover/${atividade.codAtividade}">
											Remover</a></td>																
										</tr>
									</c:forEach>								
								</c:if>							
											
								
							</table>
						</div>
					</div>
				</c:if>
				<br> <br>
				<div style="margin-right: 30px;">
					<h2>Apoiadores</h2>
				</div>
				<div class="container">
					<%
						for (int i = 0; i < 3; i++) {
					%>
					<div
						style="width: 10rem; position: relative; float: left; margin-right: 10px;">
						<div class="card">
							<center>
								<img class="card-img-top"
									src="http://lookperfeito.com/wp-content/uploads/2016/10/LABFANTASMA.jpg"
									style="width: 140px; height: 80px; margin-top: 5px;"
									alt="Card image cap">
							</center>
						</div>
						<div class="card-body"
							style="padding-top: 5px; padding-bottom: 0px;">
							<center>
								<h6 class="card-title">LAB FANTASMA</h6>
							</center>
						</div>
					</div>
					<%
						}
					%>
				</div>
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
