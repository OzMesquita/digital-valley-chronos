<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.ufc.russas.n2s.chronos.util.Constantes"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>



<!-- calendar -->
<style>
.grid-header {
	background-color: #aaa;
	background-size: 34px;
	border-bottom: 1px solid #ddd;
	overflow: hidden;
	box-shadow: 0 0 3px 0;
	position: fixed;
	top: 43px;
	display: block;
	z-index: 1035;
}

.grid-header-labels {
	padding-left: 60px;
}

.grid-header-label {
	width: 120px;
	float: left;
	padding: 10px 5px;
	height: 40px;
	text-align: center;
	background: #009d97;
	color: #fff;
	cursor: pointer;
	position: relative;
}

.grid-header-label-divider {
	border-right: 1px solid #cbcbcb;
	position: static;
	right: 0;
	bottom: 0;
	height: 22px;
}

.grid-header-Clock {
	text-align: left;
	background-color: #aaa;
	width: 60px;
	height: 60px;
	position: fixed;
	left: 0;
	top: 0;
	font-size: 40px;
}

.grid-body {
	position: absolute;
	top: 40px !important;
	background: #f7f7f7;
}

.ui-block {
	position: absolute;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
}

.grid-times-holder {
	position: absolute;
	overflow: hidden;
	width: 60px;
	top: 0;
	display: block;
	z-index: 200;
	border-right: 1px solid #ddd;
	background: #eee;
	text-align: center;
}

.grid-date-holder {
	position: absolute;
	overflow: hidden;
	left: 60px;
	top: 0px;
	display: block;
	z-index: 200;
	border-right: 1px solid #ddd;
	background: #eee;
	text-align: center;
}

.day-label {
	height: 24px;
	line-height: 25px;
	border-bottom: 1px solid #a6a8ab;
	color: #57585a;
	background-color: #CBCBCB;
}

.grid-time-label {
	height: 50px;
	top: 10px;
	line-height: 20px;
	border-bottom: 1px solid #ddd;
	padding-left: 0;
}

.grid-times {
	
}

.grid-body-labels {
	padding-left: 0px;
}

.grid-body-cell {
	width: 110px;
	float: left;
	padding: 10px 5px;
	height: 50px;
	line-height: 49px;
	border-bottom: 1px solid #ddd;
	padding-left: 0;
	text-align: center;
	color: #fff;
	background-color: #fff;
	cursor: pointer;
	position: relative;
	border: 1px solid #ddd;
}

.grid-sessions {
	position: relative;
	min-width: 100%;
}

