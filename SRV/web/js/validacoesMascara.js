function validarMatSiap(mat){
	if ((mat.length <= 0) || (mat.length !== 7) || (isNaN(mat))){
		alert("Matricula inválida. Digite 7 números.");
		return false;
	}else{
		return true;
	}
}

//permite somente inserção de números 
function mascaraMatSiap(e){
    var digito = (window.event)?event.keyCode:e.which;
    if((digito > 47 && digito < 58)|| digito == 8 || digito == 0){
        return true;
    } else{
        return false;
    }
}

//PERMITE SOMENTE INSERÇÃO DE LETRAS (Bug corrigido no chrome)
function mascaraLetras(e){
    var dig=(window.event)?event.keyCode:e.which;
    if((dig >= 65 && dig <= 90)||(dig >= 97 && dig <= 122) || dig == 32 || dig == 8 || dig == 0){
        return true;
    }    
    else{ 
        return false;
    }
}

//Mascara Alfanumérica para PLACA (somente letra maiúscula, números e o -.
function mascaraAlfanumerica(e){
    var dig=(window.event)?event.keyCode:e.which;
    if((dig >= 65 && dig <= 90)||(dig >= 48 && dig <= 57)|| dig==45 || dig == 8 || dig == 0 ){
        return true;
    }    
    else{
            return false;
    }
}

//Máscara de formato ao Cpf permite somente inclusão de números
function mascaraCpf(evento)
{
    var origem;
    var txtOrigem;
    var key;
    if(window.event)
    {
        key = evento.keyCode;
        if(key >= 48 && key <= 57)
        {
            origem = evento.srcElement;
            txtOrigem = origem.value;
            if(txtOrigem.length == 3 || txtOrigem.length == 7 )
            {
                txtOrigem += ".";
                origem.value = txtOrigem ;
            }
            if(txtOrigem.length == 11)
            {
                txtOrigem += "-";
                origem.value = txtOrigem ;
            }
        }else
        {
            event.returnValue = false;
        }  
    }
    else
    {
        key = evento.which;
        if(key >= 48 && key <= 57)
        {
            origem = evento.target;
            txtOrigem = origem.value;
            if(txtOrigem.length == 3 || txtOrigem.length == 7 )
            {
                txtOrigem += ".";
                origem.value = txtOrigem ;
            }
            if(txtOrigem.length == 11)
            {
                txtOrigem += "-";
                origem.value = txtOrigem ;
            }
        }else
        {
            if(key != 8 && key != 0)
            {
                evento.preventDefault();
            }
        }
    }
     
}

//Máscara de formato ao Telefone
function mascaraTelefone(evento)
{
    var origem;
    var txtOrigem;
    var key;
    if(window.event)
    {
        key = evento.keyCode;
        if(key >= 48 && key <= 57)
        {
            origem = evento.srcElement;
            txtOrigem = origem.value;
            if(txtOrigem.length <1)
            {
                txtOrigem += "(";
                origem.value = txtOrigem ;
            }
            if(txtOrigem.length == 3)
            {
                txtOrigem += ")";
                origem.value = txtOrigem ;
            }
            if(txtOrigem.length == 4)
            {
                txtOrigem += " ";
                origem.value = txtOrigem ;
            }
            if(txtOrigem.length == 9)
            {
                txtOrigem += "-";
                origem.value = txtOrigem ;
            }
        }else
        {
            event.returnValue = false;
        }  
    }
    else
    {
        key = evento.which;
        if(key >= 48 && key <= 57)
        {
            origem = evento.target;
            txtOrigem = origem.value;
            if(txtOrigem.length <1)
            {
                txtOrigem += "(";
                origem.value = txtOrigem ;
            }
            if(txtOrigem.length == 3)
            {
                txtOrigem += ")";
                origem.value = txtOrigem ;
            }
            if(txtOrigem.length == 4)
            {
                txtOrigem += " ";
                origem.value = txtOrigem ;
            }
            if(txtOrigem.length == 9)
            {
                txtOrigem += "-";
                origem.value = txtOrigem ;
            }
        }else
        {
            if(key != 8 && key != 0)
            {
                evento.preventDefault();
            }
        }
    }
     
}

// Função que aplica uma máscara de formato a Data
function mascaraData(evento)
{
    var origem;
    var txtOrigem;
    var key;
    if(window.event)
    {
        key = evento.keyCode;
        if(key >= 48 && key <= 57)
        {
            origem = evento.srcElement;
            txtOrigem = origem.value;
            if(txtOrigem.length == 4 || txtOrigem.length == 7 )
            {
                txtOrigem += "-";
                origem.value = txtOrigem ;
            }
        }else
        {
            event.returnValue = false;
        }  
    }
    else
    {
        key = evento.which;
        if(key >= 48 && key <= 57)
        {
            origem = evento.target;
            txtOrigem = origem.value;
            if(txtOrigem.length == 4 || txtOrigem.length == 7 )
            {
                txtOrigem += "-";
                origem.value = txtOrigem ;
            }
        }else
        {
            if(key != 8 && key != 0)
            {
                evento.preventDefault();
            }
        }
    }
     
}