// <<<< AO CARREGAR PÁGINA   >>> ///
function funcoesOnloadServidor(){
    pegarCookies();
    if(document.body.className == "") {
        desabilitaVisualizarServidor();
        document.body.className = "habilita";
        document.getElementById("btnSaveHidden").style.display = "none";
        document.getElementById("btnEditHidden").style.display = "block";  
    } else {
        habilitaVisualizarServidor();
        document.body.className = "habilita";
        document.getElementById("btnSaveHidden").style.display = "block";
        document.getElementById("btnEditHidden").style.display = "none";  
    }
}

// <<<< AO CARREGAR PÁGINA   >>> ///
function funcoesOnloadVeiculo(){
    pegarCookies();
    if(document.body.className == "") {
        desabilitaVisualizarVeiculo();
        document.body.className = "habilita";
        document.getElementById("btnSaveHidden").style.display = "none";
        document.getElementById("btnEditHidden").style.display = "block";  
    } else {
        habilitaVisualizarVeiculo();
        document.body.className = "habilita";
        document.getElementById("btnSaveHidden").style.display = "block";
        document.getElementById("btnEditHidden").style.display = "none";  
    }
}

// <<<< AO CARREGAR PÁGINA   >>> ///
function funcoesOnloadReserva(){
    pegarCookies();
    //Verifica qual check está marcado e chama função passando o valor
    if(document.formInserirReserva.inputMotorista[0].checked == true){
        trocarMotorista(document.formInserirReserva.inputMotorista[0].value);
    }else if(document.formInserirReserva.inputMotorista[1].checked == true){
        trocarMotorista(document.formInserirReserva.inputMotorista[1].value);
        
    }
        //Verifica qual option está selecionado e chama função passando o valor
   exibirDescricaoDestino(document.formInserirReserva.iDestino.value);
    if(document.body.className == "desabilita") {
        desabilitaVisualizarReserva();
        document.body.className = "habilita";
        document.getElementById("btnSaveHidden").style.display = "none";
        document.getElementById("btnEditHidden").style.display = "block";
    }else{
        document.body.className = "habilita";
        habilitaVisualizarReserva();
        document.getElementById("btnEditHidden").style.display = "none";
        document.getElementById("btnSaveHidden").style.display = "block";
    }

    
}

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

// HABILITA OS CAMPOS DO FORMULÁRIO AO CLICAR NO BOTÃO "EDITAR"
function habilitaVisualizarReserva(){
    document.formInserirReserva.inputMotorista[0].disabled = false;
    document.formInserirReserva.inputMotorista[1].disabled = false;
    document.getElementById("inputOutroMotorista").disabled = false; 
    document.getElementById("iOcupantes").disabled = false;
    document.getElementById("iDestino").disabled = false;
    document.getElementById("inputDestinoComplementar").disabled = false;
}

// DESABILITA FORMULÁRIO AO CARREGAR PÁGINA DE VISUALIZAÇÃO
function desabilitaVisualizarReserva(){
    document.formInserirReserva.inputMotorista[0].disabled = true;
    document.formInserirReserva.inputMotorista[1].disabled = true;
    document.getElementById("inputOutroMotorista").disabled = true; 
    document.getElementById("iOcupantes").disabled = true;
    document.getElementById("iDestino").disabled = true;
    document.getElementById("inputDestinoComplementar").disabled = true;
}

