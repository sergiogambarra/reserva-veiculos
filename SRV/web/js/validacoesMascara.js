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
	if((digito > 47) && (digito < 58)){
		return true;
	}else{
	if (digito != 8) // backspace  
                //event.keyCode = 0;  
                return false;  
            else  
                return true;  
        }  
}

//PERMITE SOMENTE INSERÇÃO DE LETRAS (Bug corrigido no chrome)
function mascaraLetras(e){
    var tecla=(window.event)?event.keyCode:e.which;
    if((tecla >= 65 && tecla <= 90)||(tecla >= 97 && tecla <= 122 || (tecla == 32))){
        return true;
    }    
    else{
        if (tecla != 8) 
            return false;
        else 
            return true;
    }
}

//Mascara Alfanumérica para PLACA
function mascaraAlfanumerica(e){
    var tecla=(window.event)?event.keyCode:e.which;
    if((tecla >= 65 && tecla <= 90)||(tecla >= 97 && tecla <= 122)||(tecla >= 48 && tecla <= 57)){
        return true;
    }    
    else{
        if (tecla != 8) 
            return false;
        else 
            return true;
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

// Função que aplica uma máscara de formato a Placa
function mascaraPlaca(evento)
{
    var origem;
    var txtOrigem;
    var key;
    if(window.event)
    {
        key = evento.keyCode;
        if(key >= 97 && key <= 122)
        {
            origem = evento.srcElement;
            txtOrigem = origem.value;
            if(txtOrigem.length == 3)
            {
                txtOrigem += "-";
                origem.value = txtOrigem ;
            }
        }
    }
    if(txtOrigem>3){
        if(key >= 48 && key <= 57)
        {
            txtOrigem += "";
            origem.value = txtOrigem ;
        }
        else
        {
            event.returnValue = false;
        }  
    }
    else
    {
        key = evento.which;
        if(key >= 97 && key <= 122)
        {
            origem = evento.target;
            txtOrigem = origem.value;
            if(txtOrigem.length == 3)
            {
                txtOrigem += "-";
                origem.value = txtOrigem ;
            }
        }
    }
    if(txtOrigem > 3){
        if(key >= 48 && key <= 57)
        {
            txtOrigem += "";
            origem.value = txtOrigem ;
            
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