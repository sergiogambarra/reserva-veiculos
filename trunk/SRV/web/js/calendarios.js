jQuery(function(){

    /* Calendários para reserva: */
    jQuery('#inputDataSaida').datetimepicker({
        format:'d/m/Y',
        minDate: "today",
        mask: true,
        /*
        onShow:function( ct ){
         this.setOptions({
          maxDate:jQuery('#inputDataRetorno').val()?jQuery('#inputDataRetorno').val():false
         })
        }
        ,*/
        timepicker:false
    });
    
    jQuery('#inputDataRetorno').datetimepicker({
        format:'d/m/Y',
        minDate: "today",
        mask: true,
        onShow:function( ct ){
            this.setOptions({
                minDate:jQuery('#inputDataSaida').val()?jQuery('#inputDataSaida').val():jQuery('#inputDataSaida').val()
            })
        },
        timepicker:false
    });
    $('#inputHoraSaida').datetimepicker({
        datepicker:false,
        format:'H:i',
        mask: true
    });
    $('#inputHoraRetorno').datetimepicker({
        datepicker:false,
        format:'H:i',
        mask: true
        /* , minTime: 0 */
    });
   
   /* Calendário para data de nascimento */
   
   jQuery('#sDataNascimento').datetimepicker({
        format:'d/m/Y',
        mask: true,
        onShow:function( ct ){
            this.setOptions({
                maxDate: calcularMenorIdade()  
            })
        },
        timepicker:false
    });
    
    jQuery('#sManDataInicial').datetimepicker({
        format:'d/m/Y',
        minDate: "today",
        mask: true,
        timepicker:false
    });
    
    jQuery('#sManDataFinal').datetimepicker({
        format:'d/m/Y',
        minDate: "today",
        mask: true,
        onShow:function( ct ){
            this.setOptions({
                minDate:jQuery('#sManDataInicial').val()?jQuery('#sManDataInicial').val():jQuery('#sManDataInicial').val()
            })
        },
        timepicker:false
    });
    
    jQuery('#DataSaida').datetimepicker({
        format:'d/m/Y',
        mask: true,
        timepicker:false
    });
    
    jQuery('#DataRetorno').datetimepicker({
        format:'d/m/Y',
        mask: true,
        timepicker:false
    });
});

function calcularMenorIdade(){
    var dataAtual = new Date();
    var dia = dataAtual.getDate();
    /* Pega o índice do vetor de meses do js, por isso +1 */
    var mes = dataAtual.getMonth() + 1;
    var ano = dataAtual.getFullYear() - 18;
    
    var retorno = dia + "/" + mes + "/" + ano;
    
    return retorno;

}