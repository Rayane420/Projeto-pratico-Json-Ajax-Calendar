<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DataTable jQuery</title>
<link rel="stylesheet" href="//cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="//cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js" ></script>

</head>
<body>

<table id="minhatabela" class="display" style="width:100%">
        <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
            </tr>
        </thead>
        
        <tfoot>
            <tr>
                <th>Id</th>
                <th>Login</th>
            </tr>
        </tfoot>
    </table>
</body>
<script type="text/javascript">

	$(document).ready(function() { //faz o processamento na hora que abre a tela
    	$('#minhatabela').DataTable( {
     	   "processing": true,      //processamento
       		"serverSide": true,		//informa que irá trabalhar com o servidor
        	"ajax": "carregarDadosDataTable" // URL de retorno dos dados do servidor (resposta do servidor)
    	} );
	} );
	
</script>
</html>