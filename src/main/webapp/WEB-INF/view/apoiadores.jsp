<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="n2s">
        <link rel="icon" href="favicon.ico">
        <title>Chronos - Sistema de Gerenciamento de atividades</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/design.css" />
    </head>
    <body>
        <c:import url="elements/menu-superior.jsp" charEncoding="UTF-8"></c:import>
        <div class="container-fluid">
            <div class="row row-offcanvas row-offcanvas-right">
                <c:import url="elements/menu-lateral-esquerdo.jsp" charEncoding="UTF-8"></c:import>
                <c:set var="titulo" value="${fn:replace(categoria, '_', ' ')}"></c:set>
                <c:set var="titulo" value="${fn:replace(titulo, 'atividades', 'Atividades')}"></c:set>
                <div class="col-sm-8">
                <nav class="breadcrumb">
                    <span class="breadcrumb-item">Você está em:</span>  
                    	 <li class="breadcrumb-item" aria-current="page"><a href="/Chronos">Início</a></li>
                    	 <li class="breadcrumb-item" aria-current="page"><a href="/Chronos/atividades/${atividade.codAtividade}">${atividade.nome}</a></li>
                    	 <li class="breadcrumb-item active" aria-current="page"><a href="/Chronos/editarAtividade/${atividade.codAtividade}">Editar Atividade</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Cadastrar Apoiadores</li>
                </nav>
                <c:set var="mensagem" value="${sessionScope.mensagem}"></c:set>
                <c:if test="${not empty mensagem}">
                    <div class="alert alert-${status} alert-dismissible fade show" role="alert">
                        ${mensagem}
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <c:set scope="session" var="mensagem" value=""></c:set>
                    <c:set scope="session" var="status" value=""></c:set>
                </c:if>
                <div class="row col-sm-12">
                    <h1 class="text-capitalize" >Apoiadores</h1>
                    <br>
                </div>
                
                <c:if test="${empty atividade.apoiadores}">
                    <p class="text-muted">Nenhum apoio cadastrado!</p>
                </c:if>

                <c:if test="${not empty atividade.apoiadores}">
                <div class="row mb-2">
                    <c:forEach var="apoiador" items="${atividade.apoiadores}">
                    
                    <!-- ------------------------------------------- -->
                    
                    
				        <div class="col-md-6">
				          <div class="card flex-md-row mb-4 box-shadow h-md-250">
				            <div class="card-body d-flex flex-column align-items-start">
				              <strong class="d-inline-block mb-2 text-primary">${apoiador.tipoApoio}</strong>
				              <h3 class="mb-0">
				                <a class="text-dark" href="#">${apoiador.nomeInstituicao}</a>
				              </h3>
				              <div class="mb-1 text-muted"><fmt:parseDate value=" ${apoiador.dataPagamento}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
								<fmt:formatDate pattern="EEEE',' dd 'de' MMMM 'de' yyyy" value="${ parsedDateTime }" /></div>
				              <p class="card-text mb-auto">${apoiador.tipoApoio}</p>
				              <a href="${apoiador.siteInstituicao}">Ir para o site</a>
				            </div>
				            <img class="card-img-right flex-auto d-none d-lg-block img-responsive" data-src="holder.js/200x250?theme=thumb" alt="Thumbnail [200x250]" style="width: 150px; height: 150px;" src="https://cache.olhardigital.com.br/uploads/acervo_imagens/2015/07/20150702113911_660_420.jpg" data-holder-rendered="true">
				            
				          </div>
				        </div>
			      
                    
                    <!-- __----------------------------------------------------------------
                    
                    <div class="card">
                        <div class="card-body">
                            <div class="row" style="padding-left: 13px;">
                                <h2 class="card-title text-uppercase font-weight-bold">
                                ${apoiador.nomeInstituicao}
                                </h2>
                            </div>
                            
                            <p class="card-text text-justify">
                                ${apoiador.tipoApoio} - R$ ${apoiador.valorApoio}
                            </p>
                            <h3 class="card-subtitle mb-2 text-muted">
                            	<fmt:parseDate value=" ${apoiador.dataPagamento}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
								<fmt:formatDate pattern="EEEE',' dd 'de' MMMM 'de' yyyy" value="${ parsedDateTime }" />
                            </h3>
                            
                            <p class="card-text text-justify">
                                ${apoiador.siteInstituicao}
                            </p>
                            
                        </div>
                        <div class="row" style="text-align: center;">
                        	<a href="#"
							class="btn btn-primary btn-sm"
							style="height: 33px; margin-left: 30px; margin-top: -4px;">
							Editar Apoio </a>
							
							<a 
							class="btn btn-warning btn-sm"
							style="height: 33px; margin-left: 30px; margin-top: -4px;" data-toggle="modal" data-target="#remover">
							Remover Apoio </a>
                        </div>
                    </div>
                     -->
                </c:forEach>  </div>
                <br/>
                
                </c:if>
                 <li style="list-style: none; text-align: center;">
                 	<button class="btn btn-circle">
                       <a href="/Chronos/cadastrarApoio" class="timeline-badge primary" >
                           <i class="material-icons" title="Adicionar novo apoio">add</i>
                       </a>
                    </button>
                   </li>  
                <c:set var="pagina" value="${(((not empty param.pag) and (param.pag >= 1)) ? param.pag : 1)}"></c:set>
               
               <!-- <c:forEach var="apoio" begin="${((pagina - 1) * 5)}" end="${((pagina - 1) * 5) + 4}" items="${atividade.apoiadores}">
                    <div class="card">
                        <div class="card-body">
                            <div class="row" style="padding-left: 13px;">
                                <h2 class="card-title text-uppercase font-weight-bold">
                                ${atividade.nome} <small>(${atividade.sigla})</small>
                                </h2>
                                <span class="badge badge-pill badge-warning" style="right: 20px; font-size: 10px;position: absolute;">Remover</span>
                            </div>
                            <h3 class="card-subtitle mb-2 text-muted">
                                ${atividade.tipoAtividade} - data aqui!!! 
                              
                            </h3>
                            <p class="card-text text-justify">
                                ${fn:substring(atividade.descricao, 0, 400)}
                                <c:if test="${fn:length(atividade.descricao) > 400}">
                                    [...]
                                </c:if>
                            </p>
                            <c:set var = "nomeUrl" value = "${atividade.nome}"/>
                            <a href="/Chronos/atividades/${atividade.codAtividade}" class="card-link">Mais informações</a>
                            
                        </div>
                    </div>
                </c:forEach> -->
                <br/>
               <!-- <c:if test="${(isResponsavel and (atividade.estado eq 'ESPERA')) or (fn:contains(permissoes, 'ADMINISTRADOR'))}">    --> 
                                             
                <!--     </c:if> -->
                
                <br>
              <!--   <nav aria-label="">
                <c:if test="${titulo eq 'Início'}"><c:set value="" var="categoria"></c:set></c:if>
                <c:if test="${titulo eq 'Minhas atividades'}"><c:set value="minhas_Atividades" var="categoria"></c:set></c:if>
                
                    <ul class="pagination justify-content-center">
                        <li class="page-item "${(pagina <= 1 ? "disabled" : "")}">
                            <a class="page-link" href="/Chronos/${categoria}?pag=${pagina - 1}" tabindex="-1">Anterior</a>
                        </li>
                    <c:forEach var="i" begin="1" end="${(fn:length(apoiadores)/5) + (fn:length(apoiadores)%5 == 0 ? 0 : 1)}">
                        <li class="page-item "${(pagina == i ? "active": "")}"><a class="page-link" href="/Chronos/${categoria}?pag=${i}">${i}</a></li>
                    </c:forEach>
                        <li class="page-item  "${(pagina >= ((fn:length(apoiadores))/5) ? "disabled" : "")}">
                            <a class="page-link" href="/Chronos/${categoria}?pag=${pagina + 1}">Próximo</a>
                        </li>
                    </ul>
                </nav>-->
                
                </div>
            </div>
        </div>
        <c:import url="elements/rodape.jsp" charEncoding="UTF-8"></c:import>  
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>      
    </body>
</html>