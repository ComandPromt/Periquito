<?php
session_start();
$_POST['nombre']=trim($_POST['nombre']);

if(isset($_POST['envio'])&& $_POST['nombre']!=""){
$_SESSION['nombre']=$_POST['nombre'];
$_SESSION['categoria']=$_POST['categoria'];
header("Location: puerta.php");

}
?>

<form method="post" action="<?php echo $_SERVER['PHP_SELF']; ?>">
<legend>Elige la categor&iacute;a</legend>
<input name="nombre" type="text" placeholder="Pon el nombre de las fotos"/>
<select name="categoria">
<option value="1">Option 1
</option>
<option value="2">Option 2
</option>
<option value="3">Option 3
</option>
<option value="4">Option 4
</option>
<option value="5">Option 5
</option>
<option value="6">Option 6
</option>
<option value="7">Option 7
</option>
<option value="9">Option 9
</option>
<option value="10">Option 10
</option>
<option value="11">Option 11
</option>
</select>
<input type="radio" name="check" value="SI">SI</input>
<input type="radio" name="check" checked="checked" value="NO">NO</input>
<input name="envio" type="submit"/>
</form>