// HABILITA OS CAMPOS DO FORMULÁRIO AO CLICAR NO BOTÃO "EDITAR"
function habilitaVisualizarServidor(){
    document.getElementById("id").disabled = false; 
    document.getElementById("sNomeCompleto").disabled = false; 
    document.getElementById("sEmail").disabled = false;
    document.formCadastroServidor.sexo[0].disabled = false;
    document.formCadastroServidor.sexo[1].disabled = false;
    document.formCadastroServidor.sDataNascimento.disabled = false;
    document.getElementById("sCpf").disabled = false; 
    document.getElementById("sRg").disabled = false; 
    document.getElementById("sOrgaoExpedidor").disabled = false; 
    document.getElementById("sNaturalidade").disabled = false; 
    document.formCadastroServidor.estado.disabled = false;
    document.getElementById("sNacionalidade").disabled = false; 
    document.getElementById("estadoCivil").disabled = false; 
    document.getElementById("sTelefone1").disabled = false; 
    document.getElementById("sTelefone2").disabled = false; 
    document.formCadastroServidor.status_serv[0].disabled = false;
    document.formCadastroServidor.status_serv[1].disabled = false;
    document.getElementById("status_serv").disabled = false; 
    document.getElementById("sCnh").disabled = false; 
    document.formCadastroServidor.bMotorista[0].disabled = false;
    document.formCadastroServidor.bMotorista[1].disabled = false;
    document.getElementById("sInfoComplementar").disabled = false;
}

// DESABILITA FORMULÁRIO AO CARREGAR PÁGINA DE VISUALIZAÇÃO
function desabilitaVisualizarServidor()  {  
    document.getElementById("id").disabled = true; 
    document.getElementById("sNomeCompleto").disabled = true; 
    document.getElementById("sEmail").disabled = true;
    document.formCadastroServidor.sexo[0].disabled = true;
    document.formCadastroServidor.sexo[1].disabled = true;
    document.formCadastroServidor.sDataNascimento.disabled = true;
    document.getElementById("sCpf").disabled = true; 
    document.getElementById("sRg").disabled = true; 
    document.getElementById("sOrgaoExpedidor").disabled = true; 
    document.getElementById("sNaturalidade").disabled = true; 
    document.formCadastroServidor.estado.disabled = true;
    document.getElementById("sNacionalidade").disabled = true; 
    document.getElementById("estadoCivil").disabled = true; 
    document.getElementById("sTelefone1").disabled = true; 
    document.getElementById("sTelefone2").disabled = true; 
    document.formCadastroServidor.status_serv[0].disabled = true;
    document.formCadastroServidor.status_serv[1].disabled = true;
    document.getElementById("status_serv").disabled = true; 
    document.getElementById("sCnh").disabled = true; 
    document.formCadastroServidor.bMotorista[0].disabled = true;
    document.formCadastroServidor.bMotorista[1].disabled = true;
    document.getElementById("sInfoComplementar").disabled = true;    
} 

// DESABILITA FORMULÁRIO AO CARREGAR PÁGINA DE VISUALIZAÇÃO
function desabilitaVisualizarVeiculo()  {  
    document.getElementById("id").disabled = true; 
    document.getElementById("iAno").disabled = true; 
    document.getElementById("iMarca").disabled = true;
    document.getElementById("gasolina").disabled = true;
    document.getElementById("alcool").disabled = true;
    document.getElementById("gnv").disabled = true;
    document.getElementById("diesel").disabled = true;
    document.getElementById("flex").disabled = true;
    document.getElementById("iModelo").disabled = true; 
    document.getElementById("iRenavam").disabled = true; 
    document.getElementById("iCapacidade").disabled = true; 
}

// HABILITA OS CAMPOS DO FORMULÁRIO AO CLICAR NO BOTÃO "EDITAR"
function habilitaVisualizarVeiculo()  {  
    document.getElementById("id").disabled = false; 
    document.getElementById("iAno").disabled = false; 
    document.getElementById("iMarca").disabled = false;
    document.getElementById("gasolina").disabled = false;
    document.getElementById("alcool").disabled = false;
    document.getElementById("gnv").disabled = false;
    document.getElementById("diesel").disabled = false;
    document.getElementById("flex").disabled = false;
    document.getElementById("iModelo").disabled = false; 
    document.getElementById("iRenavam").disabled = false; 
    document.getElementById("iCapacidade").disabled = false;     
}

// QUANDO CLICAR NO CAMPO ID(PLACA OU MATRÍCULA) EXIBE MENSAGEM
function naoAlterarId(){
    var id = document.getElementById("id").getAttribute('name')
    if(id == "iPlaca"){
        alert("Você não pode alterar a Placa.");
    }else{
        alert("Você não pode alterar a Matrícula.");
    }  
}

