<%@page import="br.ufc.russas.n2s.chronos.model.EnumTipoPagamento"%>
<%@page import="br.ufc.russas.n2s.chronos.model.EnumTipoAtividade"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML">
<html>
<head>
<meta charset="utf-8" />
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
</head>
<body>
	<c:import url="../elements/menu-superior.jsp" charEncoding="UTF-8"></c:import>
	<div class="container-fluid">
		<div class="row row-offcanvas row-offcanvas-right">
			<c:import url="../elements/menu-lateral-esquerdo.jsp"
				charEncoding="UTF-8"></c:import>
			<div class="col-sm-8">
				<nav aria-label="breadcrumb" role="navigation">
					<ol class="breadcrumb">
						<li class="breadcrumb-item">Você está em:</li>
						<li class="breadcrumb-item" aria-current="page"><a
							href="/Chronos">Início</a></li>
						<li class="breadcrumb-item" aria-current="page"><a
							href="/Chronos/atividades/${atividade.codAtividade}">${atividade.nome}</a></li>
						<li class="breadcrumb-item active" aria-current="page"><a
							href="/Chronos/editarAtividade/${atividade.codAtividade}">Editar
								Atividade</a></li>
						<li class="breadcrumb-item" aria-current="page"><a
							href="/Chronos/apoiadores/${atividade.codAtividade}">Cadastrar
								Apoiadores</a></li>
						<c:if test="${empty pai}">
							<li class="breadcrumb-item active" aria-current="page">Cadastrar
								Apoiador</li>
						</c:if>

						<c:if test="${not empty pai}">
							<li class="breadcrumb-item" aria-current="page"><a
								href="/Chronos/atividades/${pai.codAtividade}">${pai.nome}</a></li>
							<li class="breadcrumb-item active" aria-current="page"><a
								href="/Chronos/editarAtividade/${pai.codAtividade}">Editar
									Atividade</a></li>
							<li class="breadcrumb-item active" aria-current="page">Cadastrar
								apoio</li>
						</c:if>
					</ol>
				</nav>

				<c:if test="${not empty mensagem}">
					<div class="alert alert-${status} alert-dismissible fade show"
						role="alert">
						${mensagem}
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
				</c:if>
				<h1>Cadastrar Apoio</h1>
				<p>Atenção: Os campos abaixo (*) são de preenchimento
					obrigatório</p>
				<br>
				<div class="form-group">
					<form method="POST"
						action="cadastraApoio/${atividade.codAtividade}"
						accept-charset="UTF-8" enctype="multipart/form-data"
						id="needs-validation" novalidate>
						<label for="tituloInput">Nome da Instituição*</label> <input
							type="text" name="nomeInstituicao" class="form-control"
							id="nomeInstituicaoInput" aria-describedby="nomeHelp"
							placeholder="Digite o nome da instituição apoiadora" required>
						<small id="nomeHelp" class="form-text text-muted">Exemplo:
							Laboratório Interdisciplinar de Computação e Engenharia de
							Software</small>
						<div class="invalid-feedback"></div>

						<br> <label for="siteInstituicaoInput">Site da
							Instituicao*</label>
						<textarea class="form-control" name="siteInstituicao"
							id="tipoDeApoioInput" placeholder="Digite o tipo do apoio"
							required></textarea>

						<br> <label for="tipoApoioInput">Tipo de Apoio*</label>
						<textarea class="form-control" name="tipoApoio"
							id="tipoDeApoioInput" placeholder="Digite o tipo do apoio"
							required></textarea>

						<div class="invalid-feedback"></div>
						<br> <label for="valorApoioInput">Valor de Apoio*</label>
						<textarea class="form-control" name="valorApoio"
							id="descricaoInput" placeholder="Digite o valor do apoio"
							required></textarea>
						<div class="invalid-feedback"></div>
						<br> <label for="periodoInput">Data de Pagamento</label>
						<div id="sandbox-container">
							<div class="input-daterange input-group col-lg-3 align-left"
								style="padding-left: 0px;" id="datepicker">
								<input type="date" class="form-control text-left"
									placeholder="Digite a data de início desta etapa"
									name="dataPagamento" id="dataPagamentoInput" required />
							</div>
							<small id="periodoHelp" class="form-text text-muted">Selecione
								uma data</small>
						</div>
						<div class="form-group">
							<label for="image">Cadastrar logo: </label> <input
								type="file" name="logo" id="logo">
						</div>
						<br> <a href="/Chronos/" type="button" id="enviar"
							class="btn btn-secondary"> Cancelar </a> <input type="button"
							class="btn btn-primary" value="Salvar e Continuar"
							data-toggle="modal" data-target="#confirmarApoio">
						<!-- Modal -->
						<div class="modal fade" id="confirmarApoio" tabindex="-1"
							role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="modalLabel">Confirmar
											cadastro do apoio</h5>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">
										<p>Você deseja confirmar o cadastro do apoio?</p>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary btn-sm"
											data-dismiss="modal">Cancelar</button>
										<button type="submit" class="btn btn-primary btn-sm">Confirmar</button>
									</div>
								</div>
							</div>
						</div>
						<hr />
					</form>
				</div>
			</div>
		</div>
	</div>
	<c:import url="../elements/rodape.jsp" charEncoding="UTF-8"></c:import>
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
	<script src="${pageContext.request.contextPath}/resources/js/script.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/scriptCadastrarSelecao.js"></script>
</body>
</html>