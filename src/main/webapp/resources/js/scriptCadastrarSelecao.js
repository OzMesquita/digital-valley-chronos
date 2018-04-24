
function habilitaCampoVagas() {
    if (!document.getElementById('isVagasLimitadasInput').checked) {
        document.getElementById('vagasRemuneradasInput').disabled = true;
        document.getElementById('vagasVoluntariasInput').disabled = true;
    } else {
        document.getElementById('vagasRemuneradasInput').disabled = false;
        document.getElementById('vagasVoluntariasInput').disabled = false;
    }
}

var listaNomeAnexo = [];
var listaLinkAnexo = [];
var numAnexo = 0;
function adicionaAnexo() {
    var nomeAnexo = document.getElementById("nomeAnexoInput").value;
    var linkAnexo = document.getElementById("linkAnexoInput").value;
    if (nomeAnexo !== "" && linkAnexo !== "") {
        listaNomeAnexo[numAnexo] = nomeAnexo;
        listaLinkAnexo[numAnexo] = linkAnexo;
        numAnexo++;
    }
    document.getElementById("nomeAnexoInput").value = "";
    document.getElementById("linkAnexoInput").value = "";
    atualizaAnexo();
}
function atualizaAnexo() {
    var list = document.getElementById("listaAnexos");
    list.innerHTML = "";
    for (i = 0; i < listaNomeAnexo.length; i++) {
        if (listaNomeAnexo[i] !== "") {
            list.innerHTML += '<li class="list-group-item" ><input type="hidden" name="listaNomeAnexo" value="' + listaNomeAnexo[i] + '" style="display: none;"> <input type="hidden" name="listaLinkAnexo" value="' + listaLinkAnexo[i] + '" style="display: none;"> <a href="' + listaLinkAnexo[i] + '" target="_blank">' + listaNomeAnexo[i] + '</a><button type="button" class="btn btn-light btn-sm material-icons float-right" style="font-size: 15px;" onclick="removeAnexo(\'' + listaNomeAnexo[i] + '\')">clear</button></li>';
        }
    }
}
function removeAnexo(nome) {
    for (i = 0; i < listaNomeAnexo.length; i++) {
        if (listaNomeAnexo[i] === nome) {
            listaNomeAnexo[i] = "";
            listaLinkAnexo[i] = "";
        }
    }
    atualizaAnexo();
}



var listaNomeAditivo = [];
var listaLinkAditivo = [];
var numAditivo = 0;
function adicionaAditivo() {
    var nomeAditivo = document.getElementById("nomeAditivoInput").value;
    var linkAditivo = document.getElementById("linkAditivoInput").value;
    if (nomeAditivo !== "" && linkAditivo !== "") {
        listaNomeAditivo[numAditivo] = nomeAditivo;
        listaLinkAditivo[numAditivo] = linkAditivo;
        numAditivo++;
    }
    document.getElementById("nomeAditivoInput").value = "";
    document.getElementById("linkAditivoInput").value = "";
    atualizaAditivo();
}
function atualizaAditivo() {
    var list = document.getElementById("listaAditivos");
    list.innerHTML = "";
    for (i = 0; i < listaNomeAditivo.length; i++) {
        if (listaNomeAditivo[i] !== "") {
            list.innerHTML += '<li class="list-group-item" ><input type="hidden" name="listaNomeAditivo" value="' + listaNomeAditivo[i] + '" style="display: none;"> <input type="hidden" name="listaLinkAditivo" value="' + listaLinkAditivo[i] + '" style="display: none;"> <a href="' + listaLinkAditivo[i] + '" target="_blank">' + listaNomeAditivo[i] + '</a><button type="button" class="btn btn-light btn-sm material-icons float-right" style="font-size: 15px;" onclick="removeAditivo(\'' + listaNomeAditivo[i] + '\')">clear</button></li>';
        }
    }
}
function removeAditivo(nome) {
    for (i = 0; i < listaNomeAditivo.length; i++) {
        if (listaNomeAditivo[i] === nome) {
            listaNomeAditivo[i] = "";
            listaLinkAditivo[i] = "";
        }
    }
    atualizaAditivo();
}


var listaResponsaveis = [];
var codResponsaveis = [];
var numResponsaveis = 0;
function adicionaResponsavel() {
    var responsavelInput = document.getElementById("responsavelInput").value;
    var nomeResponsavel = responsavelInput.substring(responsavelInput.indexOf("-") + 1, responsavelInput.lenght);
    var codResponsavel = responsavelInput.substring(0, responsavelInput.indexOf("-"));
    if (nomeResponsavel !== "") {
        listaResponsaveis[numResponsaveis] = nomeResponsavel;
        codResponsaveis[numResponsaveis] = codResponsavel;
        document.getElementById("responsavelOption-" + codResponsavel + "").disabled = "disabled";
        numResponsaveis++;
    }
    document.getElementById("responsavelInput").value = "";
    atualizaResponsaveis();

}
function atualizaResponsaveis() {
    var list = document.getElementById("listaResponsaveis");
    list.innerHTML = "";
    for (i = 0; i < listaResponsaveis.length; i++) {
        if (listaResponsaveis[i] !== "") {
            list.innerHTML += '<li class="list-group-item"><input type="hidden" name="codResponsaveis" value="' + codResponsaveis[i] + '" style="display: none;"> ' + listaResponsaveis[i] + '<button type="button" class="btn btn-light btn-sm material-icons float-right" style="font-size: 15px;" onclick="removeResponsaveis(\'' + codResponsaveis[i] + '\')">clear</button></li>';
        }
    }
}
function removeResponsaveis(codResponsavel) {
    for (i = 0; i < listaResponsaveis.length; i++) {
        if (codResponsaveis[i] === codResponsavel) {
            document.getElementById("responsavelOption-" + codResponsavel + "").disabled = "";
            listaResponsaveis[i] = "";
            codResponsaveis[i] = "";

        }
    }
    atualizaResponsaveis();
}