// SE NÃO FOR O MOTORISTA DA RESERVA, MOSTRA CAMPO PARA SELECIONAR OUTRO MOTORISTA
function trocarMotorista(checked){
    if(checked == 1){
        document.getElementById("selecaoOutroMotorista").className = "invisivel";
    }else{
        document.getElementById("selecaoOutroMotorista").className = "visivel";
    }
}

// SE ESCOLHER A OPÇÃO "OUTROS" DESTINO, MOSTRA CAMPO PARA PREENCHIMENTO DO DESTINO
function exibirDescricaoDestino(selected){
    if(selected == 1){
        document.getElementById("complementoDestino").className = "visivel";
    }else{
        document.getElementById("complementoDestino").className = "invisivel";
    }
}

// AO CLICAR NO BOTÃO "SALVAR" FAZ VALIDAÇÕES 
function validarVeiculo(){
    
    var d = document.formCadastroVeiculo;


    if(d.iPlaca.value == ""){
        alert("Dados obrigatórios não preenchidos. Prencha o campo Placa!");
        d.iPlaca.focus();
        return false;
    }
    
    if(d.iPlaca.value.length != "" && d.iPlaca.value.length < 8){
        alert("Dados inválidos. Preencha o campo Placa corretamente, como no exemplo: CAR-2121");
        d.iPlaca.focus();
        return false;
    }

    if(d.iAno.value == ""){
        alert("Dados obrigatórios não preenchidos. Preencha o campo Ano!");
        d.iAno.focus();
        return false;
    }
    
    if (d.iAno.value.length <4){
        alert("Dados Inválidos. Preencha o campo Ano com 4 números!");
        d.iAno.focus();
        return false;
    }
    
    if (d.iAno.value.length == 4){
        hoje = new Date();
        anoAtual = hoje.getFullYear();
        barras = d.iAno.value;
        ano = barras;
    
        if (ano > anoAtual){
            alert("Dados Inválidos. O campo Ano não pode ser maior que o ano atual!");
            d.iAno.focus();
            return false;
        }else if (ano < 2000){
            alert("Dados Inválidos. Preencha o campo Ano corretamente!");
            d.iAno.focus();
            return false;
        }
    }
        
    
    if(d.iMarca.value == ""){
        alert("Dados obrigatórios não preenchidos. Preencha o campo Marca!");
        d.iMarca.focus();
        return false;
    }

    if(d.iModelo.value == ""){
        alert("Dados obrigatórios não preenchidos. Preencha o campo Modelo!");
        d.iModelo.focus();
        return false;
    }
    
    if(d.combustivel[0].checked == false && d.combustivel[1].checked == false
        && d.combustivel[2].checked == false && d.combustivel[3].checked == false && d.combustivel[4].checked == false){
        alert("Dados obrigatórios não preenchidos. Selecione o tipo de Combustível!");
        return false;
    }
    
    if(d.iRenavam.value == ""){
        alert("Dados obrigatórios não preenchidos. Preencha o campo Renavam!");
        d.iRenavam.focus();
        return false;
    }
    
    if(d.iRenavam.value.length <11){
        alert("Dados inválidos. Preencha o campo Renavam com 11 dígitos!");
        d.iRenavam.focus();
        return false;
    }
    
    if(d.iCapacidade.value == ""){
        alert("Dados obrigatórios não preenchidos. Preencha o campo Capacidade!");
        d.iCapacidade.focus();
        return false;
    }
    
    if(d.iCapacidade.value == 0){
        alert("Dados inválidos. O campo Capacidade não pode ser igual a zero!");
        d.iCapacidade.focus();
        return false;
    }
    
    if(d.iCapacidade.value > 50){
        alert("Dados Inválidos. O campo Capacidade não pode ser superior a 50!")
        d.iCapacidade.focus();
        return false;
    }
}

