function validarMatricula(){
	var matricula = document.formLogin.inputMatricula.value.length;
	var teste = matricula.value.type;
	if (matricula == 7){
		prompt("Teste nan: "+reg);
		return false;
	}else{
		prompt("Teste nan: "+ teste);
		return false;
	}
}

function visualizarServidor(MatriculaSIAPE){
    var matricula = MatriculaSIAPE;
    window.open("ControleServidor?action=visualizarServidor&matricula="+matricula,
    "janela","width=700, height=900, \n\
scrollbars=yes, menubar=no, resizable=no, fullscreen=no");
}

function visualizarVeiculo(Placa){
    var placa = Placa;
    window.open("ControleVeiculo?action=visualizarVeiculo&placa="+placa,
    "janela","width=700, height=900, \n\
scrollbars=yes, menubar=no, resizable=no, fullscreen=no");
}

function desabilitaVisualizarServidor()  {  
    document.getElementById("iMatriculaSiape").disabled = true; 
    document.getElementById("sNomeCompleto").disabled = true; 
    document.getElementById("sEmail").disabled = true;
    document.formaAtualizarServidor.sexo[0].disabled = true;
    document.formaAtualizarServidor.sexo[1].disabled = true;
    document.getElementById("sCpf").disabled = true; 
    document.getElementById("sRg").disabled = true; 
    document.getElementById("sOrgaoExpedidor").disabled = true; 
    document.getElementById("sNaturalidade").disabled = true; 
    document.getElementById("estado").disabled = true; 
    document.getElementById("sNacionalidade").disabled = true; 
    document.getElementById("estadoCivil").disabled = true; 
    document.getElementById("sTelefone1").disabled = true; 
    document.getElementById("sTelefone2").disabled = true; 
    document.formaAtualizarServidor.status_serv[0].disabled = true;
    document.formaAtualizarServidor.status_serv[1].disabled = true;
    
    document.getElementById("status_serv").disabled = true; 
    document.getElementById("sCnh").disabled = true; 
    document.formaAtualizarServidor.bMotorista[0].disabled = true;
    document.formaAtualizarServidor.bMotorista[1].disabled = true;
    document.getElementById("sInfoComplementar").disabled = true; 
} 

function desabilitaVisualizarVeiculo()  {  
    document.getElementById("iPlaca").disabled = true; 
    document.getElementById("iAno").disabled = true; 
    document.getElementById("iMarca").disabled = true;
    
    document.formVisualizarVeiculo.gasolina.disabled= true;
    document.formVisualizarVeiculo.alcool.disabled= true;
    document.formVisualizarVeiculo.gnv.disabled= true;
    document.formVisualizarVeiculo.diesel.disabled= true;
    
    document.getElementById("iModelo").disabled = true; 
    
    document.getElementById("iRenavam").disabled = true; 
    document.getElementById("iCapacidade").disabled = true; 
    document.formVisualizarVeiculo.manutencao[0].disabled = true;
    document.formVisualizarVeiculo.manutencao[1].disabled = true;
     
}  