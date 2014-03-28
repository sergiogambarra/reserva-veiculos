

            function limpar(campo) 
		{
			campo.value = "";
			
		}


              function verificar() 
		{
			/*declaração de variavel
			 var x;
			 x =  document.frm.nome.value;
			 if (x == ""){
			alert("Você não digitou o nome da cidade");
			return false;
		   } else{
		
			alert("Tudo OK ...Olá tudo belezinha");
 
			return true;
		   }	*/
		   
			if (document.frm.nome.value == ""){
			alert("Você não digitou o nome do Cliente");
			return false;
		   } 
		


                   if (document.frm.telefone.value == ""){
			alert("Você não digitou o numero do Telefone");
			return false;
		   } 
		

                   if (document.frm.endereco.value == ""){
			alert("Você não digitou o Endereco do cliente");
			return false;
		   } 
		

                     if (document.frm.regmel[0].checked == true || document.frm.regmel[1].checked == true){
			alert("Tudo OK ...");
			return true;
		   } else{
		
			alert("Você não marcou nenhum campo");
 
			return false;
		   }

                  if (document.frm.cpf.value == ""){
			alert("Você não digitou o CPF do Cliente");
			return false;
		   } 
		


                   if (document.frm.rg.value == ""){
			alert("Você não selecionou o RG do cliente");
			return false;
		   } 
		

                   if (document.frm.datadenascimento.value == ""){
			alert("Você não digitou Data de Nascimento do cliente");
			return false;
		   } 
		   
		       if (document.frm.cnpj.value == ""){
			alert("Você não digitou o CNPJ do Cliente");
			return false;
		   } 
		


                   if (document.frm.inscricaoestadual.value == ""){
			alert("Você não digitou a Inscrição Estadual do Cliente");
			return false;
		   } 
		

                   if (document.frm.inscricaomunicipal.value == ""){
			alert("Você não digitou a Inscrição Municipal do Cliente");
			return false;
		   } 
		   
		   if (document.frm.datafundacao.value == ""){
			alert("Você não a Data de Fundação do Cliente");
			return false;
		   } 
		   


                   if (document.frm1.tamanho.value == ""){
			alert("Você não digitou o tamanho da Camisa");
			return false;
		   } 
		

                   if (document.frm1.cor.value == ""){
			alert("Você não digitou a cor da Camisa");
			return false;
		   } 
		   
		  
                   
   }                
                   function verificar2() {
  
	            var x=document.getElementsByTagName("input");
	                alert(x.length);

	              for (i=0; i<x.length; i++)
	           {
		       alert(x[i].name);
	           }

  return false;
}


                   
                        function mostrarValor() {
                                        
                        if(document.frm1.camisa.value == "Adidas"){
						
			    document.frm1.preco.value ="15.00";
						
			} else{ 
			if(document.frm1.camisa.value == "Nike"){
			    document.frm1.preco.value ="25.00";
		 } else{
			   document.frm1.preco.value ="20.00";
		      }
                    }				                          
                 }


