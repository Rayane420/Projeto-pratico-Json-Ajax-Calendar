<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../scriptGanttView/jquery-ui-1.8.4.css" />
	<link rel="stylesheet" type="text/css" href="../scriptGanttView/reset.css" />
	<link rel="stylesheet" type="text/css" href="../scriptGanttView/jquery.ganttView.css" />
	<script type="text/javascript" src="../scriptGanttView/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="../scriptGanttView/date.js"></script>
	<script type="text/javascript" src="../scriptGanttView/jquery-ui-1.8.4.js"></script>
	<script type="text/javascript" src="../scriptGanttView/jquery.ganttView.js"></script>
	<style type="text/css">
		body {
			font-family: tahoma, verdana, helvetica;
			font-size: 0.8em;
			padding: 10px;
		}
	</style>
<title>Gantt View</title>
</head>
<body>

<h1>Gantt View</h1>
	<div id="ganttChart"></div>
	<br/><br/>
	<div id="eventMessage"></div>

</body>

<script type="text/javascript">


	$(document).ready(function(){
		
	

	$.get("buscarDatasPlanejamento", function(response) {
		
		var ganttDataResposta = JSON.parse(response);
		
		var ganttData = ""; //Inicio
		ganttData += "[";
		
		$.each(ganttDataResposta, function (index, projeto){
			ganttData +="{ \"id\": \""+projeto.id+"\", \"name\": \""+projeto.nome+"\", \"series\": [";
			
		$.each(projeto.series, function(idx, serie) {
			
			var cores = "#3366FF,#00CC00".split(',');
			
			var cor;
			
			if (idx === 0){
				cor = "#CC33CC";
			} else{
				cor = Number.isInteger(idx / 2) ? cores[0] : cores[1];
			}
			
				var dataInicial = serie.dataInicial.split('-');
				var dataFinal = serie.dataFinal.split('-');
			
				ganttData +="{ \"name\": \""+serie.nome+"\", \"start\":\""+ new Date(dataInicial[0],dataInicial[1], dataInicial[2])+"\", \"end\": \""+new Date(dataFinal[0],dataFinal[1],dataFinal[2])+"\" , \"color\": \""+cor+"\", \"projeto\": \""+serie.projeto+"\" , \"serie\": \""+serie.id+"\" }";               
				if (idx < projeto.series.length - 1){
					ganttData +=",";	
				}
			}); //fim for da séries
			
			ganttData +="]}";
			
			if(index < ganttDataResposta.length - 1){
				ganttData +=",";
			}

		}); //fim for dos projetos
			
			ganttData += "]";
			ganttData = JSON.parse(ganttData);
		
			$("#ganttChart").ganttView({ 
				data: ganttData,
				slideWidth: 600,
				behavior: {
					onClick: function (data) { 
						var msg = "Evento de click: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
						$("#eventMessage").text(msg);
					},
					onResize: function (data) { 
						
						var start = data.start.toString("yyyy-M-d");
						var end = 	data.end.toString("yyyy-M-d");
						$.post("buscarDatasPlanejamento", { start: start, end : end, serie : data.serie, projeto : data.projeto });  //informando parâmetro e valor
						
						var msg = "Evento de errastar: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
						$("#eventMessage").text(msg);
						
					},
					onDrag: function (data) { 
						
						var start = data.start.toString("yyyy-M-d");
						var end = 	data.end.toString("yyyy-M-d");
						$.post("buscarDatasPlanejamento", { start: start, end : end, serie : data.serie, projeto : data.projeto });
						
						var msg = "Evento de errastar: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
						$("#eventMessage").text(msg);
						
					}
					
				}
			});
		
});	

});
	</script>
	
</html>