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
			<c:set var="titulo" value="${fn:replace(categoria, '_', ' ')}"></c:set>
			<c:set var="titulo"
				value="${fn:replace(titulo, 'atividades', 'Atividades')}"></c:set>
			<div class="col-sm-8">
				<nav class="breadcrumb"> <span class="breadcrumb-item">Olá ${usuarioChronos.nome}. Você
					está em:</span> <a
					class="breadcrumb-item ${titulo eq 'Início' ? 'active': ''}"
					href="/Chronos">Início</a> <c:if test="${not (titulo eq 'Início')}">
					<a class="breadcrumb-item text-capitalize active" href="#">${categoria}</a>
				</c:if> </nav>
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
				
				
				<br />	
				<div class="col-sm-8">                                       
                    <div class="row">
                        <h1 class="col-sm-8">Gerenciar Permissões</h1>
                       
                    </div>
					<br>
					<table class="table table-striped  table-responsive">
						<thead class="thead-dark">
							<tr>
								<th class="text-center" scope="col">Usuário</th>
								<!--     <th class="text-center" scope="col">Participante</th>  -->
								<th class="text-center" scope="col">Apoiador</th>
								<th class="text-center" scope="col">Responsável</th>
								<th class="text-center" scope="col">Administrador</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="numMaxPorTela" value="20"></c:set>
							<c:set var="pagina"
								value="${(((not empty param.pag) and (param.pag >= 1)) ? param.pag : 1)}"></c:set>
							<c:forEach items="${usuarios}" var="usuario"
								begin="${((pagina - 1) * numMaxPorTela)}"
								end="${((pagina - 1) * numMaxPorTela) + (numMaxPorTela - 1)}">
								<c:set var="permissoes" value="${usuario.permissoes}"></c:set>
								<tr>
									<form method="POST" id="permissaoUser-${usuario.codUsuario}"
										action="/Darwin/permissoes/atualizar"
										enctype="multipart/form-data">
										<input type="hidden" value="${usuario.codUsuario}"
											name="codUsuario" />
										<th scope="row">${usuario.nome}</th>
										<!--            <td>
			                                        <center><input class="form-check-input" type="checkbox" value="1" name="codPermissao" onclick="submeteAtualizacaoDePermissao('permissaoUser-${usuario.codUsuario}')" ${(fn:contains(permissoes, 'PARTICIPANTE') ? 'checked': '')}/></center>
			                         	       </td> -->
										<td>
											<center>
												<input class="form-check-input" type="checkbox" value="2"
													name="codPermissao"
													onclick="submeteAtualizacaoDePermissao('permissaoUser-${usuario.codUsuario}')"
													${(fn:contains(permissoes, 'AVALIADOR') ? 'checked': '')} />
											</center>
										</td>
										<td>
											<center>
												<input class="form-check-input" type="checkbox" value="3"
													name="codPermissao"
													onclick="submeteAtualizacaoDePermissao('permissaoUser-${usuario.codUsuario}')"
													${(fn:contains(permissoes, 'RESPONSAVEL') ? 'checked': '')} />
											</center>
										</td>
										<td>
											<center>
												<input class="form-check-input" type="checkbox" value="4"
													name="codPermissao"
													onclick="submeteAtualizacaoDePermissao('permissaoUser-${usuario.codUsuario}')"
													${(fn:contains(permissoes, 'ADMINISTRADOR') ? 'checked': '')} />
											</center>
										</td>
									</form>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<nav aria-label=""> <c:if test="${titulo eq 'Início'}">
						<c:set value="" var="categoria"></c:set>
						</c:if> <c:if test="${titulo eq 'Minhas atividades'}">
						<c:set value="minhas_Atividades" var="categoria"></c:set>
						</c:if>
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
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/resources/js/script.js" ></script> 
    <script>
      var listaPermissoes = [];
      var codPermissoes = [];
      var numPermissoes = 0;
      function adicionaPermissao() {
        var PermissaoInput = document.getElementById("PermissaoInput").value;
        var nomePermissao = PermissaoInput.substring(PermissaoInput.indexOf("-") + 1, PermissaoInput.lenght);
        var codPermissao = PermissaoInput.substring(0, PermissaoInput.indexOf("-"));
        if (nomePermissao !== "") {
            listaPermissoes[numPermissoes] = nomePermissao;
            codPermissoes[numPermissoes] = codPermissao;
            document.getElementById("PermissaoOption-"+codPermissao+"").disabled = "disabled";
            numPermissoes++;
        }
        document.getElementById("PermissaoInput").value = "";
        atualizaPermissoes();
        
      }
      function atualizaPermissoes() {
          var list = document.getElementById("listaPermissoes");
          list.innerHTML = "";
          for (i = 0;i < listaPermissoes.length;i++) {
            if (listaPermissoes[i] !== "") { 
                list.innerHTML += '<li class="list-group-item"><input type="hidden" name="codPermissoes" value="'+codPermissoes[i]+'" style="display: none;"> '+ listaPermissoes[i] +'<button type="button" class="btn btn-light btn-sm material-icons float-right" style="font-size: 15px;" onclick="removePermissao(\''+listaPermissoes[i]+'\')">clear</button></li>';
            }
          }
      }
      function removePermissao(codPermissao){
          for (i = 0;i < listaPermissoes.length;i++) {
              if(listaPermissoes[i] === codPermissao) {
                  document.getElementById("PermissaoOption-"+codPermissao+"").disabled = "";
                  listaPermissoes[i] = "";
                  codPermissoes[i] = "";
                  
              }
          }
          atualizaPermissoes();
      }
        $(document).ready(function() {
            $("#usuarioInput").change(function(){
                $(".select-usuario").submit();
            });
        });
        
        function submeteAtualizacaoDePermissao(formSelecionado){
            document.getElementById(formSelecionado).submit();
        }
    </script>	
</body>
</html>