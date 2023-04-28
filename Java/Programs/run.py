import imageio

import os

import os.path

import json

import urllib.request

lectura=[]

with open('Config/Config.txt') as f:
	
	for linea in f:
		lectura.append(linea)

carpeta=lectura[0].strip()

separador="/"

if "\\" in carpeta:
	separador="\\"

path=os.path.abspath("Config")

ruta = os.path.join(carpeta+separador,"Hacer_gif") 

if not(os.path.exists(ruta)): 

	os.mkdir(ruta) 

carpeta+=separador+"Hacer_gif"+separador+"frames"+separador

ruta = os.path.join(carpeta+".."+separador,"frames") 

if not(os.path.exists(ruta)): 

	os.mkdir(ruta) 

ruta = os.path.join(carpeta+".."+separador,"Output") 

if not(os.path.exists(ruta)): 

	os.mkdir(ruta)

lista_de_archivos = os.listdir(carpeta)

if(len(lista_de_archivos)>1):

	imagenes=[]

	for file in lista_de_archivos:
	
		if file[-4:]==".jpg" or file[-4:]==".png" or file[-4:]==".gif":
			
			imagenes.append(file)

	imagenes=sorted(imagenes)

	longitud=len(imagenes)

	longitud=longitud-1

	api_url = "https://api-periquito.onrender.com/api/names/loop.gif/modo/2";

	data = json.load(urllib.request.urlopen(api_url))

	json_str = json.dumps(data)

	y = json.loads(json_str)

	nombre_nuevo=y["respuesta"][0]

	with imageio.get_writer(carpeta+".."+separador+"Output"+separador+nombre_nuevo, mode='I') as writer:
	
		for i in range(0, longitud):
			
			image = imageio.imread(os.path.join(carpeta, imagenes[i]))
			
			writer.append_data(image)
        
		for i in range(longitud, 0, -1):
			
			image = imageio.imread(os.path.join(carpeta, imagenes[i]))
			
			writer.append_data(image)

fic = open(path+separador+"llamada_python.txt","w")

fic.write('gif')
    
fic.close()