.grid-sessions-day {
	position: relative;
	height: 1525px;
	background:
		url(https://farm9.staticflickr.com/8617/16121120332_0c7b48b7c4_o.png)
		60px 25px;
	min-width: 100%;
}

.grid-session-cell {
	position: absolute;
	width: 118px;
	overflow: hidden;
	border: 1px solid #bbb;
	font-size: 12px;
	cursor: pointer;
	background: rgba(0, 157, 151, .85);
	color: #fff;
	font-family: sans-serif;
}

.grid-session-icon {
	position: absolute;
	right: 10px;
	bottom: 10px;
	font-size: 24px;
}

.grid-session-title {
	padding: 9px;
	padding-right: 10px;
	font-weight: bold;
}

.grid-session-time {
	padding: 9px;
}
</style>


<link href="//code.ionicframework.com/nightly/css/ionic.css"
	rel="stylesheet">
<script src="//code.ionicframework.com/nightly/js/ionic.bundle.js"></script>
<!-- calendar -->

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
<style>
ul {
	display: block;
	list-style-type: disc;
	margin-left: 0;
	margin-right: 0;
	padding-left: 40px;
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
				<!-- Mensagem de primeiro acesso após o cadastro da seleção 
                <c:if test="${(empty selecao.inscricao) and (fn:contains(permissoes, 'ORGANIZADOR') or fn:contains(permissoes, 'ADMINISTRADOR'))}">
                    <div class="jumbotron jumbotron-fluid" style="padding-top: 40px; padding-bottom: 30px; ">
                        <div class="container">
                            <h1 style="font-size: 20px; font-weight: bold;">Cadastre a primeira etapa da sua seleção!</h1><br>
                            <p style="font-size: 15px;">Para iniciar sua seleção é necessário o cadastro da etapa de inscrição. Você deseja cadastrar a etapa de inscrição? &nbsp;
                                <a href="/Chronos/cadastrarEtapa/${selecao.codSelecao}"> Cadastrar etapa de inscrição </a>
                            </p>
                        </div>
                    </div>
                </c:if>-->

				<!-- Mensagem de solicitando a divulgação da seleção -->
				<c:if test="${(fn:contains(permissoes, 'ADMINISTRADOR'))}">
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
										href="/Darwin/editarAtividade/divulga/${atividade.codAtividade}">
										Divulgar a atividade</a>
								</div>
							</div>
						</div>
					</div>
				</c:if>

				<div class="row" style="padding-left: 15px;">
					<h1 class="text-uppercase" style="font-size: 20px;">${atividade.nome}
						(${atividade.sigla})</h1>

				</div>
				<br>
				<p class="text-justify">${atividade.descricao}</p>
				<p class="text-justify">
				<hr>
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
				<!-- calendar -->
				
				<!-- calendar -->

<!-- 
                    <c:if test="${(not empty selecao.etapas) or (not empty selecao.inscricao)}">
                        <ul class="timeline">
                            <c:if test="${not empty selecao.inscricao}">
                            <c:set var="estadoInscricao" value="${selecao.inscricao.estado.estado}"></c:set>
                            <li class="${i%2 != 0? 'timeline-inverted': ''}">
                                <div class="timeline-badge ${estadoInscricao == 1 ? 'insert_invitation': estadoInscricao == 2 ? 'warning': estadoInscricao == 3  ? 'success': 'danger'}">
                                    <i class="material-icons">${estadoInscricao == 1 ? 'insert_invitation': estadoInscricao == 2 ? 'timelapse': estadoInscricao == 3  ? 'done_all': 'warning'}</i>
                                </div>
                                <div class="timeline-panel">
                                    <div class="timeline-heading">
                                        <h2 class="timeline-title text-uppercase">${selecao.inscricao.titulo}</h2>
                                        <p>
                                            <small class="text-muted">
                                                <i class="glyphicon glyphicon-time"></i>                                             
                                                <fmt:parseDate value="${selecao.inscricao.periodo.inicio}" pattern="yyyy-MM-dd" var="parseDataInicio" type="date" />
                                                <fmt:parseDate value="${selecao.inscricao.periodo.termino}" pattern="yyyy-MM-dd" var="parseDataTermino" type="date" />
                                                <fmt:formatDate value="${parseDataInicio}"  pattern="dd/MMMM/yyyy" var="dataInicio" type="date"/>
                                                <fmt:formatDate value="${parseDataTermino}"  pattern="dd/MMMM/yyyy" var="dataTermino" type="date"/>
                                                <b>${fn:replace(dataInicio, "/", " de ")}</b> 
                                                até 
                                                <b>${fn:replace(dataTermino, "/", " de ")}</b>
                                            </small>
                                        </p>
                                    </div>
                                    <div class="timeline-body">
                                        <p>${selecao.inscricao.descricao}</p>
                                        <br>
                                        <c:if test="${not empty selecao.inscricao.documentacaoExigida}">
                                            <b>DOCUMENTAÇÃO EXIGIDA: </b> 
                                            <ul>
                                                <c:forEach var="documento" items="${selecao.inscricao.documentacaoExigida}">
                                                    <li>${documento}</b></li>
                                                </c:forEach>
                                            </ul>
                                        </c:if>
                                        <hr>
                                        <c:if test="${(estadoInscricao == 2) and (not isResponsavel) and (not fn:contains(permissoes, 'ADMINISTRADOR')) and (not fn:contains(selecao.inscricao.avaliadores, sessionScope.usuarioDarwin))}">
                                            <a href="/Darwin/participarEtapa/inscricao/${selecao.inscricao.codEtapa}" class="btn btn-primary btn-sm" role="button" aria-pressed="true">Inscrever-se</a>
                                        </c:if>
                                        <c:if test="${(isResponsavel and (estadoInscricao == 1) and (selecao.estado eq 'ESPERA')) or (fn:contains(permissoes, 'ADMINISTRADOR'))}">
                                            <a href="/Darwin/editarEtapa/${selecao.codSelecao}/${selecao.inscricao.codEtapa}" class="btn btn-primary btn-sm" style="height: 30px;">
                                                Editar etapa
                                            </a>   
                                        </c:if>
                                        <c:if test="${((estadoInscricao == 2) or (estadoInscricao == 3)) and (not selecao.inscricao.divulgadoResultado) and (fn:contains(selecao.inscricao.avaliadores, sessionScope.usuarioDarwin))}">
                                            <a href="/Darwin/avaliar/inscricao/${selecao.inscricao.codEtapa}" class="btn btn-primary btn-sm active" class="btn btn-primary btn-sm" style="height: 30px;">
                                                Avaliar
                                            </a>
                                        </c:if>

                                        <c:if test="${((estadoInscricao == 3) and (not selecao.inscricao.divulgadoResultado))}">
                                            <input type="button" style="font-size: 15px;" class="btn btn-primary btn-sm" value="Ver resultado" data-toggle="modal" data-target="#resultadoEtapa" >
                                            <div class="modal fade" id="resultadoEtapa" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="modalLabel">Resultado da avaliação</h5>
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <p>
                                                                 
                                                                
                                                                Colocar o resultado aqui!!!!!!!!!!!
                                                                
                                                                
                                                                
                                                                
                                                            </p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                            </li>
                            </c:if>
                    <c:set var="i" value="1"></c:set>
                    
                    <c:forEach var="etapa" begin="0" items="${selecao.etapas}">
                        <c:set var="estado" value="${etapa.estado.estado}"></c:set>
                        <li class="${i%2 != 0? 'timeline-inverted': ''}">
                            <div class="timeline-badge ${estado == 1 ? 'insert_invitation': estado == 2 ? 'warning': estado == 3  ? 'success': 'danger'}">
                                <i class="material-icons">${estado == 1 ? 'insert_invitation': estado == 2 ? 'timelapse': estado == 3  ? 'done_all': 'warning'}</i>
                            </div>
                            <div class="timeline-panel">
                                <div class="timeline-heading">
                                    <h2 class="timeline-title text-uppercase">${etapa.titulo}</h2>
                                    <p>
                                        <small class="text-muted">
                                            <i class="glyphicon glyphicon-time"></i>                                             
                                            <fmt:parseDate value="${etapa.periodo.inicio}" pattern="yyyy-MM-dd" var="parseDataInicio" type="date" />
                                            <fmt:parseDate value="${etapa.periodo.termino}" pattern="yyyy-MM-dd" var="parseDataTermino" type="date" />
                                            <fmt:formatDate value="${parseDataInicio}"  pattern="dd/MMMM/yyyy" var="dataInicio" type="date"/>
                                            <fmt:formatDate value="${parseDataTermino}"  pattern="dd/MMMM/yyyy" var="dataTermino" type="date"/>
                                            <b>${fn:replace(dataInicio, "/", " de ")}</b> 
                                            até 
                                            <b>${fn:replace(dataTermino, "/", " de ")}</b>
                                        </small>
                                    </p>
                                </div>
                                <div class="timeline-body" >
                                    <p class="text-justify">${etapa.descricao}</p>
                                    <br>
                                    <b>CRITÉRIO DE AVALIAÇÃO: </b> ${etapa.criterioDeAvaliacao}<br>
                                    <c:if test="${not empty etapa.documentacaoExigida}">
                                    <b>DOCUMENTAÇÃO EXIGIDA: </b> 
                                    <ul>
                                        <c:forEach var="documento" items="${etapa.documentacaoExigida}">
                                        <li>${documento}</b></li>
                                        </c:forEach>
                                    </ul>
                                    </c:if>
                                    <hr>
                                    <c:if test="${(not empty etapa.documentacaoExigida) and (estado == 2) and (fn:contains(permissoes, 'PARTICIPANTE')) and (not fn:contains(permissoes, 'ADMINISTRADOR'))}">
                                        <a href="/Darwin/participarEtapa/${etapa.codEtapa}" class="btn btn-primary btn-sm active" class="btn btn-primary btn-sm" style="height: 30px;">
                                            Enviar documentação
                                        </a>
                                    </c:if>
                                    <c:if test="${(isResponsavel and (selecao.estado eq 'ESPERA')) or (fn:contains(permissoes, 'ADMINISTRADOR'))}">
                                        <a href="/Darwin/editarEtapa/${selecao.codSelecao}/${etapa.codEtapa}" class="btn btn-primary btn-sm" style="height: 30px;">
                                            Editar etapa
                                        </a>   
                                    </c:if>
                                    <c:if test="${((estado == 2) or (estado == 3)) and (not etapa.divulgadoResultado) and (fn:contains(etapa.avaliadores, sessionScope.usuarioDarwin))}">
                                        <a href="/Darwin/avaliar/${etapa.codEtapa}" class="btn btn-primary btn-sm active" class="btn btn-primary btn-sm" style="height: 30px;">
                                            Avaliar
                                        </a>
                                    </c:if>
                                    <c:if test="${((estado == 3) and (etapa.divulgadoResultado)) or (isResponsavel and (estado == 3)) or (fn:contains(permissoes, 'ADMINISTRADOR') and (estado == 3))}">
                                        <a href="/Darwin/resultadoEtapa/${etapa.codEtapa}" class="btn btn-primary btn-sm active" class="btn btn-primary btn-sm" style="height: 30px;">
                                            Ver resultados
                                        </a>
                                    </c:if>
                                </div>
                            </div>
                        </li>
                    <c:set var="i" value="${i + 1}"></c:set>
                    </c:forEach>
                    <c:if test="${((not isResponsavel) or (fn:contains(permissoes, 'PARTICIPANTE')))}">  
                        <li class="">
                            <div class="timeline-badge primary">
                                <i class="material-icons">flag</i>
                            </div>
                            <div class="timeline-heading" style="">
                                <br><br><br>
                            </div>
                        </li>                        
                    </c:if>
                    <c:if test="${(isResponsavel and (selecao.estado eq 'ESPERA')) or (fn:contains(permissoes, 'ADMINISTRADOR'))}">  
                        <li>
                            <a href="/Darwin/cadastrarEtapa/${selecao.codSelecao}" class="timeline-badge primary" >
                                <i class="material-icons">add</i>
                            </a>
                        </li>                        
                    </c:if>
                        
                    </ul>
                </c:if>
                <br>
            </div>
            <div class="col-sm-2 sidebar-offcanvas">
                <c:if test="${not empty selecao.aditivos}">
                <div class="card" >
                    <div class="card-body">
                        <h2 style="font-size: 15px; font-weight: bold;" class="text-center">ADITIVOS</h2>
                    </div>
                    <ul class="list-group list-group-flush">
                        <c:forEach var="aditivo" items="${selecao.aditivos}">
                            <li class="list-group-item disabled">
                                <fmt:parseDate value="${aditivo.data}" pattern="yyyy-MM-dd" var="parseData" type="date" />
                                <fmt:formatDate value="${parseData}"  pattern="dd/MM/yyyy" var="dataAditivo" type="date"/>
                                <a href="/Darwin/visualizarArquvio?selecao=${selecao.codSelecao}&tipo=aditivo&aditivo=${aditivo.codArquivo}" target="_blank">(${dataAditivo}) ${aditivo.titulo}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                </c:if>
                <br>
                <c:if test="${not empty selecao.anexos}">
                <div class="card" >
                    <div class="card-body">
                        <h2 style="font-size: 15px; font-weight: bold;" class="text-center">ANEXOS</h2>
                    </div>
                    <ul class="list-group list-group-flush">
                        <c:forEach var="anexo" items="${selecao.anexos}">
                            <li class="list-group-item disabled">
                                <fmt:parseDate value="${anexo.data}" pattern="yyyy-MM-dd" var="parseData" type="date" />
                                <fmt:formatDate value="${parseData}"  pattern="dd/MM/yyyy" var="dataAditivo" type="date"/>
                                <a href="/Darwin/visualizarArquvio?selecao=${selecao.codSelecao}&tipo=anexo&anexo=${anexo.codArquivo}" target="_blank">(${dataAditivo}) ${anexo.titulo}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                </c:if>
            </div>
        </div>
         -->
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
            $("#navEtapas").addClass(function( index, currentClass ) {
                var addedClass;

                if (screen.width <= 575) {
                  addedClass = "flex-column";
                }

                return addedClass;
            });
            $("#timeline").removeClass(function( index, currentClass ) {
                var addedClass;

                if (screen.width <= 575) {
                  addedClass = "timeline";
                }

                return addedClass;
            });
        </script>
</body>
</html>
