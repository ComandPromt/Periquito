<?php

date_default_timezone_set('Europe/Madrid');

function redimensionarJPG($max_ancho, $max_alto, $ruta) {

    $imagenes = check_images_ext($ruta, "jpg");

    $calidad = 100;
    for ($x = 0; $x < count($imagenes); $x++) {

        $salida = $ruta . "/" . $imagenes[$x];

        if (substr($imagenes[$x], -4, 1) == ".") {
            $img_original = imagecreatefromjpeg("./" . $ruta . "/" . $imagenes[$x]);
            list($ancho, $alto) = getimagesize("./" . $ruta . "/" . $imagenes[$x]);
            //Se calcula ancho y alto de la imagen final
            $x_ratio = $max_ancho / $ancho;
            $y_ratio = $max_alto / $alto;
            $tmp = imagecreatetruecolor($max_ancho, $max_alto);
            imagecopyresampled($tmp, $img_original, 0, 0, 0, 0, $max_ancho, $max_alto, $ancho, $alto);
            imagedestroy($img_original);
            imagejpeg($tmp, $salida, $calidad);
        }
    }
}

function check_images_ext($path, $extension) {
    $dir = opendir($path);
    $files = array();
    while ($current = readdir($dir)) {
        if ($current != "." && $current != "..") {
            if (is_dir($path . $current)) {
                showFiles($path . $current . '/');
            } else {
                if (substr($current, -3) == $extension) {
                    $files[] = $current;
                }
            }
        }
    }
    return $files;
}

function check_images($path) {
    $dir = opendir($path);
    $files = array();
    while ($current = readdir($dir)) {
        if ($current != "." && $current != "..") {
            if (is_dir($path . $current)) {
                showFiles($path . $current . '/');
            } else {

                $files[] = $current;
            }
        }
    }
    return $files;
}

function jpeg_jpg(array $imagenes, $ruta) {
    for ($x = 0; $x < count($imagenes); $x++) {
        if (substr($current, -4) == "jpeg") {
            rename($ruta . "/" . $imagenes, $ruta . "/" . substr($imagenes, 0, -4) . ".jpg");
        }
    }
}

function png_a_jpg($ruta) {
    $imagenes = check_images_ext($ruta, "png");

    for ($x = 0; $x < count($imagenes); $x++) {
        $jpg = $ruta . "/" . substr($imagenes[$x], 0, -3) . "jpg";
        $image = imagecreatefrompng($ruta . "/" . $imagenes[$x]);
        imagejpeg($image, $jpg, 100);
        unlink($ruta . "/" . $imagenes[$x]);
    }
}

$imagenes = check_images("img");

jpeg_jpg($imagenes, "img");
png_a_jpg("img");

$imagenes = check_images_ext("img", "jpg");
$ancho = array();
$alto = array();

if (check_images_ext("img", "jpg") != null && check_images_ext("img", "jpg") != "" || check_images_ext("img", "png") != null && check_images_ext("img", "png") != "") {

    for ($x = 0; $x < count($imagenes); $x++) {

        $imagen = getimagesize("img/" . $imagenes[$x]);
        $ancho[] = $imagen[0];
        $alto[] = $imagen[1];
    }
	
	$anchofinal=min($ancho);
	$altofinal=min($alto);
	
	if($anchofinal>640){
		$anchofinal=640;
	}
	if($altofinal>480){
		$altofinal=480;
	}
	
    redimensionarJPG($anchofinal, $altofinal, "img");
	
    if (count($imagenes) > 1) {

        require "src/GifCreator/AnimGif.php";

        $anim = new GifCreator\AnimGif();

        $anim->create("img/", array(25)) // first 3s, then 5s for all the others
                ->save("Output/" . date("Y") . "_" . date("d") . "_" . date("m") . "_" . date("H") . "-" . date("i") . "-" . date("s") . ".gif");

        for ($x = 0; $x < count($imagenes); $x++) {

            unlink("img/" . $imagenes[$x]);
        }
        print '<h1 name="salida">Success</h1>';
    }
} else {
     print '<h1 name="salida">Folder empty</h1>';
}
?>