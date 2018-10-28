## Este programa está pensado principalmente para

- Redimensionar imagenes

- Crear imagenes de miniatura

- Subir las imagenes a nuestro CMS
---
# Parte HTML Y PHP

## Funcionamiento

- Renombra imagenes (automaticamente)

- Crea carpetas para su funcioamiento (automaticamente)

- Cambia las imagenes a jpg (automaticamente)

- Redimensiona imagenes (automaticamente)
---
# Parte Java

## Funcionamiento

- Mueve las imagenes al servidor (por red, es decir, mediante una carpeta compartida)

## Requisitos

- Google Chrome

- Servidor web

- Base de datos (de un CMS gestor de imagenes que tengamos instalado)

- Java

## Pasos a seguir

- Debes tener una carpeta "Periquito" en el servidor

- Debes tener dentro de esta, una carpeta llamada imagenes con esta estructura

- ->imagenes
- --->Thumb
- --->gif
- ----->Thumb

- Debes tener una carpeta llamada "img" dentro de la carpeta "Hacer_gif"

- Debes tener una carpeta llamada "Output" dentro de la carpeta "Hacer_gif"

- Copia o mueve tus imagenes a la carpeta imagenes

- Los archivos .gif también van en la carpeta imagenes

- Copia el archivo "copiar.bat" en C:\

- Descarga el archivo "chromedriver.exe" de [Chrome Driver](http://chromedriver.chromium.org/downloads) o [Chrome Driver](https://sites.google.com/a/chromium.org/chromedriver/downloads)

- Ppéga el archivo en C:\

- Edita el archivo "index.php" (Cambia Option 1 por las distintas categorias de las imagenes)

- Edita el archivo "rem.php"
 
- Cambia los valores de los parametros por los tuyos (usuario,password,bd)

- Cambia las sentencias SQL por tus tablas

- Edita el archivo MenuPrincipal.java (Cambia Option 1 por las distintas categorias de las imagenes) hasta la version 2.0
- A partir de la version 2.1 en adelante no hay que hacer este paso.

- Modifica las rutas de origen y salida de imagenes

- Ejecutar Empezar.java
----
# Manual

# 1.Subir imagenes masivas al servidor
- Escribir el nombre comun de las imagenes

- Ej. Si tenemos tres imagenes de perros y escojemos el nombre de dog

- Se subiran las tres imagenes con el nombre dog 

- Seleccionar la categoria y pulsar enter

- Listo!!

# 2.Funciones de crear gif, extraer gif,gif a frames y video a gif

- Tenemos que pegar el archivo requerido en cada carpeta del programa
- Hacer clic en la accion que queramos

# 3.Ayuda
- En la seccion ayuda se nos muestran los pasos para las configuraciones del programa
