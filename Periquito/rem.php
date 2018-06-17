<?php

session_start();
date_default_timezone_set('Europe/Madrid');
include("funciones.php");

if ($_SESSION['categoria'] != 9) {
    redimensionarJPG(640, 480, "imagenes/", false);
    redimensionarJPG(100, 125, "imagenes/", false);
    mover_imagenes("imagenes/");
    $imagenes = check_images("imagenes","jpg");
} else {
    $imagenes = check_images("imagenes/gif/","gif");
    gif_a_jpg($imagenes, "imagenes/gif/");
    redimensionarJPG(100, 125, "imagenes/gif/Thumb", true);
    $imagenes = showFiles("imagenes/gif","jpg");
}

$hostbd = "192.168.1.2";
$userbd = "root";
$passbd = "pass";

$numeros = array();
$ids = array();

if ($numeros[0] == NULL) {
    $numero = 1;
}

if ($ids[0] == NULL) {
    $id = 1;
}

$conexion = mysqli_connect($hostbd, $userbd, $passbd, "folder");
mysqli_select_db($conexion, 'folder');
	
for ($x = 0; $x < count($imagenes); $x++) {
    
    $consulta = mysqli_query($conexion, "SELECT image_id FROM 4images_images ORDER BY image_id");

    while ($fila = mysqli_fetch_row($consulta)) {
        $numeros[] = $fila[0];
    }

    $numero = consecutivos($numeros);
    $thumb = substr($imagenes[$x], 0, -4) . "_Thumb.jpg";
    
    if (substr($imagenes[$x], -4, 1) == ".") {
        $consulta = "INSERT INTO 4images_images VALUES(" . $numero . "," . $_SESSION['categoria'] . ",1,'" . $_SESSION['nombre'] . "',DEFAULT,DEFAULT," . time() . ",DEFAULT,'" . $imagenes[$x] . "','" . $thumb . "','',DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT)";
        mysqli_query($conexion, $consulta);
        $cadenas = caracteres($_SESSION['nombre']);
        $consulta = mysqli_query($conexion, "SELECT word_id FROM 4images_wordlist where word_text='" . $_SESSION['nombre'] . "'");

        while ($fila = mysqli_fetch_row($consulta)) {
            $id = $fila[0];
        }
        
        if ($id == null) {
            $id = consecutivos($ids);
        }

        for ($y = 0; $y < count($cadenas); $y++) {
            $consulta = "INSERT INTO 4images_wordlist VALUES('$cadenas[$y]', $id)";
            mysqli_query($conexion, $consulta);
            $consulta = "INSERT INTO 4images_wordmatch VALUES($numero,$id,1,0,0)";
            mysqli_query($conexion, $consulta);
            $ids = array();
            $id = consecutivos($ids);
        }
    }
    $numeros = array();
}
mysqli_close($conexion);
print '<h1 id="salida" name="salida">' . $_SESSION['categoria'] . "</h1>";
print '<a name="otravez" href="index.php">Subir mas fotos</a>';
?>