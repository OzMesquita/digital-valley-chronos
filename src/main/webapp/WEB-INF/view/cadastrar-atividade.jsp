<%@page import="br.ufc.russas.n2s.chronos.model.EnumTipoPagamento"%>
<%@page import="br.ufc.russas.n2s.chronos.model.EnumTipoAtividade"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML">
<html>
    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="n2s">
        <link rel="icon" href="favicon.ico">
        <title>Chronos - Sistema de Gerenciamento de Atividades</title>
        
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
            <div class="col-sm-8">
                <nav aria-label="breadcrumb" role="navigation">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">Você está em: </li>
                        <li class="breadcrumb-item" aria-current="page"><a href="/Chronos">Início</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Cadastrar Atividade</li>
                        
                        <c:if test="${not empty pai}">
	                    	 <li class="breadcrumb-item" aria-current="page"><a href="/Chronos/atividades/${pai.codAtividade}">${pai.nome}</a></li>
	                    	 <li class="breadcrumb-item active" aria-current="page"><a href="/Chronos/editarAtividade/${pai.codAtividade}">Editar Atividade</a></li>
	                        <li class="breadcrumb-item active" aria-current="page">Cadastrar subatividades</li>
                         </c:if>
                    </ol>
                </nav>
                <c:if test="${not empty mensagem}">
                <div class="alert alert-${status} alert-dismissible fade show" role="alert">
                    ${mensagem}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                </c:if>   
                <h1>Cadastrar Atividade</h1>
                <p>Atenção: Os campos abaixo (*) são de preenchimento obrigatório</p>
                <br>
                <div class="form-group">
                    <form method="POST" action="cadastrarAtividade" accept-charset="UTF-8" enctype="multipart/form-data" id="needs-validation" novalidate> 
                        <label for="tituloInput">Nome*</label>
                        <input type="text" name="nome" class="form-control" id="nomeInput" aria-describedby="nomeHelp" placeholder="Digite o nome da atividade" required>
                        <small id="nomeHelp" class="form-text text-muted">Exemplo: Feira de incentivo a software livre</small>
                        <div class="invalid-feedback">
                        </div>
                        
                         <br>
                        <label for="descricaoInput">Descrição*</label>
                        <textarea class="form-control" name="descricao" id="descricaoInput" placeholder="Digite uma breve descrição sobre a atividade" required></textarea>
                        <div class="invalid-feedback">
                            
                        </div>
                        <br>
                       
                        <c:if test="${not empty pai}">
			                        <label for="paiInput">Atividade pai*</label>
			                        <input type="text" name="pai" class="form-control" id="paiInput" aria-describedby="paiHelp" placeholder="Digite o nome da atividade que engloba esta atividade" value="${pai.nome}" >
			                        <small id="paiHelp" class="form-text text-muted">Exemplo: Semana de incetivo ao software livre</small>
			                        <div class="invalid-feedback">
			                        </div>
			  						<br>
  						 </c:if>   
                       
                        <label for="siglaInput">Sigla*</label>
                        <textarea class="form-control" name="sigla" id="descricaoInput" placeholder="Digite uma sigla para a atividade" required></textarea>
                        <div class="invalid-feedback">
                            
                        </div>
                        <br>
							
						<label for="tipoAtividadeInput">Tipo da Atividade*</label>
    					<select name="tipoAtividade" class="form-control" id="tipoAtividadeInput">
					      <c:forEach items="${EnumTipoAtividade.values()}" var="tipoAtividade">
					      	<option>${tipoAtividade}</option>
					      </c:forEach>
					    </select>
					    <br>
					    
					    <br>
                        <label for="localDaAticidadeInput">Local da Atividade*</label>
                        <input type="text" name="Local" class="form-control" id="localDaAtividadeInput" aria-describedby="localDaAtividadeHelp" placeholder="Digite o local em que a atividade será realizada">
                        <small id="tituloHelp" class="form-text text-muted">Exemplo: Rua Felipe Santiago - N° 411, Cidade Universitária, Russas - CE, </small>
                        <div class="invalid-feedback">
                        </div>
                        <br>
							
						<label for="preRequisitosInput">Pré Requisitos</label>
                        <textarea name="preRequisitos" class="form-control" id="preRequisitosInput" placeholder="Digite uma breve descrição sobre os pré requisitos para participar da atividade"></textarea>
                        <br>	
         
                        <div class="card">
                            <div class="card-header col-auto">
                                
                                <label class="custom-control custom-checkbox mb-2 mr-sm-2 mb-sm-0" for="isVagasLimitadasInput">
                                    <input type="checkbox" class="custom-control-input" id="isVagasLimitadasInput" onclick="habilitaCampoVagas()" alt="Definir número de vagas"/>
                                    <span class="custom-control-indicator"></span>
                                    <span class="custom-control-description" style="margin-top: 4px;">Definir o número de vagas</span>
                                </label>
                            </div>
                            
                            <div class="card-body">
                                <label for="vagasRemuneradasInput">Numero de vagas</label>
                               <input type="number" name="totalVagas" class="form-control col-sm-2 disabled" id="vagasRemuneradasInput" value="0" min="0" max="100" disabled>
                                <div class="invalid-feedback" >
                                    
                                </div>
                                <br>

                                <label for="vagasVoluntariasInput">Número de vagas reservadas a comunidade</label>
                                <input type="number" name="totalVagasComunidade" class="form-control col-sm-2 disabled" id="vagasVoluntariasInput" value="0" min="0" max="100" disabled>
                                <div class="invalid-feedback">
                                    
                                </div>
                            </div>
                        </div>
                        <br>        
                        
                        <label for="tipoPagamentoInput">Forma de Pagamento*</label>
                        <select name="tipoPagamento" class="form-control custom-select" id="tipoPagamentoInput" required>
                            <c:forEach items="${EnumTipoPagamento.values()}" var="tipoPagamento">
					      	<option>${tipoPagamento}</option>
					      </c:forEach>
                        </select>

                        <br> <br>
                          
                        
                                           
                        <label for="responsavelInput">Organizadores</label>                           
                        <div class="form-row">
                            <select id="responsavelInput" class="form-control col-md-8" style="margin-left: 3px">
                                <option value="" selected="selected" disabled="disabled">Selecione o organizador da atividade</option>
                            <c:forEach items="${organizador}" var="organizador">
                                <option id="organizadorOption-${organizador.codUsuario}" value="${organizador.codUsuario}-${organizador.nome}">${organizador.nome}</option>
                            </c:forEach>
                            </select>
                            
                            &nbsp;&nbsp;
                            <input type="button" class="btn btn-secondary btn-sm " onclick="adicionaOrganizador()" value="Adicionar">                            
                        </div>
                        <br>
                        <ul class="list-group col-md-8" id="listaOrganizador">
                        </ul>
										
                        <br>
                        <a href="/Chronos/" type="button" id="enviar" class="btn btn-secondary">
                            Cancelar
                        </a>
                        <input type="button"  class="btn btn-primary" value="Salvar e Continuar" data-toggle="modal" data-target="#confirmarSelecao" >
                        
                        <!-- Modal -->
                        <div class="modal fade" id="confirmarSelecao" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="modalLabel">Confirmar cadastro da seleção</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <p>Você deseja confirmar o cadastro da seleção?</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">Cancelar</button>
                                        <button type="submit" class="btn btn-primary btn-sm">Confirmar</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                         <hr/>  
                      

				</form>
                </div>
            </div>
        </div>
    </div>




    <c:import url="elements/rodape.jsp" charEncoding="UTF-8"></c:import>  
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/resources/js/script.js" ></script>
    <script src="${pageContext.request.contextPath}/resources/js/scriptCadastrarSelecao.js" ></script>
</body>
</html>
