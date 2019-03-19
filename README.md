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

----

## 1.3 Instalación del CMS

- Accede a http://localhost/4images/install.php (cambia "localhost" por la IP o dominio del servidor)

## 1.4 Configuración de la aplicación PHP para subir imágenes masivas al CMS

- Debes tener una carpeta llamada "Periquito" en la carpeta raíz del servidor web

- Dentro de esta, debes tener una carpeta llamada "imágenes" con esta estructura

- Nota: el programa las crea automáticamente

- ->imagenes
- --->Thumb (dentro de la carpeta imagenes)
- --->gif
- ----->Thumb (dentro de la carpeta gif)

- Copia o mueve tus imágenes (JPG,PNG,GIF) a la carpeta imágenes

- Opcional: para las utilidades (Video a GIF, frames a GIF y video a frames) tienes que tener una carpeta llamada "Utilidades" dentro de la carpeta "Periquito"

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
GIF 2 Frames

[GIF 2 Frames](https://gifframes.herokuapp.com/)

[Source](https://github.com/ComandPromt/GifFrames-API)

---
Frames 2 GIF

[Source](https://github.com/ComandPromt/Frames-to-GIF)

---
Video 2 Frames

[Source](https://github.com/ComandPromt/Video-to-frames-php)

---
Image API

*Nota: se usa esta API para asegurar que las imágenes se suben en orden alfabético, es decir, en el mismo orden que tenían antes de ser renombradas

[API](https://dashboard.heroku.com/apps/apiperiquito)
[Source](https://github.com/ComandPromt/Images-Periquito-API)
