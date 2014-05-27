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

function validarManutencao(valor){
    if(valor == "t"){
        document.formCadastroVeiculo.sManDataInicial.disabled= false;
        document.formCadastroVeiculo.sManDataFinal.disabled= false;
    }else{
        document.formCadastroVeiculo.sManDataInicial.disabled= true;
        document.formCadastroVeiculo.sManDataFinal.disabled= true;   
    }
}

function validarMotorista(valor){
    if(valor == "1"){
        document.getElementById("sCnh").disabled = false; 
    }else{
        document.getElementById("sCnh").disabled = true;
    }
}

function naoAlterarId(){
    var id = document.getElementById("id").getAttribute('name')
    if(id == "iPlaca"){
        alert("Você não pode alterar a Placa.");
    }else{
        alert("Você não pode alterar a Matrícula.");
    }  
}

function trocarMotorista(checked)
{
    if(checked == 1){
        document.getElementById("selecaoOutroMotorista").className = "invisivel";
    }else{
        document.getElementById("selecaoOutroMotorista").className = "visivel";
    }
}

function exibirDescricaoDestino(selected){
    if(selected == 1){
        document.getElementById("complementoDestino").className = "visivel";
    }else{
        document.getElementById("complementoDestino").className = "invisivel";
    }
}

//function funcoesOnload(valor){
//    validarManutencao(valor);
//    desabilitaVisualizarVeiculo();
//}

function validarVeiculo(){
    if(document.formCadastroVeiculo.iPlaca.value == ""){
        alert("Informe a Placa.");
        return false;
    }
    
    if(document.formCadastroVeiculo.iAno.value == ""){
        alert("Informe o Ano.");
        return false;
    }
    
    if(document.formCadastroVeiculo.iMarca.value == ""){
        alert("Informe a Marca.");
        return false;
    }
    
    if(document.formCadastroVeiculo.iModelo.value == ""){
        alert("Informe o Modelo.");
        return false;
    }
    
    if(document.formCadastroVeiculo.combustivel[0].checked == false && document.formCadastroVeiculo.combustivel[1].checked == false
        && document.formCadastroVeiculo.combustivel[2].checked == false && document.formCadastroVeiculo.combustivel[3].checked == false){
        alert("Escolha uma opção de Combustível.");
        return false;
    }
    
    if(document.formCadastroVeiculo.iRenavam.value == ""){
        alert("Informe o Renanvam.");
        return false;
    }
     
    if(document.formCadastroVeiculo.manutencao[0].checked == true){
        if(document.formCadastroVeiculo.sManDataInicial.value == ""){
            alert("Informe a Data Incial da Manutenção.");
            return false;
        }
    
        if(document.formCadastroVeiculo.sManDataFinal.value == ""){
            alert("Informe a Data Final da Manutenção.");
            return false;
        }
        
        var data1 = document.getElementById("sManDataInicial").value;
        var data2 = document.getElementById("sManDataFinal").value;

        var nova_data1 = parseInt(data1.split("-")[2].toString() + data1.split("-")[1].toString() + data1.split("-")[0].toString());
        var nova_data2 = parseInt(data2.split("-")[2].toString() + data2.split("-")[1].toString() + data2.split("-")[0].toString());
 
        if (nova_data2 < nova_data1){
            alert("Data Final não pode ser menor que a Data Inicial.");
            return false;
        }
    } 
    return true;
}


function validarServidor(){
    if(document.formCadastroServidor.iMatriculaSiape.value == ""){
        alert("Informe a Matrícula.");
        return false;
    }
    
    if(document.formCadastroServidor.sNomeCompleto.value == ""){
        alert("Informe o Nome.");
        return false;
    }
    
    if(document.formCadastroServidor.sEmail.value == ""){
        alert("Informe o E-mail.");
        return false;
    }
    
    if(document.formCadastroServidor.sexo[0].checked == false && document.formCadastroServidor.sexo[1].checked == false){
        alert("Informe o Sexo.");
        return false;
    }
    
    if(document.formCadastroServidor.sDataNascimento.value == ""){
        alert("Informe a Data de Nascimento.");
        return false;
    }
    
    if(document.formCadastroServidor.sCpf.value == ""){
        alert("Informe o CPF.");
        return false;
    }
    
    if(document.formCadastroServidor.sOrgaoExpedidor.value == ""){
        alert("Informe o Orgão Expedidor.");
        return false;
    }
    
    if(document.formCadastroServidor.sTelefone1.value == ""){
        alert("Informe o Telefone.");
        return false;
    }
    
    if(document.formCadastroServidor.bMotorista[0].checked == true){
        if(document.formCadastroServidor.sCnh.value == ""){
            alert("Informe a CNH.");
            return false;
        }
    }
    return true;
}