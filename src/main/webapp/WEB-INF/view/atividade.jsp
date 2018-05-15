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
	
<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
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
				<c:if test="${(fn:contains(permissoes, 'ADMINISTRADOR')) and (not atividade.divulgada)}">
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

				<div class="row" style="padding-left: 15px;">
					<h1 class="text-uppercase" style="font-size: 20px;">${atividade.nome}
						(${atividade.sigla})
					</h1>
					<c:if test="${(fn:contains(permissoes, 'ADMINISTRADOR'))}">
	                    <a href="/Chronos/editarAtividade/${atividade.codAtividade}" class="btn btn-primary btn-sm" style="height: 33px;margin-left: 30px;margin-top: -4px;">
	                        Editar Atividade
	                    </a>                    
           	 		</c:if>
	
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
				<div class="container">
		<div class="row ">
			<div class="col-2 text-right">
				<h1 class="display-4"><span class="badge badge-secondary">23</span></h1>
				<h2>OUT</h2>
			</div>
			<div class="col-10">
				<h3 class="text-uppercase"><strong>Ice Cream Social</strong></h3>
				<ul class="list-inline">
				    <li class="list-inline-item"><i class="fa fa-calendar-o" aria-hidden="true"></i> Monday</li>
					<li class="list-inline-item"><i class="fa fa-clock-o" aria-hidden="true"></i> 12:30 PM - 2:00 PM</li>
					<li class="list-inline-item"><i class="fa fa-location-arrow" aria-hidden="true"></i> Cafe</li>
					<c:if test="${(fn:contains(permissoes, 'ADMINISTRADOR'))}">
	                    <a href="/Chronos/editarAtividade/${atividade.codAtividade}" class="btn btn-primary btn-sm" style="height: 33px;margin-left: 30px;margin-top: -4px;">
	                        Inscrever-se
	                    </a>                    
           	 		</c:if>
				</ul>
				<p>Lorem ipsum dolsit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
			</div>
		</div>
		<div class="row ">
			<div class="col-2 text-right">
				<h1 class="display-4"><span class="badge badge-secondary">27</span></h1>
				<h2>OCT</h2>
			</div>
			<div class="col-10">
				<h3 class="text-uppercase"><strong>Operations Meeting</strong></h3>
				<ul class="list-inline">
				    <li class="list-inline-item"><i class="fa fa-calendar-o" aria-hidden="true"></i> Friday</li>
					<li class="list-inline-item"><i class="fa fa-clock-o" aria-hidden="true"></i> 2:30 PM - 4:00 PM</li>
					<li class="list-inline-item"><i class="fa fa-location-arrow" aria-hidden="true"></i> Room 4019</li>
				</ul>
				<p>Lorem ipsum dolsit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
			</div>
		</div>
		<div class="row row-striped">
			<div class="col-2 text-right">
				<h1 class="display-4"><span class="badge badge-secondary">27</span></h1>
				<h2>OCT</h2>
			</div>
			<div class="col-10">
				<h3 class="text-uppercase"><strong>Operations Meeting</strong></h3>
				<ul class="list-inline">
				    <li class="list-inline-item"><i class="fa fa-calendar-o" aria-hidden="true"></i> Friday</li>
					<li class="list-inline-item"><i class="fa fa-clock-o" aria-hidden="true"></i> 2:30 PM - 4:00 PM</li>
					<li class="list-inline-item"><i class="fa fa-location-arrow" aria-hidden="true"></i> Room 4019</li>
				</ul>
				<p>Lorem ipsum dolsit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
			</div>
		</div>
	</div>
				
				

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
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>      
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
