# Periquito

- Sube imagenes al servidor de forma masiva

## Este programa está pensado para

- Redimensionar imagenes

- Crear imagenes de miniatura

- Subir las imagenes a nuestro CMS

# Parte HTL Y PHP

## Funcionamiento

- Renombra imagenes (automaticamente)

- Crea carpetas para su funcioamiento (automaticamente)

- Cambia las imagenes a jpg (automaticamente)

- Redimensiona imagenes (automaticamente)

# Parte Java

## Funcionamiento

- Mueve las imagenes al servidor (por red, es decir, mediante una carpeta compartida)

## Requisitos

- Mozilla Firefox

- Servidor web

- Base de datos (de un CMS gestor de imagenes que tengamos instalado)

- Java

## Pasos

# Version 1.1 en adelante
- Copia la carpeta "Periquito" en el servidor

- Crea dentro de esta, una carpeta llamada imagenes con esta estructura

- ->imagenes
- --->Thumb
- --->gif
- ----->Thumb

---
# Version 1.2 en adelante

- Crea una carpeta llamada "img" dentro de la carpeta "Hacer_gif"

- Crea una carpeta llamada "Output" dentro de la carpeta "Hacer_gif"

----

# Version 1.1 en adelante

- Copia o mueve tus imagenes a la carpeta imagenes

- Los archivos .gif también van en la carpeta imagenes

- Copia el archivo "copiar.bat" en C:\

- Copia el archivo "geckodriver.exe" en C:\

- Edita el archivo "index.php" (Cambia Option 1 por las distintas categorias de las imagenes)

- Edita el archivo "rem.php"
 
- Cambia los valores de los parametros por los tuyos (usuario,password,bd)

- Cambia las sentencias SQL por tus tablas

- Edita el archivo MenuPrincipal.java (Cambia Option 1 por las distintas categorias de las imagenes)

- Modifica las rutas de origen y salida de imagenes

- Ejecutar Empezar.java

- Escribir el nombre comun de las imagenes (se insertaran en la BD con el mismo nombre de imagen y no de archivo)

- Ej. Si tenemos tres imagenes de perros y escojemos el nombre de dog

- Se subiran las tres imagenes con el nombre dog (para la parte web) pero con distinto nombre de archivo

- Seleccionar la categoria y pulsar enter

- Listo!!
