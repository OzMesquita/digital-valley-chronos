/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


    function ordenaLista() {
        var list, i, switching, b, shouldSwitch;
        list = document.getElementById("listaAvaliadores");
        switching = true;
        while (switching) {
          switching = false;
          b = list.getElementsByTagName("li");
          for (i = 0; i < (b.length - 1); i++) {
            shouldSwitch = false;
            if ((b[i + 1].getElementsByTagName("input")[0].checked === true) && (b[i].getElementsByTagName("input")[0].checked === false)) {
              shouldSwitch= true;
              break;
            }
          }
          if (shouldSwitch) {
            b[i].parentNode.insertBefore(b[i + 1], b[i]);
            switching = true;
          }
        }
      }
      
      var listaInputs = document.getElementById("listaDocumentos").getElementsByTagName("input");
      var numDocumentos = listaInputs.length;
      var listaDocumentos = [];
      var k;
      for(k = 0;k < numDocumentos;k++){
          listaDocumentos[k] = listaInputs[k].value;
      }
      atualizaDocumentos();
      
      function adicionaDocumento(){
        var nomeDocumento = document.getElementById("documentoInput").value;
        if(nomeDocumento !== ""){
            listaDocumentos[numDocumentos] = nomeDocumento;
            numDocumentos++;
        }
        document.getElementById("documentoInput").value = "";
        atualizaDocumentos();
        
      }
      function atualizaDocumentos(){
          var list = document.getElementById("listaDocumentos");
          list.innerHTML = "";
          for(i = 0;i < listaDocumentos.length;i++){
            if(listaDocumentos[i] !== ""){
                list.innerHTML += '<li class="list-group-item" ><input type="hidden" name="documentosExigidos" value="'+listaDocumentos[i]+'" style="display: none;"> '+ listaDocumentos[i] +'<button type="button" class="btn btn-light btn-sm material-icons float-right" style="font-size: 15px;" onclick="removeDocumento(\''+listaDocumentos[i]+'\')">clear</button></li>';
            }
          }
      }
      function removeDocumento(nome){
          for(i = 0;i < listaDocumentos.length;i++){
              if(listaDocumentos[i] === nome){
                  listaDocumentos[i] = "";
              }
          }
          atualizaDocumentos();
      }
        var codAvaliadores = [];
        var listaAvaliadores = [];
        var numAvaliadores = 0;
        var listaAInputs = document.getElementById("listaAvaliadores").getElementsByTagName("input");
        var numAvaliadores = listaAInputs.length;
        var l;
        for(l = 0;l < numAvaliadores;l++){
          var avaliadorInput = listaAInputs[l].value;
          var nomeAvaliador = avaliadorInput.substring(avaliadorInput.indexOf("-") + 1, avaliadorInput.lenght);
          var codAvaliador = avaliadorInput.substring(0, avaliadorInput.indexOf("-"));
          listaAvaliadores[l] = nomeAvaliador;

          codAvaliadores[l] = codAvaliador;
  
        atualizaAvaliadores();
  }

      function adicionaAvaliador(){
        var avaliadorInput = document.getElementById("avaliadorInput").value;
        var nomeAvaliador = avaliadorInput.substring(avaliadorInput.indexOf("-") + 1, avaliadorInput.lenght);
        var codAvaliador = avaliadorInput.substring(0, avaliadorInput.indexOf("-"));
        if(nomeAvaliador !== ""){
            listaAvaliadores[numAvaliadores] = nomeAvaliador;
            codAvaliadores[numAvaliadores] = codAvaliador;
            document.getElementById("avaliadorOption-"+nomeAvaliador+"").disabled = "disabled";
            numAvaliadores++;
        }
        document.getElementById("avaliadorInput").value = "";
        atualizaAvaliadores();
        
      }
      function atualizaAvaliadores(){
          var list = document.getElementById("listaAvaliadores");
          list.innerHTML = "";
          for(i = 0;i < listaAvaliadores.length;i++){
            if(listaAvaliadores[i] !== ""){
                list.innerHTML += '<li class="list-group-item"><input type="hidden" name="codAvaliadores" value="'+codAvaliadores[i]+'" style="display: none;"> '+ listaAvaliadores[i] +'<button type="button" class="btn btn-light btn-sm material-icons float-right" style="font-size: 15px;" onclick="removeAvaliador(\''+listaAvaliadores[i]+'\')">clear</button></li>';
            }
          }
      }
      function removeAvaliador(codAvaliador){
          for(i = 0;i < listaAvaliadores.length;i++){
              if(listaAvaliadores[i] === codAvaliador){
                  document.getElementById("avaliadorOption-"+codAvaliador+"").disabled = "";
                  listaAvaliadores[i] = "";
                  codAvaliadores[i] = "";
                  
              }
          }
          atualizaAvaliadores();
      }
      

