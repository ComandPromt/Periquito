# Periquito v3.0

---

# 1. ¿Qué es Periquito v3.0?

- Este programa principalmente está pensado para subir imágenes masivas a un CMS de gestión de imágenes creando imágenes ( 640 x 480 px ) con su correspondiente miniatura (100 x 125 px )

---

## 1.1 Acciones automáticas

- Crea carpetas para su funcionamiento

- Cada imagen se redimensiona y se renombra con la fecha y hora actual y se convierte a JPG 

- Mueve las imágenes procesadas al servidor
---

## 1.2 Requisitos

- Ffmpeg (se instala en el servidor,es decir, en el equipo del CMS)

- Google Chrome

[Chrome](https://www.google.com/intl/es_ALL/chrome/)

- Servidor web con base de datos MySQL (WAMP ,LAMP,MAMP o XAMPP)

[Windows](http://prdownloads.sourceforge.net/appserv/appserv-win32-8.6.0.exe?download)

### Linux
~~~bash
sudo apt update
sudo apt install apache2

# Abra el archivo de configuración principal con su editor de texto:

sudo nano /etc/apache2/apache2.conf

# Escribimos ServerName dominio_del_servidor_o_IP 

# Guardamos y cerramos el archivo

sudo systemctl restart apache2

sudo apt install mysql-server php mysql

sudo apt install php libapache2-mod-php php-mcrypt php-mysql php-cli

sudo systemctl restart apache2
~~~

- Java

[Java](https://www.java.com/es/download/)

- CMS

[CMS](https://github.com/ComandPromt/4images-UPDATED)

----

## 1.3 Instalación del CMS

- Accede a http://localhost/4images/install.php (cambia "localhost" por la IP o dominio del servidor)

## 1.4 Configuración de las Utilidades

- Descarga las utilidades en la sección "Download"
- Descomprime y pega cada utilidad en la carpeta raíz del servidor web

![Preview](previews/4.png)

----

# 2. Manual del programa Java

- Nota: antes de nada debes tener un archivo llamado "chromedriver.exe" en la carpeta de la aplicación. Se puede descargar desde [Chrome Driver](http://chromedriver.chromium.org/downloads) o [Chrome Driver](https://sites.google.com/a/chromium.org/chromedriver/downloads)

## Iniciar la aplicación

Ejecutar "Empezar.java" , el ".JAR" o ".EXE" de la aplicación

## Uso de la utilidad principal

- Escribir el nombre común de las imágenes (En este caso subiremos varias imágenes de perros)

![Preview](previews/0.png)

- Seleccionar la categoría correspondiente y pulsar el botón "Play"

- Listo!!

### Nota: se debe tener en cuenta que se mostrará un mensaje de las veces que tienes que pulsar el botón de play en el caso de que se quieran subir más de 40 imágenes al CMS

----

## 2.1 Casilla Fix

- Esta opción copia las imágenes al CMS (se aconseja usar esta utilidad en caso de fallo de la aplicación)

![Preview](previews/1.png)

----

## 2.2 Utilidades de conversión (Crear GIF, GIF a frames, Video a GIF, Video a frames)

- Tenemos que pegar el archivo requerido en cada carpeta del programa

- Hacer clic en la acción que queramos

![Preview](previews/2.png)

----

# 3. Ayuda

- En la sección ayuda se nos muestran los pasos para las configuraciones del programa

- Enlace a una página web para convertir fotos en blanco y negro a color

![Preview](previews/3.png)

# 4. Utilidades
---

![Preview](previews/4.png)

## GIF 2 Frames

[GIF 2 Frames](https://gifframes.herokuapp.com/)

[Source](https://github.com/ComandPromt/GifFrames-API)

---
## Frames 2 GIF

[Source](https://github.com/ComandPromt/Frames-to-GIF)

---
## Video 2 Frames

[Source](https://github.com/ComandPromt/Video-to-frames-php)

---

---
## Video 2 GIF

VID-2-GIF\index.php

~~~php
<?php
error_reporting(0);
session_start();

include("funciones.php");

    $numimmp4=check_images("./","mp4");
	$numiflv=check_images("./","flv");
	$numim3u8=check_images("./","m3u8");
	$numimkv=check_images("./","mkv");
	$numimts=check_images("./","ts");
	$numiavi=check_images("./","avi");
	$numi3gp=check_images("./","3gp");
	$numimov=check_images("./","mov");
	$numiwmv=check_images("./","wmv");
	$numiwebm=check_images("./","ebm");
$videos=array();

if(count($numimmp4)==1){
	$videos[0]=$numimmp4[0];
}

if(count($numiflv)==1 && $videos[0]==null){
	$videos[0]=$numiflv[0];
}

if(count($numim3u8)==1 && $videos[0]==null){
	$videos[0]=$numim3u8[0];
}

if(count($numimkv)==1 && $videos[0]==null){
	$videos[0]=$numimkv[0];
}

if(count($numimts)==1 && $videos[0]==null){
	$videos[0]=$numimts[0];
}
	
if(count($numiavi)==1 && $videos[0]==null){
	$videos[0]=$numiavi[0];
}

if(count($numi3gp)==1 && $videos[0]==null){
	$videos[0]=$numi3gp[0];
}

if(count($numimov)==1 && $videos[0]==null){
	$videos[0]=$numimov[0];
}

if(count($numiwmv)==1 && $videos[0]==null){
	$videos[0]=$numiwmv[0];
}

if(count($numiwebm)==1 && $videos[0]==null){
	$videos[0]=$numiwebm[0];
}

  if(count($videos)==1){ 
 
	rename($videos[0],"video".substr($videos[0],-4)) or die(print '<h1 name="salida">Cambia el nombre</h1>');  

	rename("video".substr($videos[0],-4),"../FrameExtractor/examples/video/".$videos[0]);
	header("Location: ../FrameExtractor/examples/index.php");
	$_SESSION['video2gif']=true;
  }
  else{
	print '<h1 name="salida">No tienes videos</h1>';
  }

~~~

VID-2-GIF\funciones.php

~~~
<?php

function mover($imagenes,$extension){
    if ($imagenes) {
        mover_cuarenta("imagenes/tmp", "imagenes", 0,$extension);
    } else {
        if (count(check_images("imagenes", $extension)) > 40 && count(check_images("imagenes", $extension)) > 0) {
            mover_cuarenta("imagenes", "imagenes/tmp", 40,$extension);
        }

        if (count(check_images("imagenes/tmp", $extension)) > 40 && count(check_images("imagenes/tmp", $extension)) > 0 && count(check_images("imagenes", $extension)) == 0) {
            mover_cuarenta("imagenes/tmp", "imagenes", 40,$extension);
        }

        if (count(check_images("imagenes/tmp", $extension)) < 40 && count(check_images("imagenes/tmp", $extension)) > 0 && count(check_images("imagenes", $extension)) > 0 && count(check_images("imagenes", $extension)) < 40
        ) {

            if (count(check_images("imagenes/tmp", $extension)) >= 40) {
                while (count(check_images("imagenes", $extension)) < 40) {
                    mover_cuarenta("imagenes/tmp", "imagenes", 40 - count(check_images("imagenes", $extension)),$extension);
                }
            } else {
                if (count(check_images("imagenes/tmp", $extension)) + count(check_images("imagenes", $extension)) <= 40) {
                    mover_cuarenta("imagenes/tmp", "imagenes", 0,$extension);
                } else {

                    mover_cuarenta("imagenes/tmp", "imagenes", (count(check_images("imagenes/tmp", $extension)) + count(check_images("imagenes", $extension)) - 40),$extension);
                }
            }

        }

        if (count(check_images("imagenes/tmp", $extension)) >= 40 && count(check_images("imagenes", $extension)) > 0) {

            $numero = count(check_images("imagenes", $extension));
            if (count(check_images("imagenes/tmp", $extension)) > 40) {

                $numero += count(check_images("imagenes/tmp", $extension)) - 40;
            }

            for ($x = $numero; $x < 40; $x++) {
                mover_cuarenta("imagenes/tmp", "imagenes", $numero,$extension);
            }

        }
    }
}

function mover_cuarenta($origen, $destino, $numero,$extension){
    if (substr($origen, -1) != "/") {
        $origen .= "/";
    }
    if (substr($destino, -1) != "/") {
        $destino .= "/";
    }

    if (count(check_images($origen, $extension)) > 0) {
        $num_archivos = count(check_images($origen, $extension)) - $numero;
        $imagenes = check_images($origen, $extension);
        for ($x = 0; $x < $num_archivos; $x++) {
            rename($origen . $imagenes[count($imagenes) - ($x + 1)], $destino . $imagenes[count($imagenes) - ($x + 1)]);
        }
    }
}

function caracteres($cadena) {
    $salida = array();
    $posicion = strpos($cadena, " ");
    if ($posicion == null) {
        $salida[] = $cadena;
    } else {
        while ($posicion != null) {
            if ($posicion != null) {
                $salida[] = substr($cadena, 0, $posicion);
                $cadena = substr($cadena, $posicion + 1);
            }
            $posicion = strpos($cadena, " ");
            if ($posicion == null) {
                $salida[] = $cadena;
            }
        }
    }
    return $salida;
}
function check_images($path, $extension) {
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
function comprobar_imagenes($ruta) {
    $imagenes = ver($ruta);
    for ($x = 0; $x < count($imagenes); $x++) {
        $extension = strtolower(substr($imagenes[$x], -3));
        switch ($extension) {
            case "peg":
            case "PEG":
            case "Peg":
            case "PeG":
            case "PEg":
            case "pEG":
                $extension = "jpg";
                $nombre_nuevo = substr($imagenes[$x], 0, -4);
                break;
            default:
                $nombre_nuevo = substr($imagenes[$x], 0, -3);
                break;
                rename($ruta . "/" . $imagenes[$x], $ruta . "/" . $nombre_nuevo . $extension);
        }
    }
    png_a_jpg($ruta . "/");
}
function comprobar_ficheros() {
       if (!file_exists("imagenes/Thumb")) {
        mkdir("imagenes/Thumb", 777, true);
    }
    if (!file_exists("imagenes/gif")) {
        mkdir("imagenes/gif", 777, true);
    }
	if (!file_exists("imagenes/tmp")) {
        mkdir("imagenes/tmp", 777, true);
    }
	if (file_exists("imagenes/desktop.ini")) {
        unlink('imagenes/desktop.ini');
    }
    if (file_exists("imagenes/Thumbs.db")) {
        unlink('imagenes/Thumbs.db');
    } 
    if (file_exists("imagenes/gif/Thumbs.db")) {
        unlink('imagenes/gif/Thumbs.db');
    } 
    if (file_exists("imagenes/Thumb/Thumbs.db")) {
        unlink('imagenes/Thumb/Thumbs.db');
    }
}
function showFiles($path) {
    $dir = opendir($path);
    $files = array();
    while ($current = readdir($dir)) {
        if ($current != "." && $current != "..") {
            if (is_dir($path . $current)) {
                showFiles($path . $current . '/');
            } else {
                if (substr($current, -4, 1) == "." || substr($current, -3, 1) == "." || substr($current, -5, 1) == ".") {
                    $files[] = $current;
                }
            }
        }
    }
	sort($files);
    return $files;
}
function ver($path) {
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
function jpeg_jpg(array $imagenes) {
    for ($x = 0; $x < count($imagenes); $x++) {
        if (substr($current, -4) == "jpeg") {
            rename("imagenes/" . $imagenes[$x], "imagenes/" . substr($imagenes[$x], 0, -4) . ".jpg");
        }
    }
}
function gif_a_jpg(array $imagenes, $ruta) {
    for ($x = 0; $x < count($imagenes); $x++) {
        $origen = $ruta . substr($imagenes[$x], 0, -4) . substr($imagenes[$x], -4);
        $destino = $ruta . "Thumb/" . substr($imagenes[$x], 0, -4) . "_Thumb" . ".jpg";
        imagejpeg(imagecreatefromstring(file_get_contents($origen)), $destino);
    }
}
function mover_imagenes($ruta) {
    $imagenes = showFiles($ruta);
    for ($x = 0; $x < count($imagenes); $x++) {
        $origen = $ruta . $imagenes[$x];
        $destino = $ruta . "Thumb/" . $imagenes[$x];
        $buscar = substr($imagenes[$x], 0, -4);
        $buscar = substr($buscar, -6);
        if ($buscar == "_Thumb") {
            rename($origen, $destino);
        }
    }
}

function cambiarExtension($ruta) {
    $imagenes = showFiles("imagenes/");
    $nombre = date("Y") . "_" . date("d") . "_" . date("m") . "_" . date("H") . "-" . date("i") . "-" . date("s");
    $x = 1;
    for ($i = 0; $i < count($imagenes); $i++) {
        $cadena = $ruta . "/" . $imagenes[$i];
        $extension = substr($imagenes[$i], -4);
        $origen = "imagenes/" . $imagenes[$i];
        switch ($extension) {
            case ".png":
            case ".PNG":
                if ($_SESSION['categoria'] == 9) {
                    $ok = true;
                } else {
                    $ok = false;
                    while ($ok == false) {
                        if (file_exists($nombre . "_" . $x . ".jpg")) {
                            $x++;
                        } else {
                            $destino = "imagenes/" . $nombre . "_" . $x . ".png";
                            rename($origen, $destino);
                            unlink($destino);
                            $x++;
                            $ok = true;
                        }
                    }
                }
                break;
            case ".GIF":
            case ".gif":
                if ($_SESSION['categoria'] == 9) {
                    $destino = "imagenes/gif/" . $nombre . "_" . $x . ".gif";
                    rename($origen, $destino);
                }
                break;
            case "JPEG":
            case "jpeg":
            case ".jpg":
			case ".JPG":
                if ($_SESSION['categoria'] != 9) {
                    $destino = "imagenes/" . $nombre . "_" . $x . ".jpg";
                    rename($origen, $destino);
                }
                break;
        }
        $x++;
    }
}
function png_a_jpg($ruta) {
    $imagenes = check_images("imagenes", "png");
    for ($x = 0; $x < count($imagenes); $x++) {
        $jpg = $ruta . substr($imagenes[$x], 0, -3) . "jpg";
        $image = imagecreatefrompng($ruta . $imagenes[$x]);
        imagejpeg($image, $jpg, 100);
        unlink($ruta . $imagenes[$x]);
    }
}
function redimensionarJPG($max_ancho, $max_alto, $ruta, $gif) {
    $imagenes = check_images($ruta, "jpg");
    $calidad = 100;
    for ($x = 0; $x < count($imagenes); $x++) {
        if ($gif) {
            $salida = $ruta . "/" . $imagenes[$x];
        } else {
            if ($max_ancho == 100 && $max_alto == 125) {
                $salida = "./" . $ruta . "/" . substr($imagenes[$x], 0, -4) . "_Thumb" . substr($imagenes[$x], -4);
            } else {
                $salida = "./" . $ruta . "/" . $imagenes[$x];
            }
        }
        if (substr($imagenes[$x], -10) != "_Thumb.jpg" && substr($imagenes[$x], -4, 1) == "." || $gif) {
            $img_original = imagecreatefromjpeg("./" . $ruta . "/" . $imagenes[$x]);
            list($ancho, $alto) = getimagesize("./" . $ruta . "/" . $imagenes[$x]);
            //Se calcula ancho y alto de la imagen final
            $x_ratio = $max_ancho / $ancho;
            $y_ratio = $max_alto / $alto;
            //Si el ancho y el alto de la imagen no superan los maximos, 
            //ancho final y alto final son los que tiene actualmente
            if (($ancho <= $max_ancho) && ($alto <= $max_alto)) {//Si ancho 
                $ancho_final = $ancho;
                $alto_final = $alto;
            } elseif (($x_ratio * $alto) < $max_alto) {
                $alto_final = ceil($x_ratio * $alto);
                $ancho_final = $max_ancho;
            } else {
                $ancho_final = ceil($y_ratio * $ancho);
                $alto_final = $max_alto;
            }
            $tmp = imagecreatetruecolor($ancho_final, $alto_final);
            imagecopyresampled($tmp, $img_original, 0, 0, 0, 0, $ancho_final, $alto_final, $ancho, $alto);
            imagedestroy($img_original);
            imagejpeg($tmp, $salida, $calidad);
        }
    }
}
function consecutivos(array $array){
	if(count($array)>0 && $array[0]!=null && $array[0]==1){
        asort($array);
        for($x=0;$x<count($array);$x++){
            if($x+1<count($array)){
            if($array[$x]+1!=$array[$x+1]){
                $numero=$array[$x]+1;
                $x=count($array);
                $noc=true;
            }
            }
        }
        if(!isset($noc)){
            $numero=count($array)+1;
        }
  
    }
    else{
        $numero=1;
    }
	return $numero;
}
?>
~~~

---

# Image API

*Nota: se usa esta API para asegurar que las imágenes se suben en orden alfabético, es decir, en el mismo orden que tenían antes de ser renombradas. Esta API se usará en el programa Java para que el usuario sólo tenga en el servidor web las utilidades del programa

[API](https://dashboard.heroku.com/apps/apiperiquito)
[Source](https://github.com/ComandPromt/Images-Periquito-API)
