<%-- 
    Document   : menu-lateral
    Created on : 05/10/2017, 11:50:30
    Author     : Alex Felipe
--%>
<div class="col-12 col-md-3 col-xl-2 bd-sidebar" style="margin-top: 5px;">
    <div class="btn-group-vertical d-flex flex-column border" role="group">
    <strong><p class="btn btn-sm" align="center">Categorias</p></strong>
        <a href="/Darwin/assistência_estudantil" class="btn btn-light btn-sm text-left ${categoria == 'assistência_estudantil' ? 'active': ''}">Assistência Estudantil</a>
        <a href="/Darwin/bolsas_para_discentes" class="btn btn-light btn-sm text-left ${categoria == 'bolsas_para_discentes' ? 'active': ''}">Seleções para Discentes</a>  
        <a href="/Darwin/cargos_de_docente" class="btn btn-light btn-sm text-left ${categoria == 'cargos_de_docente' ? 'active': ''}">Cargos de Docente</a>
        <a href="/Darwin/cargos_de_técnicos_admin" class="btn btn-light btn-sm text-left ${categoria == 'cargos_de_técnicos_admin' ? 'active': ''}">Cargos de Técnicos Admin.</a>
        <a href="/Darwin/professores_substitutos" class="btn btn-light btn-sm text-left ${categoria == 'professores_substitutos' ? 'active': ''}">Professores Substitutos</a>      
    </div>
    <br>
</div>