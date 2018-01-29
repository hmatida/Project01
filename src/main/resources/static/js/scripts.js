 $(document).ready(function () {
        var $seuCampoCpf = $("#cnpj");
        $seuCampoCpf.mask("99.999.999/9999-99");
    });

$(document).ready(function(index, element){
       $("#tabela_etq tbody tr #status").each(function(){
           var valor = $(this).text();
           if(valor == "Cancelada"){
               $(this).parent().addClass('fundo_table_alert');
           }
       });
   });

$(document).ready('shown.bs.modal', function () {
     $('#myInput').trigger('focus')
   });

   $(document).ready(function(){
       $("#formSave").click(function(){
          var senha=document.getElementById('password_1').value;
          var repsenha=document.getElementById('password_2').value;
          if (senha != repsenha){
          alert("As senhas não coincidem.");
          return false;
          }
       });
   });
