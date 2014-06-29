jQuery(function(){
    
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
        mask: true,
        minTime: 0
    });
    $('#inputHoraRetorno').datetimepicker({
        datepicker:false,
        format:'H:i',
        mask: true,
        minTime: 0
    });
});