// AO CLICAR NO BOTÃO "SALVAR" FAZ VALIDAÇÕES DOS CAMPOS OBRIGATÓRIOS
function validarServidor(){
    
    var d = document.formCadastroServidor;
   
    if(d.iMatriculaSiape.value == ""){
        alert("Dados obrigatórios não preenchidos. Preencha o campo Matrícula!");
        d.iMatriculaSiape.focus();
        return false;
    }
    
    if(d.iMatriculaSiape.value.length <7){
        alert("Dados inválidos.`Preencha o campo Matrícula com 7 dígitos!");
        d.iMatriculaSiape.focus();
        return false;
    }
    
    if(d.sNomeCompleto.value == ""){
        alert("Dados obrigatórios não preenchidos. Preencha o campo Nome!");
        d.sNomeCompleto.focus();
        return false;
    }
   
    if(d.sEmail.value == ""){
        alert("Dados obrigatórios não preenchidos. Preencha o campo E-mail!");
        d.sEmail.focus();
        return false;
    }
    
    if(d.sEmail.value != ""){
        var str = d.sEmail.value;
        var filter = /^([\w-]+(?:\.[\w-]+)*)+@[a-zA-Z]{8}\.[a-zA-Z]{4}\.[a-zA-Z]{3}\.[a-zA-Z]{2}/; 
        if(!filter.test(str)){
            alert("Dados Inválidos. Preencha o campo e-mail com @restinga.ifrs.edu.br!");
            d.sEmail.focus();
            return false;
        }
    }
        
    if(d.sexo[0].checked == false && d.sexo[1].checked == false){
        alert("Dados obrigatórios não preenchidos. Selecione o Sexo!");
        return false;
    }
    
    if(d.sCpf.value == ""){
        alert("Dados obrigatórios não preenchidos. Preencha o campo CPF!");
        d.sCpf.focus();
        return false;
    }
    
    if(d.sCpf.value.length <14){
        alert("Dados inválidos. Preencha o campo CPF com 11 dígitos!");
        d.sCpf.focus();
        return false;
    }
    
    if(d.sRg.value == ""){
        alert("Dados obrigatórios não preenchidos. Preencha o campo RG!");
        d.sRg.focus();
        return false;
    }
    
    if(d.sRg.value.length <8){
        alert("Dados inválidos. Preencha o campo RG com no mínimo 7 dígitos!");
        d.sRg.focus();
        return false;
    }
    
    if(d.sOrgaoExpedidor.value == ""){
        alert("Dados obrigatórios não preenchidos. Preencha o campo Órgão Expedidor!");
        d.sOrgaoExpedidor.focus();
        return false;
    }
    
    if(d.sOrgaoExpedidor.value.length <6){
        alert("Dados Inválidos. Preencha o campo Órgão Expedidor com pelo menos 5 dígitos, por exemplo: SSE/RS!");
        d.sOrgaoExpedidor.focus();
        return false;
    }
    
    if(d.sTelefone1.value == ""){
        alert("Dados obrigatórios não preenchidos. Preencha o campo Telefone1!");
        d.sTelefone1.focus();
        return false;
    }
    
    if(d.sTelefone1.value.length <14){
        alert("Dados inválidos. Preecha o campo Telefone corretamente!");
        d.sTelefone1.focus();
        return false;
    }
    
    if(d.sTelefone2.value != "" && d.sTelefone2.value.length <14){
        alert("Dados inválidos. Preecha o campo Telefone corretamente! ");
        d.sTelefone2.focus();
        return false;
    }
    
    if (d.bMotorista[1].checked==false && d.bMotorista[0].checked==false) {
        alert("Dados obrigatórios não preenchidos. Selecione se o servidor é ou não motorista!");
        return false;
    }
    
    //SE FOR MOTORISTA, VERIFICA SE O CAMPO CNH FOI PREENCHIDO
    if(d.bMotorista[1].checked == false && d.bMotorista[0].checked==true){
        if(d.sCnh.value == ""){
            alert("Dados obrigatórios não preenchidos. Preencha o campo CNH!");
            d.sCnh.focus();
            return false;
        } 
    }
    
    if(d.sCnh.value != "" && d.sCnh.value.length <11){
        alert("Dados inválidos. Preencha o campo CNH com 11 dígitos!");
        d.sCnh.focus();
        return false;
    }  
    
    return true;
}

