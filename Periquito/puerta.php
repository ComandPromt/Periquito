<!DOCTYPE html>
<html>

	<head>
		 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>

	</head>
	
	<body style="overflow-y: hidden;background-color:green;">
		<h1 style="color:black;text-align:center;">Continuar<h1>
		<div style="text-align:center;margin:auto;">
		<a style="color:black;" href="index.php">NO</a>
		<a name="si" style="color:black;" href="rem.php">SI</a><br/>
		</div><hr/>

<?php
session_start();
date_default_timezone_set('Europe/Madrid');
include("funciones.php");
comprobar_ficheros();
if($_SESSION['categoria']==9){
	$ruta="imagenes/gif/";
	cambiarExtension("imagenes/gif");
}
else{
	comprobar_imagenes("imagenes");
	$ruta="imagenes/";
}
$imagenes=showFiles($ruta);
jpeg_jpg($imagenes);
png_a_jpg("imagenes/");
if($imagenes[0]==null){
	print '<h1 style="color:red;">NO HAY IMAGENES</h1>';
}
else{
		cambiarExtension("imagenes");
		$imagenes=ver($ruta);
	
		print '<div id="myCarousel" class="carousel slide" data-ride="carousel">
				<ol class="carousel-indicators">
  		<li data-target="#myCarousel" data-slide-to="0" class="active"></li>';
		
		for($x=1;$x<count($imagenes);$x++){
			print '<li data-target="#myCarousel" data-slide-to="'.$x.'"></li>';
		}
	
		print '</ol>
	<div class="carousel-inner">
    <div class="item active">
			<img style="width:350px;height:350px;" src='.$ruta.$imagenes[0].'>
		</div>';
		
		for($x=1;$x<count($imagenes);$x++){
			print '
			<div class="item">
				<img style="width:350px;height:350px;" src='.$ruta.$imagenes[$x].'>
			</div>';   
		}
	}
?>
			<a class="left carousel-control" href="#myCarousel" data-slide="prev">
			<span class="glyphicon glyphicon-chevron-left"></span>
			<span class="sr-only">Previous</span>
			</a>
			<a class="right carousel-control" href="#myCarousel" data-slide="next">
			<span class="glyphicon glyphicon-chevron-right"></span>
			<span class="sr-only">Next</span>
			</a>
		</div>
		</div>
	</body>
</html>
