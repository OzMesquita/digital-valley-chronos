
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


var listaOrganizadores = [];
var codOrganizadores= [];
var numOrganizadores = 0;
function adicionaOrganizador() {
    var organizadorInput = document.getElementById("organizadorInput").value;
    var nomeOrganizador = organizadorInput.substring(organizadorInput.indexOf("-") + 1, organizadorInput.lenght);
    var codOrganizador = organizadorInput.substring(0, organizadorInput.indexOf("-"));
    if (nomeOrganizador !== "") {
        listaOrganizadores[numOrganizadores] = nomeOrganizador;
        codOrganizadores[numOrganizadores] = codOrganizador;
        document.getElementById("organizadorOption-" + codOrganizador + "").disabled = "disabled";
        numOrganizadores++;
    }
    document.getElementById("organizadorInput").value = "";
    atualizaOrganizadores();

}
function atualizaOrganizadores() {
    var list = document.getElementById("listaOrganizadores");
    list.innerHTML = "";
    for (i = 0; i < listaOrganizadores.length; i++) {
        if (listaOrganizadores[i] !== "") {
            list.innerHTML += '<li class="list-group-item"><input type="hidden" name="codOrganizadores" value="' + codOrganizadores[i] + '" style="display: none;"> ' + listaOrganizadores[i] + '<button type="button" class="btn btn-light btn-sm material-icons float-right" style="font-size: 15px;" onclick="removeOrganizador(\'' + codOrganizadores[i] + '\')">clear</button></li>';
        }
    }
}
function removeOrganizador(codOrganizador) {
    for (i = 0; i < listaOrganizadores.length; i++) {
        if (codOrganizadores[i] === codOrganizador) {
            document.getElementById("organizadorOption-" + codOrganizador + "").disabled = "";
            listaOrganizadores[i] = "";
            codOrganizadores[i] = "";

        }
    }
    atualizaOrganizadores();
}

// listar apoiadores 

var listaApoiadores = [];
var codOrganizadores= [];
var numApoiadores = 0;
function adicionaApoiador() {
    var apoiadorInput = document.getElementById("apoiadorInput").value;
    var nomeApoiador = apoiadorInput.substring(apoiadorInput.indexOf("-") + 1, apoiadorInput.lenght);
    //var codApoiador = apoiadorInput.substring(0, apoiadorInput.indexOf("-"));
    if (nomeApoiador !== "") {
        listaApoiadorres[numApoiadorres] = nomeApoiador;
       // codOrganizadores[numOrganizadores] = codOrganizador;
       // document.getElementById("organizadorOption-" + codOrganizador + "").disabled = "disabled";
        numApoiadores++;
    }
    document.getElementById("apoiadorInput").value = "";
    atualizaApoiadores();

}
function atualizaApoiadores() {
    var list = document.getElementById("listaApoiadores");
    list.innerHTML = "";
    for (i = 0; i < listaApoiadores.length; i++) {
        if (listaApoiadores[i] !== "") {
            list.innerHTML += '<li class="list-group-item"><input type="hidden" name="listaApoiadores" value="' + listaApoiadores[i] + '" style="display: none;"> ' + listaApoiadores[i] + '<button type="button" class="btn btn-light btn-sm material-icons float-right" style="font-size: 15px;" onclick="removeApoiador(\'' + nomeApoiador[i] + '\')">clear</button></li>';
        }
    }
}
function removeOrganizador(codOrganizador) {
    for (i = 0; i < listaOrganizadores.length; i++) {
        if (codOrganizadores[i] === codOrganizador) {
            document.getElementById("organizadorOption-" + codOrganizador + "").disabled = "";
            listaOrganizadores[i] = "";
            codOrganizadores[i] = "";

        }
    }
    atualizaOrganizadores();
}

$(function () {
    $('#btnRight').click(function (e) {
        var selectedOpts = $('#colaboradorInput1 option:selected');
        if (selectedOpts.length == 0) {
            alert("Selecione um Colaborador.");
            e.preventDefault();
        }
        $('#colaboradorInput2').append($(selectedOpts).clone());
        $(selectedOpts).remove();
        e.preventDefault();
    });
    $('#btnLeft').click(function (e) {
        var selectedOpts = $('#colaboradorInput2 option:selected');
        if (selectedOpts.length == 0) {
            alert("Selecione um Colaborador.");
            e.preventDefault();
        }
        $('#colaboradorInput1').append($(selectedOpts).clone());
        $(selectedOpts).remove();
        e.preventDefault();
    });
}(jQuery));
