function validarMatSiap(mat){
	if ((mat.length <= 0) || (mat.length !== 7) || (isNaN(mat))){
		alert("Matricula inválida. Digite 7 números.");
		return false;
	}else{
		return true;
	}
}

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

 
function mascaraLetras(e){
    var dig = (window.event)?event.keycode:e.which;
    if (dig >= 33 && dig <= 64 || dig >= 91 && dig <= 93 || dig >= 123 && dig <= 159 || dig >= 162 && dig <= 191 ){
        return false;
    }else{
        return true;
    }
}

//valida numero inteiro com mascara
function mascaraInteiro(){
	if (event.keyCode < 48 || event.keyCode > 57){
		event.returnValue = false;
		return false;
	}
	return true;
}

// Função que aplica uma máscara de formato ao Cpf
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