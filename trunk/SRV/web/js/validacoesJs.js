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