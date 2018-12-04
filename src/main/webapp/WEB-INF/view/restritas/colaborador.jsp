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
	<c:import url="../elements/menu-superior.jsp" charEncoding="UTF-8"></c:import>
	<div class="container-fluid">
		<div class="row row-offcanvas row-offcanvas-right">
			<c:import url="../elements/menu-lateral-esquerdo.jsp"
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
					colaborador</li>
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
					<h1 class="text-capitalize">${atividade.nome}</h1>
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
				<br>
				<c:set var="pagina"
					value="${(((not empty param.pag) and (param.pag >= 1)) ? param.pag : 1)}"></c:set>

				<!--######################-->
				<!-- EXIBE COLABORADORESL -->
				<!--######################-->
				<table class="table table-striped custab">
					<tr>
						<td><b>Nome</b></td>
						<td><b>Função</b></td>
						<td></td>
						<td></td>
					</tr>
					<c:forEach var="colaborador" begin="${((pagina - 1) * 5)}"
						end="${((pagina - 1) * 5) + 4}" items="${colaborador}">
						<tr>
							<td>${colaborador.nome}</td>
							<td>${colaborador.funcao}</td>
							<!--######################-->
							<!-- EDITAR COLABORADOR -->
							<!--######################-->
							<td class="text-center">
								<form method="POST"
									action="/Chronos/editarColaborador/${atividade.codAtividade}&${colaborador.codColaborador}/editar"
									accept-charset="UTF-8" enctype="multipart/form-data"
									id="needs-validation" novalidate>
									<input type="button" class="btn btn-primary" value="Editar"
										data-toggle="modal" data-target="#editarColaborador">
									<!-- Modal -->
									<div class="modal fade" id="editarColaborador" tabindex="-1"
										role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="modalLabel">Editar
														Colaborador</h5>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<div class="modal-body">
													<label for="nomeInput">Nome</label>
													<div id="sandbox-container">
														<div class=" " style="padding-left: 0px;" id="datepicker">
															<input name="nome" class="form-control" id="nomeInput"
																aria-describedby="nomeHelp"
																placeholder="Digite o nome do colaborador" required=""
																type="text" value="${colaborador.nome}">
														</div>
													</div>
													<br> <label for="funcaoInput">Função</label>
													<div id="sandbox-container">
														<div class="" style="padding-left: 0px;" id="datepicker">
															<input name="funcao" class="form-control"
																id="funcaoInput" aria-describedby="nomeHelp"
																placeholder="Digite o nome do colaborador" required=""
																type="text" value="${colaborador.funcao}">
														</div>
													</div>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary btn-sm"
														data-dismiss="modal">Cancelar</button>
													<button type="submit" class="btn btn-primary btn-sm">Confirmar</button>
												</div>
											</div>
										</div>
									</div>
								</form>
							</td>
							<td class="text-center">
								<form method="POST"
									action="/Chronos/editarColaborador/${atividade.codAtividade}&${colaborador.codColaborador}/remover"
									accept-charset="UTF-8"
									id="needs-validation" novalidate>
									<input class="btn btn-danger" value="Remover" type="submit">
								</form>
							</td>
						</tr>
					</c:forEach>
				</table>

				<br>
				<!--######################-->
				<!-- ADICIONAR COLABORADOR -->
				<!--######################-->


				<li style="list-style: none; text-align: center;">
					<form method="POST"
						action="/Chronos/cadastrarColaborador/cadastraColaborador/${atividade.codAtividade}"
						accept-charset="UTF-8" enctype="multipart/form-data"
						id="needs-validation" novalidate>


						<input type="button" class="btn btn-circle"
							value="Cadastrar Colaborador" data-toggle="modal"
							data-target="#confirmarColaborador" />



						<!-- Modal -->
						<div class="modal fade" id="confirmarColaborador" tabindex="-1"
							role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="modalLabel">Confirmar
											cadastro do Colaborador</h5>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">
										<label for="nomeInput">Nome</label>
										<div id="sandbox-container">
											<div class=" " style="padding-left: 0px;" id="datepicker">
												<input name="nome" class="form-control" id="nomeInput"
													aria-describedby="nomeHelp"
													placeholder="Digite o nome do colaborador" required=""
													type="text">
											</div>
										</div>
										<br> <label for="funcaoInput">Função</label>
										<div id="sandbox-container">
											<div class="" style="padding-left: 0px;" id="datepicker">
												<input name="funcao" class="form-control" id="funcaoInput"
													aria-describedby="nomeHelp"
													placeholder="Digite a função do colaborador" required=""
													type="text">
											</div>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary btn-sm"
											data-dismiss="modal">Cancelar</button>
										<button type="submit" class="btn btn-primary btn-sm">Adicionar</button>
									</div>
								</div>
							</div>
						</div>
					</form>

				</li> <br /> <br>

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
</body>
</html>