// AO CLICAR EM "EXCLUIR", MOSTRA MENSAGEM DE CONFIRMAÇÃO DE EXCLUSÃO
function exluirCadastro(){
    if (window.confirm (' Deseja realmente excluir? ')){
        return true;
    }
    return false;
}

// AO CLICAR EM "EXCLUIR", MOSTRA MENSAGEM DE CONFIRMAÇÃO DE EXCLUSÃO
function exluirReserva(){
    if (window.confirm (' Deseja realmente excluir esta reserva? ')){
        return true;
    }
    return false;
    
}


function validarReserva(){
    var d = document.formInserirReserva;
    
  if(d.inputMotorista[1].checked == true && d.inputMotorista[0].checked==false)
        if((d.inputOutroMotorista.value== "")){
            alert("Selecione o motorista.");
            d.inputOutroMotorista.focus();
            return false;
        }
    
    
    if((d.iDestino[0].value == "") && (d.iDestino[0].selected == true)){
        alert("Selecione o destino.");
        d.iDestino.focus();
        return false;
    }
    //Pega o tamanho -1 porque a última posição deve ser a opção outros
    if(d.iDestino[d.iDestino.length - 1].selected == true){
        if(d.inputDestinoComplementar.value == ""){
            alert("Descreva o destino.");
            d.inputDestinoComplementar.focus();
            return false;
        }
    }
    
    return true;
}

function validarSenha(){
    
    var d = document.formAlterarSenha;
    
    if(d.sSenhaAtual.value == ""){
        alert("Dados obrigatórios não preenchidos. Preencha a sua Senha Atual!");
        d.sSenhaAtual.focus();
        return false;
    }
    
    if(d.sSenhaAtual.value.length < 6){
        alert("Dados inválidos. Preencha o campo Senha Atual com no mínimo 6 dígitos!");
        d.sSenhaAtual.focus();
        return false;
    }
    
    if(d.sNovaSenha.value == ""){
        alert("Dados obrigatórios não preenchidos. Preencha a sua Nova Senha!");
        d.sNovaSenha.focus();
        return false;
    }
    
    if(d.sNovaSenha.value.length < 6){
        alert("Dados inválidos. A nova senha deve conter entre 6 e 8 caracteres.");
        d.sNovaSenha.focus();
        return false;
    }
    
    if(d.sConfirmaSenha.value == ""){
        alert("Dados obrigatórios não preenchidos. Confirme a sua Nova Senha!");
        d.sConfirmaSenha.focus();
        return false;
    }
    
    /*if(d.sConfirmaSenha.value.length <6){
        alert("Dados inválidos.");
        d.sConfirmaSenha.focus();
        return false;
    }*/
    
    if(d.sConfirmaSenha.value != d.sNovaSenha.value){
        alert("Dados inválidos. Confirmação de senha diferente da nova senha.");
        d.sConfirmaSenha.focus();
        return false;
    }
    
    return true;    
}

function validarConsultaReserva(){
    var d = document.consultaReserva;

    if(d.DataSaida.value == "" && d.DataRetorno.value == "" && d.destino.value == ""){
        alert("É preciso informar dados para consulta.");
        return false;
    }
    return true;
}

function validarConsultaServidor(){
    var d = document.consultaServidor;

    if(d.nome.value == "" && d.MatriculaSiape.value == "" && d.motorista[0].checked == false && d.motorista[1].checked == false
            && d.status_serv[0].checked == false && d.status_serv[1].checked == false){
        alert("É preciso informar dados para consulta.");
        return false;
    }
    return true;
}

function validarConsultaVeiculo(){
    var d = document.consultaVeiculo;

    if(d.ano.value == "" && d.placa.value == "" && d.renavam.value == ""){
        alert("É preciso informar dados para consulta.");
        return false;
    }
    return true;
}