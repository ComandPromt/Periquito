<?php
session_start();
$_POST['nombre']=trim($_POST['nombre']);

if(isset($_POST['envio'])&& $_POST['nombre']!="" && $_POST['check']=="SI"){
	$_SESSION['nombre']=$_POST['nombre'];
	$_SESSION['categoria']=$_POST['categoria'];
	header("Location: puerta.php");
}
if (!file_exists("imagenes")) {
    mkdir("imagenes", 777, true);
}
if (!file_exists("imagenes/Thumb")) {
    mkdir("imagenes/Thumb", 777, true);
}
if (!file_exists("imagenes/gif")) {
    mkdir("imagenes/gif", 777, true);
}
if (!file_exists("imagenes/gif/Thumb")) {
    mkdir("imagenes/gif/Thumb", 777, true);
}
?>
<html>
	<body>
		<form method="post" action="<?php echo $_SERVER['PHP_SELF']; ?>">
			<legend>Elige la categor&iacute;a</legend>
			<input name="nombre" type="text" placeholder="Pon el nombre de las fotos"/>
			<select name="categoria">
				<option value="1">Models
				</option>
				<option value="2">Girls/Womans
				</option>
				<option value="3">XXX
				</option>
				<option value="4">Singers
				</option>
				<option value="5">WebCam
				</option>
				<option value="6">Films/Cartoon
				</option>
				<option value="7">Dangles
				</option>
				<option value="8">Links
				</option>
				<option value="9">GIF
				</option>
				<option value="10">Sado
				</option>
				<option value="11">Earrings
				</option>
			</select>
			<input type="radio" name="check" checked="checked" value="SI">SI</input>
			<input type="radio" name="check"  value="NO">NO</input>
			<input name="envio" type="submit"/>
		</form>
	</body>
</html>
