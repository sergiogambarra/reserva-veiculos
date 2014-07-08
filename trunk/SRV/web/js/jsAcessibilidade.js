function aumentarFonte(){
    var fSize1;
    fSize1 = getCookie("fonteConteudo") || "12pt";
    
    if((parseInt(fSize1.substring(0,2))) <= 12){

        var aux = (parseInt(fSize1.substring(0,2)) + 2)+"pt";
        
        document.getElementById("corpo").style.fontSize = aux;
        
        var x = document.getElementsByClassName("containerLogado");
        var y = document.getElementsByClassName('listaCabecalhoCentral');
        var styleMenu = document.getElementsByClassName("menuAcoes");
        var styleTabela = document.getElementsByClassName("tabelaListaVeiculos");
        var styleLabelForm = document.getElementsByClassName('formularioCadastrarServidorBox');
        
        for(var i = 0; i < x.length; i++){
            x[i].style.fontSize = aux;
        }
        
        for(var j = 0; j < y.length; j++){
            y[j].style.fontSize = aux;
        }
        
        for(var k = 0; k < styleMenu.length; k++){
            styleMenu[k].style.fontSize = aux;
        }
        
        for(var l = 0; l < styleTabela.length; l++){
            styleTabela[l].style.fontSize = aux;
        }
        
        for(var m = 0; m < styleLabelForm.length; m++){
            styleLabelForm[l].style.fontSize = aux;
        }
        
        setCookie("fonteConteudo",aux );
    }
}

function diminuirFonte(){
    var fSize1;
    
    fSize1 = getCookie("fonteConteudo") || "12pt";
    
    if((parseInt(fSize1.substring(0,2))) >= 10){
        
        var aux = (parseInt(fSize1.substring(0,2)) - 2)+"pt";
        
        document.getElementById("corpo").style.fontSize = aux;        
        
        var x = document.getElementsByClassName("containerLogado");
        var y = document.getElementsByClassName('listaCabecalhoCentral');
        var styleMenu = document.getElementsByClassName("menuAcoes");
        var styleTabela = document.getElementsByClassName("tabelaListaVeiculos");
        var styleLabelForm = document.getElementsByClassName('formularioCadastrarServidorBox');
        
        for(var i = 0; i < x.length; i++){
            x[i].style.fontSize = aux;
        }
        
        for(var j = 0; j < y.length; j++){
            y[j].style.fontSize = aux;
        }
        
        for(var k = 0; k < styleMenu.length; k++){
            styleMenu[k].style.fontSize = aux;
        }
        
        for(var l = 0; l < styleTabela.length; l++){
            styleTabela[l].style.fontSize = aux;
        }
        
        for(var m = 0; m < styleLabelForm.length; m++){
            styleLabelForm[l].style.fontSize = aux;
        }
        
        setCookie("fonteConteudo",aux );
    }
    
}

function setCookie(cname, cvalue) {
    document.cookie = cname + "=" + cvalue + "; ";
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i].trim();
        if (c.indexOf(name) == 0) 
            return c.substring(name.length,c.length);
    }
    return "";
}

function verificarCookie(){
    if( document.cookie === '' || document.cookie.length == 0 ){
        return 0;
    }else{
        return 1;
    }
}

function configurarCookiesIniciais(){
    //mapeando os valores dos atributos para estilo
    setCookie("fonteConteudo", "10pt");
}

function pegarCookies(){
    if(verificarCookie() == 0){
        configurarCookiesIniciais();
    }else{
        var x = document.getElementsByClassName("containerLogado");
        var y = document.getElementsByClassName('listaCabecalhoCentral');
        var styleMenu = document.getElementsByClassName("menuAcoes");
        var styleTabela = document.getElementsByClassName("tabelaListaVeiculos");
        var styleLabelForm = document.getElementsByClassName('formularioCadastrarServidorBox');
        
        for(var i = 0; i < x.length; i++){
            x[i].style.fontSize = getCookie("fonteConteudo");
        }
        
        for(var j = 0; j < y.length; j++){
            y[j].style.fontSize = getCookie("fonteConteudo");
        }
        
        for(var k = 0; k < styleMenu.length; k++){
            styleMenu[k].style.fontSize = getCookie("fonteConteudo");
        }
        
        for(var l = 0; l < styleTabela.length; l++){
            styleTabela[l].style.fontSize = getCookie("fonteConteudo");
        }
        
        for(var m = 0; m < styleLabelForm.length; m++){
            styleLabelForm[l].style.fontSize = getCookie("fonteConteudo");
        }
    }
}